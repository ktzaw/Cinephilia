package com.ktz.cinephilia.data.models.remote.movies

import com.ktz.cinephilia.data.models.entities.movies.PopularMoviesBaseEntity
import com.ktz.cinephilia.data.models.entities.movies.PopularMoviesEntity
import com.ktz.cinephilia.data.models.entities.moviesList.PopularMoviesListEntity
import com.ktz.cinephilia.ui.screens.fragments.movies.PopularMovies
import com.ktz.cinephilia.ui.screens.fragments.movies.PopularMoviesBase
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularMoviesBaseResponse(
    @Contextual
    @SerialName("dates")
    val dates: Dates? = null,
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<PopularMoviesResponse> = listOf(),
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
data class PopularMoviesResponse(
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

fun PopularMoviesBaseResponse.toPopularMoviesBase(): PopularMoviesBase = PopularMoviesBase(
    dates = dates?.toPopularMoviesBaseDates(),
    page = page,
    results = results.map { it.toPopularMovies() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun PopularMoviesBaseResponse.Dates.toPopularMoviesBaseDates(): PopularMoviesBase.Dates = PopularMoviesBase.Dates(
    maximum = maximum,
    minimum = minimum
)

fun PopularMoviesResponse.toPopularMovies(): PopularMovies = PopularMovies(
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

fun PopularMoviesBaseResponse.toPopularMoviesBaseEntity(): PopularMoviesBaseEntity = PopularMoviesBaseEntity(
    dates = dates?.toPopularMoviesBaseEntityDates(),
    page = page,
    results = results.map { it.toPopularMoviesEntity() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun PopularMoviesBaseResponse.Dates.toPopularMoviesBaseEntityDates(): PopularMoviesBaseEntity.Dates = PopularMoviesBaseEntity.Dates(
    maximum = maximum,
    minimum = minimum
)

fun PopularMoviesResponse.toPopularMoviesEntity(): PopularMoviesEntity = PopularMoviesEntity(
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

fun PopularMoviesResponse.toPopularMoviesListEntity(): PopularMoviesListEntity = PopularMoviesListEntity(
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
