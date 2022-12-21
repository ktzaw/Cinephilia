package com.ktz.cinephilia.data.source.local.room.daos.tv

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ktz.cinephilia.data.models.entities.tv.TopRatedTvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopRatedTvDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedTv(tvList: List<TopRatedTvEntity>)

    @Query("SELECT * FROM TopRatedTv")
    fun getAllTopRatedTv(): Flow<List<TopRatedTvEntity>>

    @Query("SELECT * FROM TopRatedTv WHERE id = :id")
    fun getTopRatedTvById(id: Int): Flow<TopRatedTvEntity>

    @Query("DELETE FROM TopRatedTv")
    fun deleteAllTopRatedTv()
}
