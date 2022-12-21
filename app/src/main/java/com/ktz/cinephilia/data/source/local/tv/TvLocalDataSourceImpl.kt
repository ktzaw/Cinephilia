package com.ktz.cinephilia.data.source.local.tv

import com.ktz.cinephilia.data.models.entities.tv.toOnAirResponse
import com.ktz.cinephilia.data.models.entities.tv.toPopularTvResponse
import com.ktz.cinephilia.data.models.entities.tv.toTopRatedTvResponse
import com.ktz.cinephilia.data.models.remote.tv.OnAirResponse
import com.ktz.cinephilia.data.models.remote.tv.PopularTvResponse
import com.ktz.cinephilia.data.models.remote.tv.TopRatedTvResponse
import com.ktz.cinephilia.data.models.remote.tv.toOnAirEntity
import com.ktz.cinephilia.data.models.remote.tv.toPopularTvEntity
import com.ktz.cinephilia.data.models.remote.tv.toTopRatedTvEntity
import com.ktz.cinephilia.data.source.local.room.daos.tv.OnAirTvDao
import com.ktz.cinephilia.data.source.local.room.daos.tv.PopularTvDao
import com.ktz.cinephilia.data.source.local.room.daos.tv.TopRatedTvDao
import com.ktz.cinephilia.utils.StatefulData
import com.ktz.cinephilia.utils.asStatefulData
import com.ktz.cinephilia.utils.mapList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TvLocalDataSourceImpl @Inject constructor(
    private val topRatedTvDao: TopRatedTvDao,
    private val onAirTvDao: OnAirTvDao,
    private val popularTvDao: PopularTvDao
) : TvLocalDataSource {

    override fun saveAllTopRatedTv(tvList: List<TopRatedTvResponse>) {
        topRatedTvDao.insertTopRatedTv(
            tvList.map {
                it.toTopRatedTvEntity()
            }
        )
    }

    override fun saveAllOnAirTv(tvList: List<OnAirResponse>) {
        onAirTvDao.insertOnAirTv(
            tvList.map {
                it.toOnAirEntity()
            }
        )
    }

    override fun saveAllPopularTv(tvList: List<PopularTvResponse>) {
        popularTvDao.insertPopularTv(
            tvList.map {
                it.toPopularTvEntity()
            }
        )
    }

    override fun getAllTopRatedTv(): Flow<StatefulData<List<TopRatedTvResponse>>> =
        topRatedTvDao.getAllTopRatedTv().mapList {
            it.toTopRatedTvResponse()
        }
            .asStatefulData()

    override fun getAllOnAirTv(): Flow<StatefulData<List<OnAirResponse>>> =
        onAirTvDao.getAllOnAirTv().mapList {
            it.toOnAirResponse()
        }
            .asStatefulData()

    override fun getAllPopularTv(): Flow<StatefulData<List<PopularTvResponse>>> =
        popularTvDao.getAllPopularTv().mapList {
            it.toPopularTvResponse()
        }
            .asStatefulData()

    override fun getTopRatedTvById(id: Int): Flow<StatefulData<TopRatedTvResponse>> =
        topRatedTvDao.getTopRatedTvById(id).map {
            it.toTopRatedTvResponse()
        }
            .asStatefulData()

    override fun getOnAirTvById(id: Int): Flow<StatefulData<OnAirResponse>> =
        onAirTvDao.getOnAirTvById(id).map {
            it.toOnAirResponse()
        }
            .asStatefulData()

    override fun getPopularTvById(id: Int): Flow<StatefulData<PopularTvResponse>> =
        popularTvDao.getPopularTvById(id).map {
            it.toPopularTvResponse()
        }
            .asStatefulData()

    override fun deleteTopRatedTv() {
        topRatedTvDao.deleteAllTopRatedTv()
    }

    override fun deleteOnAirTv() {
        onAirTvDao.deleteAllOnAirTv()
    }

    override fun deletePopularTv() {
        popularTvDao.deleteAllPopularTv()
    }
}
