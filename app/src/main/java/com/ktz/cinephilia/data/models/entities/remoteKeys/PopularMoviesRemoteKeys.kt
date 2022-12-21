package com.ktz.cinephilia.data.models.entities.remoteKeys

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "popular_movies_remote_keys")
@Serializable
data class PopularMoviesRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
