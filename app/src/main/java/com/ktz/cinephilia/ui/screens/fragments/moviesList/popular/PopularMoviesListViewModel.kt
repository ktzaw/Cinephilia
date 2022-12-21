package com.ktz.cinephilia.ui.screens.fragments.moviesList.popular

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ktz.cinephilia.data.models.entities.moviesList.PopularMoviesListEntity
import com.ktz.cinephilia.repositories.moviesList.PopularMoviesListRepository
import com.ktz.cinephilia.ui.screens.fragments.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularMoviesListViewModel @Inject constructor(private val repository: PopularMoviesListRepository) : BaseViewModel() {

    fun loadPopularMoviesList(): Flow<PagingData<PopularMoviesListEntity>> {
        return repository.getPopularMoviesList().cachedIn(viewModelScope)
    }
}
