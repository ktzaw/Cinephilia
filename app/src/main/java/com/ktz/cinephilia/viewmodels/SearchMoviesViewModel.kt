package com.ktz.cinephilia.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ktz.cinephilia.data.model.SearchResponse
import com.ktz.cinephilia.repository.search.SearchMoviesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(

    private val repository: SearchMoviesRepositoryImpl

):ViewModel(){

    fun searchMovies(query:String):Flow<PagingData<SearchResponse.SearchMovies>>{

        return repository.searchMovies(query).cachedIn(viewModelScope)

    }

}