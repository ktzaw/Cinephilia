package com.ktz.cinephilia.data.paged

import android.widget.Toast
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ktz.cinephilia.data.model.MovieResponse
import com.ktz.cinephilia.data.model.Movies
import com.ktz.cinephilia.service.ApiService
import com.ktz.cinephilia.service.MovieType
import com.ktz.cinephilia.utils.API_KEY
import javax.inject.Inject

class MoviePagingDataSource @Inject constructor(
    private val apiService: ApiService,
    private val movieType: MovieType
) : PagingSource<Int, Movies>() {
    override fun getRefreshKey(state: PagingState<Int, Movies>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies> {
        return try {
            val nextPage = params.key ?: 1
            val response = loadPage(API_KEY, page = nextPage)

            LoadResult.Page(

                data = response.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nextPage == response.totalPages) null else nextPage + 1
            )

        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    private suspend fun loadPage(
        apikey: String,
        page: Int

    ): MovieResponse<Movies> {

        val response = when (movieType) {

            MovieType.NOW_PLAYING -> {

                apiService.loadNowPlayingMovies(apikey, page)

            }

            MovieType.POPULAR -> {

                apiService.loadPopularMovies(apikey, page)

            }

            MovieType.UPCOMING -> {
                apiService.loadUpcomingMovies(apikey, page)
            }

        }

        return response

    }


}