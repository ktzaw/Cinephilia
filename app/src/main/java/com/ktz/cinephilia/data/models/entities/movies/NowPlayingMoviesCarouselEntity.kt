package com.ktz.cinephilia.data.models.entities.movies

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ktz.cinephilia.data.models.remote.movies.NowPlayingMoviesBaseResponse
import com.ktz.cinephilia.data.models.remote.movies.NowPlayingMoviesResponse
import com.ktz.cinephilia.ui.screens.fragments.movies.NowPlayingMovies
import com.ktz.cinephilia.ui.screens.fragments.movies.NowPlayingMoviesBase
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "now_playing_movies_base")
@Serializable
data class NowPlayingMoviesBaseEntity(
    @Contextual
    @SerialName("dates")
    val dates: Dates? = null,
    @PrimaryKey(autoGenerate = false)
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<NowPlayingMoviesEntity> = listOf(),
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

@Entity(tableName = "NowPlayingMovies")
@Serializable
data class NowPlayingMoviesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerialName("adult")
    val adult: Boolean? = false,
    @SerialName("backdrop_path")
    val backdropPath: String? = "",
    @SerialName("genre_ids")
    val genreIds: List<Int>? = listOf(),
    @SerialName("movieId")
    val movieId: Int = 0,
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

fun NowPlayingMoviesBaseEntity.toNowPlayingMoviesBase(): NowPlayingMoviesBase = NowPlayingMoviesBase(
    dates = dates?.toNowPlayingMoviesBaseDates(),
    page = page,
    results = results.map { it.toNowPlayingMovies() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun NowPlayingMoviesBaseEntity.Dates.toNowPlayingMoviesBaseDates(): NowPlayingMoviesBase.Dates = NowPlayingMoviesBase.Dates(
    maximum = maximum,
    minimum = minimum
)

fun NowPlayingMoviesEntity.toNowPlayingMovies(): NowPlayingMovies = NowPlayingMovies(
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

fun NowPlayingMoviesBaseEntity.toNowPlayingMoviesBaseResponse(): NowPlayingMoviesBaseResponse = NowPlayingMoviesBaseResponse(
    dates = dates?.toNowPlayingMoviesBaseResponseDates(),
    page = page,
    results = results.map { it.toNowPlayingMoviesResponse() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun NowPlayingMoviesBaseEntity.Dates.toNowPlayingMoviesBaseResponseDates(): NowPlayingMoviesBaseResponse.Dates = NowPlayingMoviesBaseResponse.Dates(
    maximum = maximum,
    minimum = minimum
)

fun NowPlayingMoviesEntity.toNowPlayingMoviesResponse(): NowPlayingMoviesResponse = NowPlayingMoviesResponse(
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
