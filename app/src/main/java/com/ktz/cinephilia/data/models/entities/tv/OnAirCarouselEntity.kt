package com.ktz.cinephilia.data.models.entities.tv

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ktz.cinephilia.data.models.remote.tv.OnAirResponse
import com.ktz.cinephilia.ui.screens.fragments.tv.OnAirTvCarousel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Entity(tableName = "OnAirTv")
@Serializable
data class OnAirEntity(
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("first_air_date")
    val firstAirDate: String = "",
    @SerialName("genre_ids")
    val genreIds: List<Int> = listOf(),
    @PrimaryKey(autoGenerate = false)
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("origin_country")
    val originCountry: List<String> = listOf(),
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("original_name")
    val originalName: String = "",
    @SerialName("overview")
    val overview: String = "",
    @SerialName("popularity")
    val popularity: Double = 0.0,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0
)

fun OnAirEntity.toOnAir(): OnAirTvCarousel = OnAirTvCarousel(
    backdropPath = backdropPath,
    firstAirDate = firstAirDate,
    genreIds = genreIds,
    id = id,
    name = name,
    originCountry = originCountry,
    originalLanguage = originalLanguage,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun OnAirEntity.toOnAirResponse(): OnAirResponse = OnAirResponse(
    backdropPath = backdropPath,
    firstAirDate = firstAirDate,
    genreIds = genreIds,
    id = id,
    name = name,
    originCountry = originCountry,
    originalLanguage = originalLanguage,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount
)
