package com.ktz.cinephilia.paging.movies

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ktz.cinephilia.data.models.entities.moviesList.PopularMoviesListEntity
import com.ktz.cinephilia.data.models.entities.remoteKeys.PopularMoviesRemoteKeys
import com.ktz.cinephilia.data.models.remote.movies.toPopularMoviesListEntity
import com.ktz.cinephilia.data.source.local.room.CinephiliaDatabase
import com.ktz.cinephilia.data.source.remote.api.MoviesApi
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class PopularMoviesRemoteMediator(
    private val moviesApi: MoviesApi,
    private val database: CinephiliaDatabase
) : RemoteMediator<Int, PopularMoviesListEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PopularMoviesListEntity>
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
            val response = moviesApi.getPopularMovies(page)
            val movies = mutableListOf<PopularMoviesListEntity>()

            response.map {
                it.results.forEach { popularMoviesResponse ->
                    movies.add(popularMoviesResponse.toPopularMoviesListEntity())
                }
            }

            val endOfPaginationReached = movies.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.popularMoviesRemoteKeysDao().deleteAllRemoteKeys()
                    database.popularMoviesListDao().deleteAllPopularMovies()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = movies.map {
                    PopularMoviesRemoteKeys(id = it.movieId, prevKey = prevKey, nextKey = nextKey)
                }
                database.popularMoviesRemoteKeysDao().insertAll(keys)
                database.popularMoviesListDao().insertPopularMovies(movies)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PopularMoviesListEntity>): PopularMoviesRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { popularMoviesResponse ->
                database.popularMoviesRemoteKeysDao().getRemoteKeysByMovieId(popularMoviesResponse.movieId)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PopularMoviesListEntity>): PopularMoviesRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { popularMoviesResponse ->
                database.popularMoviesRemoteKeysDao().getRemoteKeysByMovieId(popularMoviesResponse.movieId)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, PopularMoviesListEntity>): PopularMoviesRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.movieId?.let { movieId ->
                database.popularMoviesRemoteKeysDao().getRemoteKeysByMovieId(movieId)
            }
        }
    }
}
