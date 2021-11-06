package com.ktz.cinephilia.repository.nowplaying

import androidx.paging.PagingData
import com.ktz.cinephilia.data.model.Movies
import kotlinx.coroutines.flow.Flow

interface NowPlayingRepository {

    fun getNowPlayingMovies():Flow<PagingData<Movies>>

}