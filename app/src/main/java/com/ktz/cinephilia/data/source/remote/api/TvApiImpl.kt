package com.ktz.cinephilia.data.source.remote.api

import com.ktz.cinephilia.data.models.remote.tv.BaseTvResponses
import com.ktz.cinephilia.data.models.remote.tv.OnAirResponse
import com.ktz.cinephilia.data.models.remote.tv.PopularTvResponse
import com.ktz.cinephilia.data.models.remote.tv.TopRatedTvResponse
import com.ktz.cinephilia.utils.StatefulData
import com.ktz.cinephilia.utils.toStateful
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.takeFrom
import javax.inject.Inject

class TvApiImpl @Inject constructor(private val client: HttpClient) : TvApi{

    override suspend fun getTopRatedTv(page: Int): StatefulData<BaseTvResponses<TopRatedTvResponse>> =
        client.get {
            url {
                takeFrom(TmdbApiConfigs.TV.TOP_RATED_TV)
            }
            this.parameter(TmdbApiConfigs.Param.PARAM_PAGE, page)
        }
            .body<BaseTvResponses<TopRatedTvResponse>>()
            .toStateful()

    override suspend fun getOnAirTv(page: Int): StatefulData<BaseTvResponses<OnAirResponse>> =
        client.get {
            url {
                takeFrom(TmdbApiConfigs.TV.AIRING_TODAY)
            }
            this.parameter(TmdbApiConfigs.Param.PARAM_PAGE, page)
        }
            .body<BaseTvResponses<OnAirResponse>>()
            .toStateful()

    override suspend fun getPopularTv(page: Int): StatefulData<BaseTvResponses<PopularTvResponse>> =
        client.get {
            url {
                takeFrom(TmdbApiConfigs.TV.POPULAR)
            }
            this.parameter(TmdbApiConfigs.Param.PARAM_PAGE, page)
        }
            .body<BaseTvResponses<PopularTvResponse>>()
            .toStateful()

}