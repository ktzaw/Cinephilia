package com.ktz.cinephilia.repository.movieDetail

import com.ktz.cinephilia.BuildConfig
import com.ktz.cinephilia.data.model.MovieDetail
import com.ktz.cinephilia.data.model.VideoResponses
import com.ktz.cinephilia.service.ApiService
import com.ktz.cinephilia.utils.API_KEY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(

    private val apiService: ApiService

) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return apiService.loadMovieDetail(movieId, API_KEY)
    }

    override suspend fun getVideo(movieId: Int): VideoResponses {
        return apiService.loadMovieTrailer(movieId, API_KEY)
    }
}