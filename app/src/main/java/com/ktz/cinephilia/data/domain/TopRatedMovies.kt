package com.ktz.cinephilia.data.domain

import com.ktz.cinephilia.ui.screens.fragments.movies.TopRatedMoviesResponseViewObject
import com.ktz.cinephilia.ui.screens.fragments.movies.TopRatedMoviesViewObject
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopRatedMoviesResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<TopRatedMovies>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)

@Serializable
data class TopRatedMovies(
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("id")
    val id: Int,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("title")
    val title: String,
    @SerialName("video")
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)

fun TopRatedMoviesResponse.toTopRatedMoviesResponseViewObject(): TopRatedMoviesResponseViewObject =
    TopRatedMoviesResponseViewObject(
        page = page,
        results = toTopRatedMoviesViewObjecList(results),
        totalPages = totalPages,
        totalResults = totalResults
    )

fun TopRatedMovies.toTopRatedMoviesViewObject(): TopRatedMoviesViewObject =
    TopRatedMoviesViewObject(
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

fun toTopRatedMoviesViewObjecList(result: List<TopRatedMovies>): List<TopRatedMoviesViewObject> {
    val topRatedMoviesViewObjectList = mutableListOf<TopRatedMoviesViewObject>()
    result.forEach {
        topRatedMoviesViewObjectList.add(it.toTopRatedMoviesViewObject())
    }
    return topRatedMoviesViewObjectList
}
