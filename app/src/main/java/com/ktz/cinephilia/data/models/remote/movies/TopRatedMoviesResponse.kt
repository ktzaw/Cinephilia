package com.ktz.cinephilia.data.models.remote.movies

import com.ktz.cinephilia.data.models.entities.movies.TopRatedMoviesEntity
import com.ktz.cinephilia.data.models.entities.movies.TopRatedMoviesBaseEntity
import com.ktz.cinephilia.ui.screens.fragments.movies.TopRatedMoviesBase
import com.ktz.cinephilia.ui.screens.fragments.movies.TopRatedMovies
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopRatedMoviesBaseResponse(
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<TopRatedMoviesResponse> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)

@Serializable
data class TopRatedMoviesResponse(
    @SerialName("adult")
    val adult: Boolean = false,
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("genre_ids")
    val genreIds: List<Int> = listOf(),
    @SerialName("id")
    val id: Int = 0,
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("original_title")
    val originalTitle: String = "",
    @SerialName("overview")
    val overview: String = "",
    @SerialName("popularity")
    val popularity: Double = 0.0,
    @SerialName("poster_path")
    val posterPath: String = "",
    @SerialName("release_date")
    val releaseDate: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("video")
    val video: Boolean = false,
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0
)

fun TopRatedMoviesBaseResponse.toTopRatedMoviesResponseViewObject(): TopRatedMoviesBase = TopRatedMoviesBase(
    page = page,
    results = results.map { it.toTopRatedMovies() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun TopRatedMoviesResponse.toTopRatedMovies(): TopRatedMovies = TopRatedMovies(
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

fun TopRatedMoviesBaseResponse.toTopRatedMoviesBaseEntity(): TopRatedMoviesBaseEntity = TopRatedMoviesBaseEntity(
    page = page,
    results = results.map { it.toTopRatedMoviesEntity() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun TopRatedMoviesResponse.toTopRatedMoviesEntity(): TopRatedMoviesEntity = TopRatedMoviesEntity(
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
