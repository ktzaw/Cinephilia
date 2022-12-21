package com.ktz.cinephilia.data.source.local.room.daos.remoteKeysDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ktz.cinephilia.data.models.entities.remoteKeys.NowPlayingMoviesRemoteKeys

@Dao
interface NowPlayingMoviesListRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<NowPlayingMoviesRemoteKeys>)

    @Query("SELECT * FROM now_playing_movies_remote_keys WHERE id = :movieId")
    suspend fun getRemoteKeysByMovieId(movieId: Int): NowPlayingMoviesRemoteKeys?

    @Query("DELETE FROM now_playing_movies_remote_keys")
    suspend fun deleteAllRemoteKeys()
}