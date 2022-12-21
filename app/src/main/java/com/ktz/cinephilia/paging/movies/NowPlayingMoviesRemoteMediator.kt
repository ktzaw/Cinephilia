package com.ktz.cinephilia.paging.movies

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ktz.cinephilia.data.models.entities.moviesList.NowPlayingMoviesListEntity
import com.ktz.cinephilia.data.models.entities.remoteKeys.NowPlayingMoviesRemoteKeys
import com.ktz.cinephilia.data.models.remote.movies.toNowPlayingMoviesListEntity
import com.ktz.cinephilia.data.source.local.room.CinephiliaDatabase
import com.ktz.cinephilia.data.source.remote.api.MoviesApi
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class NowPlayingMoviesRemoteMediator(
    private val moviesApi: MoviesApi,
    private val database: CinephiliaDatabase
) : RemoteMediator<Int, NowPlayingMoviesListEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NowPlayingMoviesListEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                Timber.d(nextKey.toString())
                nextKey
            }
        }

        try {
            val response = moviesApi.getNowPlayingMovies(page)
            val movies = mutableListOf<NowPlayingMoviesListEntity>()

            response.map {
                it.results.forEach { nowPlayingMoviesResponse ->
                    movies.add(nowPlayingMoviesResponse.toNowPlayingMoviesListEntity())
                }
            }

            val endOfPaginationReached = movies.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.nowPlayingMoviesListRemoteKeysDao().deleteAllRemoteKeys()
                    database.nowPlayingMoviesListDao().deleteAllNowPlayingMovies()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = movies.map {
                    NowPlayingMoviesRemoteKeys(id = it.movieId, prevKey = prevKey, nextKey = nextKey)
                }
                database.nowPlayingMoviesListRemoteKeysDao().insertAll(keys)
                database.nowPlayingMoviesListDao().insertNowPlayingMovies(movies)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, NowPlayingMoviesListEntity>): NowPlayingMoviesRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { nowPlayingMoviesResponse ->
                database.nowPlayingMoviesListRemoteKeysDao().getRemoteKeysByMovieId(nowPlayingMoviesResponse.movieId)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, NowPlayingMoviesListEntity>): NowPlayingMoviesRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { nowPlayingMoviesResponse ->
                database.nowPlayingMoviesListRemoteKeysDao().getRemoteKeysByMovieId(nowPlayingMoviesResponse.movieId)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, NowPlayingMoviesListEntity>): NowPlayingMoviesRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.movieId?.let { movieId ->
                database.nowPlayingMoviesListRemoteKeysDao().getRemoteKeysByMovieId(movieId)
            }
        }
    }
}
