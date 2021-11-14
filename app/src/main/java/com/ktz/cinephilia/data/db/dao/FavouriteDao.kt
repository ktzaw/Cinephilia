package com.ktz.cinephilia.data.db.dao

import androidx.room.*
import com.ktz.cinephilia.data.model.MovieDetail

@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteMovie(movieDetail: MovieDetail)

    @Query("SELECT * FROM favourite_table")
    fun getAllMovies(): List<MovieDetail>

//    @Query("SELECT 1 FROM favourite_table WHERE id=:movieId")
//    fun getSpecificMovie(movieId: String): MovieDetail

    @Query("SELECT EXISTS(SELECT * FROM favourite_table WHERE id = :id)")
    fun isFavourite(id: Int): Boolean

    @Query("DELETE FROM favourite_table WHERE id==:id")
    fun delete(id:Int)

}