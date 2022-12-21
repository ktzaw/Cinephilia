package com.ktz.cinephilia.data.source.local.room.daos.moviesList

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ktz.cinephilia.data.models.entities.moviesList.NowPlayingMoviesListEntity
import com.ktz.cinephilia.data.models.remote.movies.NowPlayingMoviesResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface NowPlayingMoviesListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlayingMovies(movies: List<NowPlayingMoviesListEntity>)

    @Query("SELECT * FROM now_playing_movies_list")
    fun getNowPlayingMoviesList(): PagingSource<Int, NowPlayingMoviesListEntity>

    @Query("SELECT * FROM now_playing_movies_list WHERE movieId = :id")
    fun getNowPlayingMovieById(id: Int): Flow<NowPlayingMoviesListEntity>

    @Query("DELETE FROM now_playing_movies_list")
    suspend fun deleteAllNowPlayingMovies()
}
