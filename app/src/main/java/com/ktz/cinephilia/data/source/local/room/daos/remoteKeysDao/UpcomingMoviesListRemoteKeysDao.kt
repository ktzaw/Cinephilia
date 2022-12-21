package com.ktz.cinephilia.data.source.local.room.daos.remoteKeysDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ktz.cinephilia.data.models.entities.remoteKeys.NowPlayingMoviesRemoteKeys
import com.ktz.cinephilia.data.models.entities.remoteKeys.UpcomingMoviesRemoteKeys

@Dao
interface UpcomingMoviesListRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<UpcomingMoviesRemoteKeys>)

    @Query("SELECT * FROM upcoming_movies_remote_keys WHERE id = :movieId")
    suspend fun getRemoteKeysByMovieId(movieId: Int): UpcomingMoviesRemoteKeys?

    @Query("DELETE FROM upcoming_movies_remote_keys")
    suspend fun deleteAllRemoteKeys()
}