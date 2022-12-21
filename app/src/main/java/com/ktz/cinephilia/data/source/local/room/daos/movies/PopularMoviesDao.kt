package com.ktz.cinephilia.data.source.local.room.daos.movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ktz.cinephilia.data.models.entities.movies.PopularMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(movies: List<PopularMoviesEntity>)

    @Query("SELECT * FROM PopularMovies")
    fun getAllPopularMovies(): Flow<List<PopularMoviesEntity>>

    @Query("SELECT * FROM PopularMovies WHERE movieId = :id")
    fun getPopularMovieById(id: Int): Flow<PopularMoviesEntity>

    @Query("DELETE FROM PopularMovies")
    fun deleteAllPopularMovies()
}
