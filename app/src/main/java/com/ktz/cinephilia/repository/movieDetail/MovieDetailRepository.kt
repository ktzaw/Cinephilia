package com.ktz.cinephilia.repository.movieDetail

import com.ktz.cinephilia.data.model.MovieDetail
import com.ktz.cinephilia.data.model.ReviewResponses
import com.ktz.cinephilia.data.model.Reviews
import com.ktz.cinephilia.data.model.VideoResponses

interface MovieDetailRepository {

    suspend fun getMovieDetail(movieId:Int): MovieDetail
    suspend fun getVideo(movieId: Int):VideoResponses
    suspend fun addToFavourite(movieDetail: MovieDetail)
    suspend fun removeFromFavourite(movieId: Int)

    suspend fun addReviewToFavourite(movieReview:Reviews)
    suspend fun getReviews(movieId: Int):ReviewResponses


}