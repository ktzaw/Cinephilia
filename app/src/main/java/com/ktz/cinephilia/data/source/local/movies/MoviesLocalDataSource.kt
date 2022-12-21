package com.ktz.cinephilia.data.source.local.movies

import com.ktz.cinephilia.data.models.remote.movies.NowPlayingMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.PopularMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.TopRatedMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.UpcomingMoviesResponse
import com.ktz.cinephilia.utils.StatefulData
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {

    fun saveAllTopRatedMovies(movies: List<TopRatedMoviesResponse>)
    fun saveAllNowPlayingMovies(movies: List<NowPlayingMoviesResponse>)
    fun saveAllPopularMovies(movies: List<PopularMoviesResponse>)
    fun saveAllUpcomingMovies(movies: List<UpcomingMoviesResponse>)

    fun getAllTopRatedMovies(): Flow<StatefulData<List<TopRatedMoviesResponse>>>
    fun getAllNowPlayingMovies(): Flow<StatefulData<List<NowPlayingMoviesResponse>>>
    fun getAllPopularMovies(): Flow<StatefulData<List<PopularMoviesResponse>>>
    fun getAllUpcomingMovies(): Flow<StatefulData<List<UpcomingMoviesResponse>>>

    fun getTopRatedMovieById(id: Int): Flow<StatefulData<TopRatedMoviesResponse>>
    fun getNowPlayingMovieById(id: Int): Flow<StatefulData<NowPlayingMoviesResponse>>
    fun getPopularMovieById(id: Int): Flow<StatefulData<PopularMoviesResponse>>
    fun getUpcomingMovieById(id: Int): Flow<StatefulData<UpcomingMoviesResponse>>

    fun deleteTopRatedMovies()
    fun deleteNowPlayingMovies()
    fun deletePopularMovies()
    fun deleteUpcomingMovies()
}
