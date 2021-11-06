package com.ktz.cinephilia.repository.nowplaying

import com.ktz.cinephilia.data.model.MovieResponse
import com.ktz.cinephilia.data.model.NowPlaying
import com.ktz.cinephilia.network.ApiService
import io.reactivex.Observable
import javax.inject.Inject

interface NowPlayingMoviesRepository {

    fun loadNowPlayingMovies(apiKey:String,page:Int):Observable<MovieResponse<NowPlaying>>

}