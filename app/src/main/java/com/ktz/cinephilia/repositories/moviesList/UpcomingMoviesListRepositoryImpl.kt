package com.ktz.cinephilia.repositories.moviesList

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ktz.cinephilia.data.models.entities.moviesList.PopularMoviesListEntity
import com.ktz.cinephilia.data.models.entities.moviesList.UpcomingMoviesListEntity
import com.ktz.cinephilia.data.source.local.room.CinephiliaDatabase
import com.ktz.cinephilia.data.source.remote.api.MoviesApi
import com.ktz.cinephilia.paging.movies.PopularMoviesRemoteMediator
import com.ktz.cinephilia.paging.movies.UpcomingMoviesRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpcomingMoviesListRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val database: CinephiliaDatabase
) : UpcomingMoviesListRepository {

    private val dao = database.upcomingMoviesListDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getUpcomingMoviesList(): Flow<PagingData<UpcomingMoviesListEntity>> {
        dao.getUpcomingMoviesList()
        val pagingSourceFactory = { dao.getUpcomingMoviesList() }

        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = UpcomingMoviesRemoteMediator(
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