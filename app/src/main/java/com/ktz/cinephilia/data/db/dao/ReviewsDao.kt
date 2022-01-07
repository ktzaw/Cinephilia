package com.ktz.cinephilia.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ktz.cinephilia.data.model.ReviewResponses
import com.ktz.cinephilia.data.model.ReviewResult

@Dao
interface ReviewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReviews(movieReview: ReviewResult)

    @Query("SELECT * FROM review_table")
    fun getAllReviews(): LiveData<List<ReviewResult>>

    @Query("DELETE FROM review_table WHERE id==:id")
    fun delete(id: Int)

}