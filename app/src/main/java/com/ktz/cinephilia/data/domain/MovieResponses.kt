package com.ktz.cinephilia.data.domain

import com.ktz.cinephilia.ui.screens.fragments.movies.MovieResponseViewObject
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponses<T>(
    @Contextual
    @SerialName("dates")
    val dates: Dates? = null,
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<T>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
) {
    @Serializable
    data class Dates(
        @SerialName("maximum")
        val maximum: String,
        @SerialName("minimum")
        val minimum: String
    )
}

fun <W> MovieResponses<W>.toMovieResponseViewObject(): MovieResponseViewObject<W> = MovieResponseViewObject(
    dates = dates?.toMovieResponseViewObjectDates(),
    page = page,
    results = results,
    totalPages = totalPages,
    totalResults = totalResults
)

fun MovieResponses.Dates.toMovieResponseViewObjectDates(): MovieResponseViewObject.Dates = MovieResponseViewObject.Dates(
    maximum = maximum,
    minimum = minimum
)
