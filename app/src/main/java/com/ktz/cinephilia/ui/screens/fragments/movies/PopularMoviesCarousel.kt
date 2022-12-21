package com.ktz.cinephilia.ui.screens.fragments.movies

data class PopularMoviesBase(
    val dates: Dates?,
    val page: Int,
    val results: List<PopularMovies>,
    val totalPages: Int,
    val totalResults: Int
) {
    data class Dates(
        val maximum: String,
        val minimum: String
    )
}
data class PopularMovies(
    val adult: Boolean? = false,
    val backdropPath: String? = "",
    val genreIds: List<Int> = listOf(),
    val id: Int = 0,
    val originalLanguage: String? = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double? = 0.0,
    val posterPath: String? = "",
    val releaseDate: String? = "",
    val title: String = "",
    val video: Boolean? = false,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0
)
