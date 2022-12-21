package com.ktz.cinephilia.data.source.local.room.daos.movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ktz.cinephilia.data.models.entities.movies.UpcomingMoviesBaseEntity
import com.ktz.cinephilia.data.models.entities.movies.UpcomingMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingMovies(movies: List<UpcomingMoviesEntity>)

    @Query("SELECT * FROM UpcomingMovies")
    fun getAllUpcomingMovies(): Flow<List<UpcomingMoviesEntity>>

    @Query("SELECT * FROM UpcomingMovies WHERE movieId = :id")
    fun getUpcomingMovieById(id: Int): Flow<UpcomingMoviesEntity>

    @Query("DELETE FROM UpcomingMovies")
    fun deleteAllUpcomingMovies()
}
