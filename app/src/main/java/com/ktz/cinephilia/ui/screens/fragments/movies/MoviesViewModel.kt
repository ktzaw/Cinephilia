package com.ktz.cinephilia.ui.screens.fragments.movies

import com.ktz.cinephilia.data.domain.toMovieResponseViewObject
import com.ktz.cinephilia.data.domain.toNowPlayingMoviesViewObject
import com.ktz.cinephilia.data.domain.toTopRatedMoviesResponseViewObject
import com.ktz.cinephilia.data.domain.toTopRatedMoviesViewObject
import com.ktz.cinephilia.repositories.MoviesRepository
import com.ktz.cinephilia.ui.screens.CinephiliaEvent
import com.ktz.cinephilia.ui.screens.fragments.BaseViewModel
import com.ktz.cinephilia.utils.onErrorState
import com.ktz.cinephilia.utils.onSuccessState
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : BaseViewModel<MoviesEvent>() {

    fun loadTopRatedMovies() = repository.getTopRatedMovies(1)
        .onSuccessState {
            it.toTopRatedMoviesResponseViewObject()
            it.results.forEach { topRatedMovies ->
                topRatedMovies.toTopRatedMoviesViewObject()
            }
        }
        .onErrorState {
            emitEvent(CinephiliaEvent.Nothing)
        }

    fun loadNowPlayingMovies() = repository.getNowPlayingMovies(1)
        .onSuccessState {
            Timber.d("${it.results}")
            it.toMovieResponseViewObject()

            it.results.forEach { nowPlayingMovies ->
                nowPlayingMovies.toNowPlayingMoviesViewObject()
            }
        }
        .onErrorState {
            emitEvent(CinephiliaEvent.Nothing)
        }

    fun loadPopularMovies() = repository.getPopularMovies(1)
        .onSuccessState {
            it.toMovieResponseViewObject()
        }
        .onErrorState {
            emitEvent(CinephiliaEvent.Nothing)
        }

    fun loadUpcomingMovies() = repository.getUpcomingMovies(1)
        .onSuccessState {
            it.toMovieResponseViewObject()
        }
        .onErrorState {
            emitEvent(CinephiliaEvent.Nothing)
        }

//    fun loadGenreList() = repository.getGenreList()
}
