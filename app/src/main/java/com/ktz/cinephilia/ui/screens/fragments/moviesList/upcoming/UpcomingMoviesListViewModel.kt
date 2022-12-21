package com.ktz.cinephilia.ui.screens.fragments.moviesList.upcoming

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ktz.cinephilia.data.models.entities.moviesList.UpcomingMoviesListEntity
import com.ktz.cinephilia.repositories.moviesList.UpcomingMoviesListRepository
import com.ktz.cinephilia.ui.screens.fragments.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesListViewModel @Inject constructor(private val repository: UpcomingMoviesListRepository) : BaseViewModel() {

    fun loadUpcomingMoviesList(): Flow<PagingData<UpcomingMoviesListEntity>> {
        return repository.getUpcomingMoviesList().cachedIn(viewModelScope)
    }
}
