package com.ktz.cinephilia.data.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ktz.cinephilia.data.model.MovieResponse
import com.ktz.cinephilia.data.model.Movies
import com.ktz.cinephilia.data.model.SearchResponse
import com.ktz.cinephilia.service.ApiService
import com.ktz.cinephilia.utils.MovieType
import com.ktz.cinephilia.utils.API_KEY
import javax.inject.Inject

class SearchMoviePagingDataSource @Inject constructor(
    private val apiService: ApiService,
    private val query: String
) : PagingSource<Int, SearchResponse.SearchMovies>() {


    override fun getRefreshKey(state: PagingState<Int, SearchResponse.SearchMovies>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResponse.SearchMovies> {
        return try {
            val nextPage = params.key ?: 1
            val response = apiService.searchMovies(API_KEY, query, page = nextPage)

            LoadResult.Page(

                data = response.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nextPage == response.totalPages) null else nextPage + 1
            )

        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

}