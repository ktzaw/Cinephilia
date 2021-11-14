package com.ktz.cinephilia.viewmodels

import androidx.lifecycle.ViewModel
import com.ktz.cinephilia.data.model.MovieDetail
import com.ktz.cinephilia.repository.movieDetail.MovieDetailRepository
import com.ktz.cinephilia.repository.movieDetail.MovieDetailRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repository: MovieDetailRepositoryImpl) :
    ViewModel() {

    suspend fun loadMovieDetail(movieId: Int): MovieDetail {

        return repository.getMovieDetail(movieId)

    }

}