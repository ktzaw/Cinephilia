package com.ktz.cinephilia.repositories.tv

import com.ktz.cinephilia.data.models.remote.tv.OnAirResponse
import com.ktz.cinephilia.data.models.remote.tv.PopularTvResponse
import com.ktz.cinephilia.data.models.remote.tv.TopRatedTvResponse
import com.ktz.cinephilia.data.source.local.tv.TvLocalDataSource
import com.ktz.cinephilia.data.source.remote.tv.TvRemoteDataSource
import com.ktz.cinephilia.utils.StatefulData
import com.ktz.cinephilia.utils.onSuccessState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@OptIn(FlowPreview::class)
class TvRepositoryImpl @Inject constructor(
    private val localDataSource: TvLocalDataSource,
    private val remoteDataSource: TvRemoteDataSource
) : TvReposiory {

    override fun getTopRatedTv(page: Int): Flow<StatefulData<List<TopRatedTvResponse>>> =
        remoteDataSource.getTopRatedTv(page)
            .onSuccessState { localDataSource.saveAllTopRatedTv(it) }
            .flatMapMerge { localDataSource.getAllTopRatedTv() }
            .onStart { StatefulData.Loading }
            .flowOn(Dispatchers.IO)

    override fun getOnAirTv(page: Int): Flow<StatefulData<List<OnAirResponse>>> =
        remoteDataSource.getOnAirTv(page)
            .onSuccessState { localDataSource.saveAllOnAirTv(it) }
            .flatMapMerge { localDataSource.getAllOnAirTv() }
            .onStart { StatefulData.Loading }
            .flowOn(Dispatchers.IO)

    override fun getPopularTv(page: Int): Flow<StatefulData<List<PopularTvResponse>>> =
        remoteDataSource.getPopularTv(page)
            .onSuccessState { localDataSource.saveAllPopularTv(it) }
            .flatMapMerge { localDataSource.getAllPopularTv() }
            .onStart { StatefulData.Loading }
            .flowOn(Dispatchers.IO)
}
