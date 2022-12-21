package com.ktz.cinephilia.repositories.moviesList

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ktz.cinephilia.data.models.entities.moviesList.NowPlayingMoviesListEntity
import com.ktz.cinephilia.data.source.local.room.CinephiliaDatabase
import com.ktz.cinephilia.data.source.remote.api.MoviesApi
import com.ktz.cinephilia.paging.movies.NowPlayingMoviesRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NowPlayingMoviesListRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val database: CinephiliaDatabase
) : NowPlayingMoviesListRepository {

    private val dao = database.nowPlayingMoviesListDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getNowPlayingMoviesList(): Flow<PagingData<NowPlayingMoviesListEntity>> {
        dao.getNowPlayingMoviesList()
        val pagingSourceFactory = { dao.getNowPlayingMoviesList() }

        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = NowPlayingMoviesRemoteMediator(
                moviesApi,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}
