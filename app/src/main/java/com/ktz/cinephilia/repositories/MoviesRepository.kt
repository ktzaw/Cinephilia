package com.ktz.cinephilia.repositories

import com.ktz.cinephilia.data.domain.GenreListResponse
import com.ktz.cinephilia.data.domain.MovieResponses
import com.ktz.cinephilia.data.domain.NowPlayingMovies
import com.ktz.cinephilia.data.domain.PopularMovies
import com.ktz.cinephilia.data.domain.TopRatedMoviesResponse
import com.ktz.cinephilia.data.domain.UpcomingMovies
import com.ktz.cinephilia.utils.StatefulData
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getNowPlayingMovies(page: Int): Flow<StatefulData<MovieResponses<NowPlayingMovies>>>
    fun getPopularMovies(page: Int): Flow<StatefulData<MovieResponses<PopularMovies>>>
    fun getUpcomingMovies(page: Int): Flow<StatefulData<MovieResponses<UpcomingMovies>>>
    fun getTopRatedMovies(page: Int): Flow<StatefulData<TopRatedMoviesResponse>>

//    fun getGenreList(): Flow<StatefulData<GenreListResponse>>
}
