package com.ktz.cinephilia.data.source.local.room.daos.tv

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ktz.cinephilia.data.models.entities.movies.PopularMoviesEntity
import com.ktz.cinephilia.data.models.entities.tv.PopularTvEntity
import com.ktz.cinephilia.utils.StatefulData
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularTvDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularTv(tvList: List<PopularTvEntity>)

    @Query("SELECT * FROM PopularTv")
    fun getAllPopularTv(): Flow<List<PopularTvEntity>>

    @Query("SELECT * FROM PopularTv WHERE id = :id")
    fun getPopularTvById(id: Int): Flow<PopularTvEntity>

    @Query("DELETE FROM PopularTv")
    fun deleteAllPopularTv()
}