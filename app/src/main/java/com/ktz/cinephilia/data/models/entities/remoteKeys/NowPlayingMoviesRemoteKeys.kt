package com.ktz.cinephilia.data.models.entities.remoteKeys

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "now_playing_movies_remote_keys")
@Serializable
data class NowPlayingMoviesRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
