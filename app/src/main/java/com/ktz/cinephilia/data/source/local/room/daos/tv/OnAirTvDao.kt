package com.ktz.cinephilia.data.source.local.room.daos.tv

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ktz.cinephilia.data.models.entities.tv.OnAirEntity
import com.ktz.cinephilia.utils.StatefulData
import kotlinx.coroutines.flow.Flow

@Dao
interface OnAirTvDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOnAirTv(tvList: List<OnAirEntity>)

    @Query("SELECT * FROM OnAirTv")
    fun getAllOnAirTv(): Flow<List<OnAirEntity>>

    @Query("SELECT * FROM OnAirTv WHERE id = :id")
    fun getOnAirTvById(id: Int): Flow<OnAirEntity>

    @Query("DELETE FROM OnAirTv")
    fun deleteAllOnAirTv()
}