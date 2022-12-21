package com.ktz.cinephilia.data.source.local.room.daos.movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ktz.cinephilia.data.models.entities.movies.TopRatedMoviesBaseEntity
import com.ktz.cinephilia.data.models.entities.movies.TopRatedMoviesEntity
import com.ktz.cinephilia.utils.StatefulData
import kotlinx.coroutines.flow.Flow

@Dao
interface TopRatedMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedMovies(movies: List<TopRatedMoviesEntity>)

    @Query("SELECT * FROM TopRatedMovies")
    fun getAllTopRatedMovies(): Flow<List<TopRatedMoviesEntity>>

    @Query("SELECT * FROM TopRatedMovies WHERE movieId = :id")
    fun getTopRatedMovieById(id: Int): Flow<TopRatedMoviesEntity>

    @Query("DELETE FROM TopRatedMovies")
    fun deleteAllTopRatedMovies()
}
