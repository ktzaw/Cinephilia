package com.ktz.cinephilia.data.source.local.room.daos.movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ktz.cinephilia.data.models.entities.movies.NowPlayingMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NowPlayingMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNowPlayingMovies(movies: List<NowPlayingMoviesEntity>)

    @Query("SELECT * FROM NowPlayingMovies")
    fun getAllNowPlayingMovies(): Flow<List<NowPlayingMoviesEntity>>

    @Query("SELECT * FROM NowPlayingMovies WHERE movieId = :id")
    fun getNowPlayingMovieById(id: Int): Flow<NowPlayingMoviesEntity>

    @Query("DELETE FROM NowPlayingMovies")
    fun deleteAllNowPlayingMovies()
}
