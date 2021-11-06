package com.ktz.cinephilia.repository.popular

import androidx.paging.PagingData
import com.ktz.cinephilia.data.model.Movies
import kotlinx.coroutines.flow.Flow

interface PopularRepository {

    fun getPopularMovies():Flow<PagingData<Movies>>

}