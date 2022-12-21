package com.ktz.cinephilia.data.models.remote.movies

import com.ktz.cinephilia.data.models.entities.movies.NowPlayingMoviesBaseEntity
import com.ktz.cinephilia.data.models.entities.movies.NowPlayingMoviesEntity
import com.ktz.cinephilia.data.models.entities.moviesList.NowPlayingMoviesListEntity
import com.ktz.cinephilia.ui.screens.fragments.movies.NowPlayingMovies
import com.ktz.cinephilia.ui.screens.fragments.movies.NowPlayingMoviesBase
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlayingMoviesBaseResponse(
    @Contextual
    @SerialName("dates")
    val dates: Dates? = null,
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<NowPlayingMoviesResponse> = listOf(),
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
data class NowPlayingMoviesResponse(
    @SerialName("adult")
    val adult: Boolean? = false,
    @SerialName("backdrop_path")
    val backdropPath: String? = "",
    @SerialName("genre_ids")
    val genreIds: List<Int>? = listOf(),
    @SerialName("id")
    val id: Int = 0,
    @SerialName("original_language")
    val originalLanguage: String? = "",
    @SerialName("original_title")
    val originalTitle: String? = "",
    @SerialName("overview")
    val overview: String? = "",
    @SerialName("popularity")
    val popularity: Double? = 0.0,
    @SerialName("poster_path")
    val posterPath: String? = "",
    @SerialName("release_date")
    val releaseDate: String? = "",
    @SerialName("title")
    val title: String? = "",
    @SerialName("video")
    val video: Boolean? = false,
    @SerialName("vote_average")
    val voteAverage: Double? = 0.0,
    @SerialName("vote_count")
    val voteCount: Int? = 0
)

fun NowPlayingMoviesBaseResponse.toNowPlayingMoviesBase(): NowPlayingMoviesBase = NowPlayingMoviesBase(
    dates = dates?.toNowPlayingMoviesBaseDates(),
    page = page,
    results = results.map { it.toNowPlayingMovies() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun NowPlayingMoviesBaseResponse.Dates.toNowPlayingMoviesBaseDates(): NowPlayingMoviesBase.Dates = NowPlayingMoviesBase.Dates(
    maximum = maximum,
    minimum = minimum
)

fun NowPlayingMoviesResponse.toNowPlayingMovies(): NowPlayingMovies = NowPlayingMovies(
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

fun NowPlayingMoviesBaseResponse.toNowPlayingMoviesBaseEntity(): NowPlayingMoviesBaseEntity = NowPlayingMoviesBaseEntity(
    dates = dates?.toNowPlayingMoviesBaseEntityDates(),
    page = page,
    results = results.map { it.toNowPlayingMoviesEntity() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun NowPlayingMoviesBaseResponse.Dates.toNowPlayingMoviesBaseEntityDates(): NowPlayingMoviesBaseEntity.Dates = NowPlayingMoviesBaseEntity.Dates(
    maximum = maximum,
    minimum = minimum
)

fun NowPlayingMoviesResponse.toNowPlayingMoviesEntity(): NowPlayingMoviesEntity = NowPlayingMoviesEntity(
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

fun NowPlayingMoviesResponse.toNowPlayingMoviesListEntity(): NowPlayingMoviesListEntity = NowPlayingMoviesListEntity(
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
