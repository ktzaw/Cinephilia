package com.ktz.cinephilia.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ktz.cinephilia.data.model.Movies
import com.ktz.cinephilia.repository.popular.PopularRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val repository: PopularRepositoryImpl
) : ViewModel() {

    fun loadPopularMovies(): Flow<PagingData<Movies>> {

        return repository.getPopularMovies().cachedIn(viewModelScope)

    }

}