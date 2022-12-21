package com.ktz.cinephilia.data.source.remote.api

import com.ktz.cinephilia.data.models.entities.moviesList.NowPlayingMoviesListEntity
import com.ktz.cinephilia.data.models.remote.movies.BaseMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.NowPlayingMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.PopularMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.TopRatedMoviesBaseResponse
import com.ktz.cinephilia.data.models.remote.movies.UpcomingMoviesResponse
import com.ktz.cinephilia.utils.StatefulData

interface MoviesApi {

    suspend fun getTopRatedMovies(page: Int): StatefulData<TopRatedMoviesBaseResponse>
    suspend fun getNowPlayingMovies(page: Int): StatefulData<BaseMoviesResponse<NowPlayingMoviesResponse>>
    suspend fun getPopularMovies(page: Int): StatefulData<BaseMoviesResponse<PopularMoviesResponse>>
    suspend fun getUpcomingMovies(page: Int): StatefulData<BaseMoviesResponse<UpcomingMoviesResponse>>

}
