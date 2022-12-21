package com.ktz.cinephilia.data.source.remote.api

import com.ktz.cinephilia.data.models.remote.tv.BaseTvResponses
import com.ktz.cinephilia.data.models.remote.tv.OnAirResponse
import com.ktz.cinephilia.data.models.remote.tv.PopularTvResponse
import com.ktz.cinephilia.data.models.remote.tv.TopRatedTvResponse
import com.ktz.cinephilia.utils.StatefulData
import io.ktor.client.request.get

interface TvApi {

    suspend fun getTopRatedTv(page: Int): StatefulData<BaseTvResponses<TopRatedTvResponse>>
    suspend fun getOnAirTv(page: Int): StatefulData<BaseTvResponses<OnAirResponse>>
    suspend fun getPopularTv(page: Int): StatefulData<BaseTvResponses<PopularTvResponse>>
}
