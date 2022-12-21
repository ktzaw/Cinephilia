package com.ktz.cinephilia.repositories.moviesList

import androidx.paging.PagingData
import com.ktz.cinephilia.data.models.entities.moviesList.NowPlayingMoviesListEntity
import kotlinx.coroutines.flow.Flow

interface NowPlayingMoviesListRepository {
    fun getNowPlayingMoviesList(): Flow<PagingData<NowPlayingMoviesListEntity>>
}
