package com.ktz.cinephilia.repositories

import com.ktz.cinephilia.data.domain.MovieResponses
import com.ktz.cinephilia.data.domain.NowPlayingMovies
import com.ktz.cinephilia.data.domain.PopularMovies
import com.ktz.cinephilia.data.domain.TopRatedMoviesResponse
import com.ktz.cinephilia.data.domain.UpcomingMovies
import com.ktz.cinephilia.data.source.remote.MoviesApi
import com.ktz.cinephilia.utils.StatefulData
import com.ktz.cinephilia.utils.asStatefulData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesRepository {

    override fun getNowPlayingMovies(page: Int): Flow<StatefulData<MovieResponses<NowPlayingMovies>>> {
        val responseFlow = flow {
            emit(moviesApi.getNowPlayingMovies(page))
        }.flowOn(Dispatchers.IO)

        return responseFlow.asStatefulData()
    }

    override fun getPopularMovies(page: Int): Flow<StatefulData<MovieResponses<PopularMovies>>> {
        val responseFlow = flow {
            emit(moviesApi.getPopularMovies(page))
        }.flowOn(Dispatchers.IO)

        return responseFlow.asStatefulData()
    }

    override fun getUpcomingMovies(page: Int): Flow<StatefulData<MovieResponses<UpcomingMovies>>> {
        val responseFlow = flow {
            emit(moviesApi.getUpcomingMovies(page))
        }.flowOn(Dispatchers.IO)

        return responseFlow.asStatefulData()
    }

    override fun getTopRatedMovies(page: Int): Flow<StatefulData<TopRatedMoviesResponse>> {
        val responseFlow = flow {
            emit(moviesApi.getTopRatedMovies(page))
        }.flowOn(Dispatchers.IO)

        return responseFlow.asStatefulData()
    }

//    override fun getGenreList(): Flow<StatefulData<GenreListResponse>> {
//        val responseFlow = flow {
//            emit(tmdbApi.getGenreList(BuildConfig.API_KEY, "en-US"))
//        }
//
//        return responseFlow.asStatefulData()
//    }
}
