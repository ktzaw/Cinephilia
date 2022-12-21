package com.ktz.cinephilia.data.models.entities.movies

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ktz.cinephilia.data.models.remote.movies.TopRatedMoviesBaseResponse
import com.ktz.cinephilia.data.models.remote.movies.TopRatedMoviesResponse
import com.ktz.cinephilia.ui.screens.fragments.movies.TopRatedMovies
import com.ktz.cinephilia.ui.screens.fragments.movies.TopRatedMoviesBase
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "TopRatedMoviesBase")
@Serializable
data class TopRatedMoviesBaseEntity(
    @SerialName("page")
    @PrimaryKey(autoGenerate = false)
    val page: Int = 0,
    @SerialName("results")
    val results: List<TopRatedMoviesEntity> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)

@Entity(tableName = "TopRatedMovies")
@Serializable
data class TopRatedMoviesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerialName("adult")
    val adult: Boolean = false,
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("genre_ids")
    val genreIds: List<Int> = listOf(),
    @SerialName("movieId")
    val movieId: Int = 0,
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

fun TopRatedMoviesBaseEntity.toTopRatedMoviesBase(): TopRatedMoviesBase = TopRatedMoviesBase(
    page = page,
    results = listOf(TopRatedMoviesEntity().toTopRatedMovies()),
    totalPages = totalPages,
    totalResults = totalResults
)

fun TopRatedMoviesEntity.toTopRatedMovies(): TopRatedMovies = TopRatedMovies(
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

fun TopRatedMoviesBaseEntity.toTopRatedMoviesBaseResponse(): TopRatedMoviesBaseResponse = TopRatedMoviesBaseResponse(
    page = page,
    results = listOf(TopRatedMoviesEntity().toTopRatedMoviesResponse()),
    totalPages = totalPages,
    totalResults = totalResults
)

fun TopRatedMoviesEntity.toTopRatedMoviesResponse(): TopRatedMoviesResponse = TopRatedMoviesResponse(
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
