package com.ktz.cinephilia.data.source.local.tv

import com.ktz.cinephilia.data.models.remote.tv.OnAirResponse
import com.ktz.cinephilia.data.models.remote.tv.PopularTvResponse
import com.ktz.cinephilia.data.models.remote.tv.TopRatedTvResponse
import com.ktz.cinephilia.utils.StatefulData
import kotlinx.coroutines.flow.Flow

interface TvLocalDataSource {

    fun saveAllTopRatedTv(tvList: List<TopRatedTvResponse>)
    fun saveAllOnAirTv(tvList: List<OnAirResponse>)
    fun saveAllPopularTv(tvList: List<PopularTvResponse>)

    fun getAllTopRatedTv(): Flow<StatefulData<List<TopRatedTvResponse>>>
    fun getAllOnAirTv(): Flow<StatefulData<List<OnAirResponse>>>
    fun getAllPopularTv(): Flow<StatefulData<List<PopularTvResponse>>>

    fun getTopRatedTvById(id: Int): Flow<StatefulData<TopRatedTvResponse>>
    fun getOnAirTvById(id: Int): Flow<StatefulData<OnAirResponse>>
    fun getPopularTvById(id: Int): Flow<StatefulData<PopularTvResponse>>

    fun deleteTopRatedTv()
    fun deleteOnAirTv()
    fun deletePopularTv()
}
