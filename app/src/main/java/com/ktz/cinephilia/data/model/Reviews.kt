package com.ktz.cinephilia.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "review_table")
data class Reviews(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    var content: String,
    @SerializedName("created_at")
    val createdAt: String,
    @PrimaryKey(autoGenerate = false)
    var movieId: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("url")
    val url: String
)