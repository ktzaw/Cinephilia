package com.ktz.cinephilia.repositories.moviesList

import androidx.paging.PagingData
import com.ktz.cinephilia.data.models.entities.moviesList.PopularMoviesListEntity
import com.ktz.cinephilia.data.models.entities.moviesList.UpcomingMoviesListEntity
import kotlinx.coroutines.flow.Flow

interface UpcomingMoviesListRepository{
    fun getUpcomingMoviesList(): Flow<PagingData<UpcomingMoviesListEntity>>
}
