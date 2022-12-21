package com.ktz.cinephilia.ui.screens.fragments.moviesList.nowPlaying

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ktz.cinephilia.data.models.entities.moviesList.NowPlayingMoviesListEntity
import com.ktz.cinephilia.repositories.moviesList.NowPlayingMoviesListRepository
import com.ktz.cinephilia.ui.screens.fragments.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NowPlayingListViewModel @Inject constructor(private val repository: NowPlayingMoviesListRepository) : BaseViewModel() {

    fun loadNowPlayingMoviesList(): Flow<PagingData<NowPlayingMoviesListEntity>> {
        return repository.getNowPlayingMoviesList().cachedIn(viewModelScope)
    }
}
