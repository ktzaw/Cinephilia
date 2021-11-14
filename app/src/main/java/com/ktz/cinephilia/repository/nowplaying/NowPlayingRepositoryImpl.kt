package com.ktz.cinephilia.repository.nowplaying

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ktz.cinephilia.data.paged.MoviePagingDataSource
import com.ktz.cinephilia.data.model.Movies
import com.ktz.cinephilia.service.ApiService
import com.ktz.cinephilia.service.MovieType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NowPlayingRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : NowPlayingRepository {

    override fun getNowPlayingMovies(): Flow<PagingData<Movies>> {

        val pagingSourceFactory = MoviePagingDataSource(apiService, MovieType.NOW_PLAYING)

        return Pager(
            config = PagingConfig(

                pageSize = 30,
                enablePlaceholders = true,
                initialLoadSize = 1

            ), pagingSourceFactory = { pagingSourceFactory }
        ).flow

    }


}