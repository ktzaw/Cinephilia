package com.ktz.cinephilia.ui.screens.fragments.tv

data class PopularTvCarousel(
    val backdropPath: String? = null,
    val firstAirDate: String = "",
    val genreIds: List<Int> = listOf(),
    val id: Int = 0,
    val name: String = "",
    val originCountry: List<String> = listOf(),
    val originalLanguage: String = "",
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String? = null,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)
