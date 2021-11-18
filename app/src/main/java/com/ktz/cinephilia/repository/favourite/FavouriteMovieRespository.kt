package com.ktz.cinephilia.repository.favourite

import androidx.lifecycle.LiveData
import com.ktz.cinephilia.data.model.MovieDetail

interface FavouriteMovieRespository {

    suspend fun getAllMovies():LiveData<List<MovieDetail>>
    suspend fun deleteMovie(movieId:Int)

}