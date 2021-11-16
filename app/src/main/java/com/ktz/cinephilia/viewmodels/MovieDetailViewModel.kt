package com.ktz.cinephilia.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.ktz.cinephilia.data.db.MoviesDatabase
import com.ktz.cinephilia.data.model.MovieDetail
import com.ktz.cinephilia.data.model.VideoResponses
import com.ktz.cinephilia.repository.movieDetail.MovieDetailRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieDetailRepositoryImpl,
    private val database: MoviesDatabase
) : ViewModel() {

    suspend fun loadMovieDetail(movieId: Int): MovieDetail {

        return repository.getMovieDetail(movieId)

    }

    suspend fun loadMovieTrailer(movieId: Int): VideoResponses {

        return repository.getVideo(movieId)

    }

    suspend fun isFavourite(movieId: Int): Boolean {

        var isFavourite = true

        database.withTransaction {
            isFavourite = database.favouriteDao.isFavourite(movieId)
        }

        return isFavourite
    }

}