package com.ktz.cinephilia.repositories.moviesList

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ktz.cinephilia.data.models.entities.moviesList.PopularMoviesListEntity
import com.ktz.cinephilia.data.source.local.room.CinephiliaDatabase
import com.ktz.cinephilia.data.source.remote.api.MoviesApi
import com.ktz.cinephilia.paging.movies.PopularMoviesRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMoviesListRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val database: CinephiliaDatabase
) : PopularMoviesListRepository {

    private val dao = database.popularMoviesListDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getPopularMoviesList(): Flow<PagingData<PopularMoviesListEntity>> {
        dao.getPopularMoviesList()
        val pagingSourceFactory = { dao.getPopularMoviesList() }

        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = PopularMoviesRemoteMediator(
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
