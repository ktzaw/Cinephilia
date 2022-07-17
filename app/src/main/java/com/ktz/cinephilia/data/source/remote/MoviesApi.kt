package com.ktz.cinephilia.data.source.remote

import com.ktz.cinephilia.BuildConfig
import com.ktz.cinephilia.data.domain.MovieResponses
import com.ktz.cinephilia.data.domain.NowPlayingMovies
import com.ktz.cinephilia.data.domain.PopularMovies
import com.ktz.cinephilia.data.domain.TopRatedMoviesResponse
import com.ktz.cinephilia.data.domain.UpcomingMovies
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import io.ktor.http.takeFrom
import javax.inject.Inject

class MoviesApi @Inject constructor(private val client: HttpClient) {

    suspend fun getTopRatedMovies(page: Int): TopRatedMoviesResponse =
        client.get {
            endPontNowPlayingMovies(TmdbApiConfigs.Movies.TOP_RATED)
            this.parameter(TmdbApiConfigs.Param.PARAM_API_KEY, BuildConfig.API_KEY)
            this.parameter(TmdbApiConfigs.Param.PARAM_PAGE, page)
        }.body()

    suspend fun getNowPlayingMovies(page: Int): MovieResponses<NowPlayingMovies> =
        client.get {
            endPontNowPlayingMovies(TmdbApiConfigs.Movies.NOW_PLAYING)
            this.parameter(TmdbApiConfigs.Param.PARAM_API_KEY, BuildConfig.API_KEY)
            this.parameter(TmdbApiConfigs.Param.PARAM_PAGE, page)
        }.body()

    suspend fun getPopularMovies(page: Int): MovieResponses<PopularMovies> =
        client.get {
            endPontNowPlayingMovies(TmdbApiConfigs.Movies.POPULAR)
            this.parameter(TmdbApiConfigs.Param.PARAM_API_KEY, BuildConfig.API_KEY)
            this.parameter(TmdbApiConfigs.Param.PARAM_PAGE, page)
        }.body()

    suspend fun getUpcomingMovies(page: Int): MovieResponses<UpcomingMovies> =
        client.get {
            endPontNowPlayingMovies(TmdbApiConfigs.Movies.UPCOMING)
            this.parameter(TmdbApiConfigs.Param.PARAM_API_KEY, BuildConfig.API_KEY)
            this.parameter(TmdbApiConfigs.Param.PARAM_PAGE, page)
        }.body()

    private fun HttpRequestBuilder.endPontNowPlayingMovies(vararg path: String) {
        url {
            takeFrom(TmdbApiConfigs.Base.BASE_URL)
            path(TmdbApiConfigs.Base.API_VERSION, *path)
        }
    }
}
