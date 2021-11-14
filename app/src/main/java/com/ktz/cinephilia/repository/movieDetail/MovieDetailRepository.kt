package com.ktz.cinephilia.repository.movieDetail

import com.ktz.cinephilia.data.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {

    suspend fun getMovieDetail(movieId:Int): MovieDetail

}