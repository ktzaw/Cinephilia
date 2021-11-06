package com.ktz.cinephilia.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ktz.cinephilia.data.model.Movies
import com.ktz.cinephilia.repository.popular.PopularRepositoryImpl
import com.ktz.cinephilia.repository.upcoming.UpcomingRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(
    private val repository: UpcomingRepositoryImpl
) : ViewModel() {

    fun loadUpcomingMovies(): Flow<PagingData<Movies>> {

        return repository.getUpcomingMovies().cachedIn(viewModelScope)

    }

}