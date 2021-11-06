package com.ktz.cinephilia.repository.upcoming

import androidx.paging.PagingData
import com.ktz.cinephilia.data.model.Movies
import kotlinx.coroutines.flow.Flow

interface UpcomingRepository {

    fun getUpcomingMovies():Flow<PagingData<Movies>>

}