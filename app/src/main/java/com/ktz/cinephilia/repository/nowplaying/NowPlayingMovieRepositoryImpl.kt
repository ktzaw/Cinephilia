package com.ktz.cinephilia.repository.nowplaying

import com.ktz.cinephilia.data.model.MovieResponse
import com.ktz.cinephilia.data.model.NowPlaying
import com.ktz.cinephilia.network.ApiService
import com.ktz.cinephilia.utils.API_KEY
import io.reactivex.Observable
import javax.inject.Inject

class NowPlayingMovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : NowPlayingMoviesRepository {

    override fun loadNowPlayingMovies(
        apiKey: String,
        page: Int
    ): Observable<MovieResponse<NowPlaying>> {
        return apiService.loadNowPlayingMovies(API_KEY, page)
    }


}