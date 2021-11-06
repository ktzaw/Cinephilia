package com.ktz.cinephilia.network

import com.ktz.cinephilia.data.model.MovieResponse
import com.ktz.cinephilia.data.model.NowPlaying
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    fun loadNowPlayingMovies(
        @Query("api_key") apiKey: String, @Query("page") page: Int
    ): Observable<MovieResponse<NowPlaying>>

}