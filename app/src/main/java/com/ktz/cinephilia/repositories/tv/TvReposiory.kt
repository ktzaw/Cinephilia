package com.ktz.cinephilia.repositories.tv

import com.ktz.cinephilia.data.models.remote.tv.OnAirResponse
import com.ktz.cinephilia.data.models.remote.tv.PopularTvResponse
import com.ktz.cinephilia.data.models.remote.tv.TopRatedTvResponse
import com.ktz.cinephilia.utils.StatefulData
import kotlinx.coroutines.flow.Flow

interface TvReposiory {

    fun getTopRatedTv(page: Int): Flow<StatefulData<List<TopRatedTvResponse>>>
    fun getOnAirTv(page: Int): Flow<StatefulData<List<OnAirResponse>>>
    fun getPopularTv(page: Int): Flow<StatefulData<List<PopularTvResponse>>>
}
