package com.ktz.cinephilia.data.source.remote.movies

import com.ktz.cinephilia.data.models.remote.movies.NowPlayingMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.PopularMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.TopRatedMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.UpcomingMoviesResponse
import com.ktz.cinephilia.data.source.remote.api.MoviesApi
import com.ktz.cinephilia.utils.StatefulData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesRemoteDataSource {

    override fun getTopRatedMovies(page: Int): Flow<StatefulData<List<TopRatedMoviesResponse>>> =
        channelFlow {
            try {
                send(StatefulData.Loading)
                send(
                    moviesApi.getTopRatedMovies(page).map {
                        it.results
                    }
                )
                close()
            } catch (e: Exception) {
                e.localizedMessage?.let { send(StatefulData.Error(it)) }
            }
        }

    override fun getNowPlayingMovies(page: Int): Flow<StatefulData<List<NowPlayingMoviesResponse>>> =
        channelFlow {
            try {
                send(StatefulData.Loading)
                send(
                    moviesApi.getNowPlayingMovies(page).map {
                        it.results
                    }
                )
                close()
            } catch (e: Exception) {
                e.localizedMessage?.let { send(StatefulData.Error(it)) }
            }
        }

    override fun getPopularMovies(page: Int): Flow<StatefulData<List<PopularMoviesResponse>>> =
        channelFlow {
            try {
                send(StatefulData.Loading)
                send(
                    moviesApi.getPopularMovies(page).map {
                        it.results
                    }
                )
                close()
            } catch (e: Exception) {
                e.localizedMessage?.let { send(StatefulData.Error(it)) }
            }
        }

    override fun getUpcomingMovies(page: Int): Flow<StatefulData<List<UpcomingMoviesResponse>>> =
        channelFlow {
            try {
                send(StatefulData.Loading)
                send(
                    moviesApi.getUpcomingMovies(page).map {
                        it.results
                    }
                )
                close()
            } catch (e: Exception) {
                e.localizedMessage?.let { send(StatefulData.Error(it)) }
            }
        }
}
