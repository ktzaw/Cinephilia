package com.ktz.cinephilia.repository.upcoming

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ktz.cinephilia.data.paged.MoviePagingDataSource
import com.ktz.cinephilia.data.model.Movies
import com.ktz.cinephilia.service.ApiService
import com.ktz.cinephilia.service.MovieType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpcomingRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UpcomingRepository {

    override fun getUpcomingMovies(): Flow<PagingData<Movies>> {

        val pagingSourceFactory = MoviePagingDataSource(apiService, MovieType.UPCOMING)

        return Pager(
            config = PagingConfig(

                pageSize = 20,
                enablePlaceholders = true,
                initialLoadSize = 3

            ), pagingSourceFactory = { pagingSourceFactory }
        ).flow

    }

}