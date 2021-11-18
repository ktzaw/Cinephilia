package com.ktz.cinephilia.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ktz.cinephilia.data.model.MovieDetail

@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteMovie(movieDetail: MovieDetail)

    @Query("SELECT * FROM favourite_table ORDER BY title ASC")
    fun getAllMovies(): LiveData<List<MovieDetail>>

    @Query("SELECT * FROM favourite_table WHERE id=:movieId")
    fun getSpecificMovie(movieId: String): MovieDetail

    @Query("SELECT EXISTS(SELECT * FROM favourite_table WHERE id = :id)")
    fun isFavourite(id: Int): Boolean

    @Query("DELETE FROM favourite_table WHERE id==:id")
    fun delete(id:Int)

}