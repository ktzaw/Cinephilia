package com.ktz.cinephilia.repository.favourite

import androidx.lifecycle.LiveData
import com.ktz.cinephilia.data.model.MovieDetail
import com.ktz.cinephilia.data.model.Reviews

interface FavouriteMovieRepository {

    suspend fun getAllMovies():LiveData<List<MovieDetail>>
    suspend fun deleteMovie(movieId:Int)

    suspend fun getReview(movieId: Int):LiveData<List<Reviews>>
    suspend fun deleteReview(movieId: Int)

}