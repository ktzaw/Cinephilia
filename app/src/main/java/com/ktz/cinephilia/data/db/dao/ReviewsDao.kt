package com.ktz.cinephilia.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ktz.cinephilia.data.model.Reviews

@Dao
interface ReviewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReviews(movieReview: Reviews)

    @Query("SELECT * FROM review_table WHERE movieId == :movieId")
    fun getReview(movieId:Int): LiveData<List<Reviews>>

    @Query("DELETE FROM review_table WHERE id==:id")
    fun delete(id: Int)

}