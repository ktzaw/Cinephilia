package com.ktz.cinephilia.repository.search

import androidx.paging.PagingData
import com.ktz.cinephilia.data.model.SearchResponse
import kotlinx.coroutines.flow.Flow

interface SearchMoviesRepository {

    fun searchMovies(query:String): Flow<PagingData<SearchResponse.SearchMovies>>

}