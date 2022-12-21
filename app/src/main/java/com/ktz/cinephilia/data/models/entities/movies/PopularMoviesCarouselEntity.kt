package com.ktz.cinephilia.data.models.entities.movies

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ktz.cinephilia.data.models.remote.movies.PopularMoviesBaseResponse
import com.ktz.cinephilia.data.models.remote.movies.PopularMoviesResponse
import com.ktz.cinephilia.ui.screens.fragments.movies.PopularMovies
import com.ktz.cinephilia.ui.screens.fragments.movies.PopularMoviesBase
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "PopularMoviesBase")
@Serializable
data class PopularMoviesBaseEntity(
    @Contextual
    @SerialName("dates")
    val dates: Dates? = null,
    @PrimaryKey(autoGenerate = false)
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<PopularMoviesEntity> = listOf(),
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

@Entity(tableName = "PopularMovies")
@Serializable
data class PopularMoviesEntity(
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

fun PopularMoviesBaseEntity.toPopularMoviesBase(): PopularMoviesBase = PopularMoviesBase(
    dates = dates?.toPopularMoviesBaseDates(),
    page = page,
    results = results.map { it.toPopularMovies() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun PopularMoviesBaseEntity.Dates.toPopularMoviesBaseDates(): PopularMoviesBase.Dates = PopularMoviesBase.Dates(
    maximum = maximum,
    minimum = minimum
)

fun PopularMoviesEntity.toPopularMovies(): PopularMovies = PopularMovies(
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

fun PopularMoviesBaseEntity.toPopularMoviesBaseResponse(): PopularMoviesBaseResponse = PopularMoviesBaseResponse(
    dates = dates?.toPopularMoviesBaseResponseDates(),
    page = page,
    results = results.map { it.toPopularMoviesResponse() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun PopularMoviesBaseEntity.Dates.toPopularMoviesBaseResponseDates(): PopularMoviesBaseResponse.Dates = PopularMoviesBaseResponse.Dates(
    maximum = maximum,
    minimum = minimum
)

fun PopularMoviesEntity.toPopularMoviesResponse(): PopularMoviesResponse = PopularMoviesResponse(
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
