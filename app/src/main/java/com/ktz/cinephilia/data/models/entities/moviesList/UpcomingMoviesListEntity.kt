package com.ktz.cinephilia.data.models.entities.moviesList

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "upcoming_movies_list")
@Serializable
data class UpcomingMoviesListEntity(
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

//fun NowPlayingMoviesListEntity.toNowPlayingMoviesList(): NowPlayingMoviesList = NowPlayingMoviesList(
//    adult = adult,
//    backdropPath = backdropPath,
//    genreIds = genreIds,
//    id = movieId,
//    originalLanguage = originalLanguage,
//    originalTitle = originalTitle,
//    overview = overview,
//    popularity = popularity,
//    posterPath = posterPath,
//    releaseDate = releaseDate,
//    title = title,
//    video = video,
//    voteAverage = voteAverage,
//    voteCount = voteCount
//)