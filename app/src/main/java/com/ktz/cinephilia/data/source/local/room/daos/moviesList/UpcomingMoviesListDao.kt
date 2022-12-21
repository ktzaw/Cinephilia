package com.ktz.cinephilia.data.source.local.room.daos.moviesList

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ktz.cinephilia.data.models.entities.moviesList.NowPlayingMoviesListEntity
import com.ktz.cinephilia.data.models.entities.moviesList.UpcomingMoviesListEntity
import com.ktz.cinephilia.data.models.remote.movies.NowPlayingMoviesResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingMoviesListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcomingMovies(movies: List<UpcomingMoviesListEntity>)

    @Query("SELECT * FROM upcoming_movies_list")
    fun getUpcomingMoviesList(): PagingSource<Int, UpcomingMoviesListEntity>

    @Query("SELECT * FROM upcoming_movies_list WHERE movieId = :id")
    fun getUpcomingMoviesById(id: Int): Flow<UpcomingMoviesListEntity>

    @Query("DELETE FROM upcoming_movies_list")
    suspend fun deleteAllUpcomingMovies()
}
