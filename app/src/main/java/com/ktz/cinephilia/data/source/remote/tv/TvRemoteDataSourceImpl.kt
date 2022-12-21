package com.ktz.cinephilia.data.source.remote.tv

import com.ktz.cinephilia.data.models.remote.tv.OnAirResponse
import com.ktz.cinephilia.data.models.remote.tv.PopularTvResponse
import com.ktz.cinephilia.data.models.remote.tv.TopRatedTvResponse
import com.ktz.cinephilia.data.source.remote.api.TvApi
import com.ktz.cinephilia.utils.StatefulData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class TvRemoteDataSourceImpl @Inject constructor(
    private val tvApi: TvApi
) : TvRemoteDataSource {

    override fun getTopRatedTv(page: Int): Flow<StatefulData<List<TopRatedTvResponse>>> =
        channelFlow {
            try {
                send(StatefulData.Loading)
                send(
                    tvApi.getTopRatedTv(page).map {
                        it.results
                    }
                )
            } catch (e: Exception) {
                e.localizedMessage?.let { send(StatefulData.Error(it)) }
            }
        }

    override fun getOnAirTv(page: Int): Flow<StatefulData<List<OnAirResponse>>>  =
        channelFlow {
            try {
                send(StatefulData.Loading)
                send(
                    tvApi.getOnAirTv(page).map {
                        it.results
                    }
                )
            } catch (e: Exception) {
                e.localizedMessage?.let { send(StatefulData.Error(it)) }
            }
        }

    override fun getPopularTv(page: Int): Flow<StatefulData<List<PopularTvResponse>>> =
        channelFlow {
            try {
                send(StatefulData.Loading)
                send(
                    tvApi.getPopularTv(page).map {
                        it.results
                    }
                )
            } catch (e: Exception) {
                e.localizedMessage?.let { send(StatefulData.Error(it)) }
            }
        }
}
