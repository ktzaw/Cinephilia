package com.ktz.cinephilia.data.source.remote.api

import com.ktz.cinephilia.data.models.remote.movies.BaseMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.NowPlayingMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.PopularMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.TopRatedMoviesBaseResponse
import com.ktz.cinephilia.data.models.remote.movies.UpcomingMoviesResponse
import com.ktz.cinephilia.utils.StatefulData
import com.ktz.cinephilia.utils.toStateful
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.takeFrom
import javax.inject.Inject

class MoviesApiImpl @Inject constructor(private val client: HttpClient) : MoviesApi {

    override suspend fun getTopRatedMovies(page: Int): StatefulData<TopRatedMoviesBaseResponse> =
        client.get {
            url {
                takeFrom(TmdbApiConfigs.Movies.TOP_RATED)
            }
            this.parameter(TmdbApiConfigs.Param.PARAM_PAGE, page)
        }
            .body<TopRatedMoviesBaseResponse>()
            .toStateful()

    override suspend fun getNowPlayingMovies(page: Int): StatefulData<BaseMoviesResponse<NowPlayingMoviesResponse>> =
        client.get {
            url {
                takeFrom(TmdbApiConfigs.Movies.NOW_PLAYING)
            }
            this.parameter(TmdbApiConfigs.Param.PARAM_PAGE, page)
        }
            .body<BaseMoviesResponse<NowPlayingMoviesResponse>>()
            .toStateful()

    override suspend fun getPopularMovies(page: Int): StatefulData<BaseMoviesResponse<PopularMoviesResponse>> =
        client.get {
            url {
                takeFrom(TmdbApiConfigs.Movies.POPULAR)
            }
            this.parameter(TmdbApiConfigs.Param.PARAM_PAGE, page)
        }
            .body<BaseMoviesResponse<PopularMoviesResponse>>()
            .toStateful()

    override suspend fun getUpcomingMovies(page: Int): StatefulData<BaseMoviesResponse<UpcomingMoviesResponse>> =
        client.get {
            url {
                takeFrom(TmdbApiConfigs.Movies.UPCOMING)
            }
            this.parameter(TmdbApiConfigs.Param.PARAM_PAGE, page)
        }
            .body<BaseMoviesResponse<UpcomingMoviesResponse>>()
            .toStateful()
}
