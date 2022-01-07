package com.ktz.cinephilia.repository.movieDetail

import com.ktz.cinephilia.data.model.MovieDetail
import com.ktz.cinephilia.data.model.ReviewResponses
import com.ktz.cinephilia.data.model.ReviewResult
import com.ktz.cinephilia.data.model.VideoResponses
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {

    suspend fun getMovieDetail(movieId:Int): MovieDetail
    suspend fun getVideo(movieId: Int):VideoResponses
    suspend fun addToFavourite(movieDetail: MovieDetail)
    suspend fun removeFromFavourite(movieId: Int)

    suspend fun addReviewToFavourite(movieReview:ReviewResult)
    suspend fun getReviews(movieId: Int):ReviewResponses


}