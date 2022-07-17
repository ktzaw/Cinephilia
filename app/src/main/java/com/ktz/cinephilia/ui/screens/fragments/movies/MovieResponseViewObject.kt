package com.ktz.cinephilia.ui.screens.fragments.movies

data class MovieResponseViewObject<T>(
    val dates: Dates?,
    val page: Int,
    val results: List<T>,
    val totalPages: Int,
    val totalResults: Int
) {
    data class Dates(
        val maximum: String,
        val minimum: String
    )
}
