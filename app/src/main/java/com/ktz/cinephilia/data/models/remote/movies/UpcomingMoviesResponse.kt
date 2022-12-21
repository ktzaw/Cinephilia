package com.ktz.cinephilia.data.models.remote.movies

import com.ktz.cinephilia.data.models.entities.movies.UpcomingMoviesBaseEntity
import com.ktz.cinephilia.data.models.entities.movies.UpcomingMoviesEntity
import com.ktz.cinephilia.data.models.entities.moviesList.NowPlayingMoviesListEntity
import com.ktz.cinephilia.data.models.entities.moviesList.UpcomingMoviesListEntity
import com.ktz.cinephilia.ui.screens.fragments.movies.UpcomingMovies
import com.ktz.cinephilia.ui.screens.fragments.movies.UpcomingMoviesBase
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpcomingMoviesBaseResponse(
    @Contextual
    @SerialName("dates")
    val dates: Dates? = null,
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<UpcomingMoviesResponse> = listOf(),
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

@Serializable
data class UpcomingMoviesResponse(
    @SerialName("adult")
    val adult: Boolean? = false,
    @SerialName("backdrop_path")
    val backdropPath: String? = "",
    @SerialName("genre_ids")
    val genreIds: List<Int> = listOf(),
    @SerialName("id")
    val id: Int = 0,
    @SerialName("original_language")
    val originalLanguage: String? = "",
    @SerialName("original_title")
    val originalTitle: String = "",
    @SerialName("overview")
    val overview: String = "",
    @SerialName("popularity")
    val popularity: Double? = 0.0,
    @SerialName("poster_path")
    val posterPath: String? = "",
    @SerialName("release_date")
    val releaseDate: String? = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("video")
    val video: Boolean? = false,
    @SerialName("vote_average")
    val voteAverage: Double? = 0.0,
    @SerialName("vote_count")
    val voteCount: Int? = 0
)

fun UpcomingMoviesBaseResponse.toUpcomingMoviesBase(): UpcomingMoviesBase = UpcomingMoviesBase(
    dates = dates?.toUpcomingMoviesBaseDates(),
    page = page,
    results = results.map { it.toUpcomingMovies() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun UpcomingMoviesBaseResponse.Dates.toUpcomingMoviesBaseDates(): UpcomingMoviesBase.Dates = UpcomingMoviesBase.Dates(
    maximum = maximum,
    minimum = minimum
)

fun UpcomingMoviesResponse.toUpcomingMovies(): UpcomingMovies = UpcomingMovies(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds,
    id = id,
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

fun UpcomingMoviesBaseResponse.toUpcomingMoviesBaseEntity(): UpcomingMoviesBaseEntity = UpcomingMoviesBaseEntity(
    dates = dates?.toUpcomingMoviesBaseEntityDates(),
    page = page,
    results = results.map { it.toUpcomingMoviesEntity() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun UpcomingMoviesBaseResponse.Dates.toUpcomingMoviesBaseEntityDates(): UpcomingMoviesBaseEntity.Dates = UpcomingMoviesBaseEntity.Dates(
    maximum = maximum,
    minimum = minimum
)

fun UpcomingMoviesResponse.toUpcomingMoviesEntity(): UpcomingMoviesEntity = UpcomingMoviesEntity(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds,
    movieId = id,
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

fun UpcomingMoviesResponse.toUpcomingMoviesListEntity(): UpcomingMoviesListEntity = UpcomingMoviesListEntity(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds,
    movieId = id,
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
