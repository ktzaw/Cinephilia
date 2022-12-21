package com.ktz.cinephilia.data.source.remote.movies

import com.ktz.cinephilia.data.models.remote.movies.NowPlayingMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.PopularMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.TopRatedMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.UpcomingMoviesResponse
import com.ktz.cinephilia.utils.StatefulData
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteDataSource {
    fun getTopRatedMovies(page: Int): Flow<StatefulData<List<TopRatedMoviesResponse>>>
    fun getNowPlayingMovies(page: Int): Flow<StatefulData<List<NowPlayingMoviesResponse>>>
    fun getPopularMovies(page: Int): Flow<StatefulData<List<PopularMoviesResponse>>>
    fun getUpcomingMovies(page: Int): Flow<StatefulData<List<UpcomingMoviesResponse>>>
}
