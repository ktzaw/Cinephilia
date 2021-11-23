package com.ktz.cinephilia.repository.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ktz.cinephilia.data.model.SearchResponse
import com.ktz.cinephilia.data.paged.SearchMoviePagingDataSource
import com.ktz.cinephilia.service.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SearchMoviesRepository {

    override fun searchMovies(query: String): Flow<PagingData<SearchResponse.SearchMovies>> {
        val pagingSourceFactory = SearchMoviePagingDataSource(apiService, query)

        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = true,
                initialLoadSize = 1
            ), pagingSourceFactory = { pagingSourceFactory }
        ).flow

    }

}