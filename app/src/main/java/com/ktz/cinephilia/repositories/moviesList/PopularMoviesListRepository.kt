package com.ktz.cinephilia.repositories.moviesList

import androidx.paging.PagingData
import com.ktz.cinephilia.data.models.entities.moviesList.PopularMoviesListEntity
import kotlinx.coroutines.flow.Flow

interface PopularMoviesListRepository {
    fun getPopularMoviesList(): Flow<PagingData<PopularMoviesListEntity>>
}