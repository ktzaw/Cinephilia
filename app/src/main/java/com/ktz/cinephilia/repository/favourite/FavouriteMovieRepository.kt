package com.ktz.cinephilia.repository.favourite

import androidx.lifecycle.LiveData
import com.ktz.cinephilia.data.model.MovieDetail
import com.ktz.cinephilia.data.model.ReviewResponses
import com.ktz.cinephilia.data.model.ReviewResult

interface FavouriteMovieRepository {

    suspend fun getAllMovies():LiveData<List<MovieDetail>>
    suspend fun deleteMovie(movieId:Int)

    suspend fun getAllReviews():LiveData<List<ReviewResult>>
    suspend fun deleteReview(movieId: Int)

}