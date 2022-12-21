package com.ktz.cinephilia.ui.screens.fragments.movies

import androidx.lifecycle.viewModelScope
import com.ktz.cinephilia.data.models.remote.movies.toNowPlayingMovies
import com.ktz.cinephilia.data.models.remote.movies.toPopularMovies
import com.ktz.cinephilia.data.models.remote.movies.toTopRatedMovies
import com.ktz.cinephilia.data.models.remote.movies.toUpcomingMovies
import com.ktz.cinephilia.repositories.movies.MoviesRepository
import com.ktz.cinephilia.ui.screens.CinephiliaEvent
import com.ktz.cinephilia.ui.screens.fragments.BaseViewModel
import com.ktz.cinephilia.utils.mapState
import com.ktz.cinephilia.utils.onErrorState
import com.ktz.cinephilia.utils.onLoadingState
import com.ktz.cinephilia.utils.onSuccessState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO var _loadingState and val loadingState

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : BaseViewModel() {

    private var fetchJob: Job? = null

    private val _loadingState: Channel<Boolean> = Channel(Channel.RENDEZVOUS)
    var loadingState: Flow<Boolean> = _loadingState.receiveAsFlow()

    fun loadTopRatedMovies() = repository.getTopRatedMovies(1)
        .mapState {
            it.map { topRatedMoviesResponse ->
                topRatedMoviesResponse.toTopRatedMovies()
            }
        }
        .onSuccessState { stopLoading() }
        .onLoadingState { emitLoading() }
        .onErrorState { emitEvent(CinephiliaEvent.Nothing) }

    fun loadNowPlayingMovies() = repository.getNowPlayingMovies(1)
        .onSuccessState {
            it.map { nowPlayingMoviesResponse ->
                nowPlayingMoviesResponse.toNowPlayingMovies()
            }
            // loadingState = false
        }
//        .onLoadingState { loadingState = true }
        .onErrorState { emitEvent(CinephiliaEvent.Nothing) }

    fun loadPopularMovies() = repository.getPopularMovies(1)
        .onSuccessState {
            it.map { popularMoviesResponse ->
                popularMoviesResponse.toPopularMovies()
            }
            // loadingState = false
        }
//        .onLoadingState { loadingState = true }
        .onErrorState { emitEvent(CinephiliaEvent.Nothing) }

    fun loadUpcomingMovies() = repository.getUpcomingMovies(1)
        .onSuccessState {
            it.map { upcomingMoviesResponse ->
                upcomingMoviesResponse.toUpcomingMovies()
            }
            // loadingState = false
        }
//        .onLoadingState { loadingState = true }
        .onErrorState { emitEvent(CinephiliaEvent.Nothing) }

    private fun loadData() {
        fetchJob = viewModelScope.launch {
            launch { loadTopRatedMovies() }
            launch { loadNowPlayingMovies() }
            launch { loadPopularMovies() }
            launch { loadUpcomingMovies() }
        }
    }

    private fun emitLoading() {
        _loadingState.trySend(true)
    }

    private fun stopLoading() {
        _loadingState.trySend(false)
    }

    fun refresh() {
        fetchJob?.cancel()
        loadData()
    }
}
