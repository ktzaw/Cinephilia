package com.ktz.cinephilia.data.domain
import com.google.gson.annotations.SerializedName

data class GenreListResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
