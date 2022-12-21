package com.ktz.cinephilia.data.source.local.room.daos.moviesList

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ktz.cinephilia.data.models.entities.moviesList.PopularMoviesListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularMoviesListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(movies: List<PopularMoviesListEntity>)

    @Query("SELECT * FROM popular_movies_list")
    fun getPopularMoviesList(): PagingSource<Int, PopularMoviesListEntity>

    @Query("SELECT * FROM popular_movies_list WHERE movieId = :id")
    fun getPopularMoviesById(id: Int): Flow<PopularMoviesListEntity>

    @Query("DELETE FROM popular_movies_list")
    suspend fun deleteAllPopularMovies()
}
