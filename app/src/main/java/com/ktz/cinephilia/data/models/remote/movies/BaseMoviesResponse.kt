package com.ktz.cinephilia.data.models.remote.movies

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseMoviesResponse<T>(
    @Contextual
    @SerialName("dates")
    val dates: Dates? = null,
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<T> = listOf(),
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
