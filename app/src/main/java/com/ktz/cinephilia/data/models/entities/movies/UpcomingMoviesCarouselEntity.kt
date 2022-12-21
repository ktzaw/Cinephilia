package com.ktz.cinephilia.data.models.entities.movies

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ktz.cinephilia.data.models.remote.movies.UpcomingMoviesBaseResponse
import com.ktz.cinephilia.data.models.remote.movies.UpcomingMoviesResponse
import com.ktz.cinephilia.ui.screens.fragments.movies.UpcomingMovies
import com.ktz.cinephilia.ui.screens.fragments.movies.UpcomingMoviesBase
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "UpcomingMoviesBase")
@Serializable
data class UpcomingMoviesBaseEntity(
    @Contextual
    @SerialName("dates")
    val dates: Dates? = null,
    @PrimaryKey(autoGenerate = false)
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<UpcomingMoviesEntity> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
) {
    @Serializable
    data class Dates(
        @SerialName("maximum")
        val maximum: String = "",
        @SerialName("minimum")
        val minimum: String = ""
    )
}

@Entity(tableName = "UpcomingMovies")
@Serializable
data class UpcomingMoviesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerialName("adult")
    val adult: Boolean?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("movieId")
    val movieId: Int,
    @SerialName("original_language")
    val originalLanguage: String?,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    val popularity: Double?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("title")
    val title: String,
    @SerialName("video")
    val video: Boolean?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("vote_count")
    val voteCount: Int?
)

fun UpcomingMoviesBaseEntity.toUpcomingMoviesBase(): UpcomingMoviesBase = UpcomingMoviesBase(
    dates = dates?.toUpcomingMoviesBaseDates(),
    page = page,
    results = results.map { it.toUpcomingMovies() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun UpcomingMoviesBaseEntity.Dates.toUpcomingMoviesBaseDates(): UpcomingMoviesBase.Dates = UpcomingMoviesBase.Dates(
    maximum = maximum,
    minimum = minimum
)

fun UpcomingMoviesEntity.toUpcomingMovies(): UpcomingMovies = UpcomingMovies(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds,
    id = movieId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun UpcomingMoviesBaseEntity.toUpcomingMoviesBaseResponse(): UpcomingMoviesBaseResponse = UpcomingMoviesBaseResponse(
    dates = dates?.toUpcomingMoviesBaseResponseDates(),
    page = page,
    results = results.map { it.toUpcomingMoviesResponse() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun UpcomingMoviesBaseEntity.Dates.toUpcomingMoviesBaseResponseDates(): UpcomingMoviesBaseResponse.Dates = UpcomingMoviesBaseResponse.Dates(
    maximum = maximum,
    minimum = minimum
)

fun UpcomingMoviesEntity.toUpcomingMoviesResponse(): UpcomingMoviesResponse = UpcomingMoviesResponse(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds,
    id = movieId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)
