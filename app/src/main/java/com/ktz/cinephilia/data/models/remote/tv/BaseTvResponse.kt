package com.ktz.cinephilia.data.models.remote.tv // ktlint-disable filename

import com.ktz.cinephilia.data.models.entities.tv.BaseTvEntity
import com.ktz.cinephilia.data.models.entities.tv.OnAirEntity
import com.ktz.cinephilia.data.models.entities.tv.PopularTvEntity
import com.ktz.cinephilia.data.models.entities.tv.TopRatedTvEntity
import com.ktz.cinephilia.ui.screens.fragments.tv.BaseTv
import com.ktz.cinephilia.ui.screens.fragments.tv.OnAirTvCarousel
import com.ktz.cinephilia.ui.screens.fragments.tv.PopularTvCarousel
import com.ktz.cinephilia.ui.screens.fragments.tv.TopRatedTvCarousel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseTvResponses<T>(
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<T> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)

fun BaseTvResponses<OnAirResponse>.toOnAirResponseViewObject(): BaseTv<OnAirTvCarousel> = BaseTv(
    page = page,
    results = results.map { it.toOnAir() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun BaseTvResponses<PopularTvResponse>.toPopularTvResponseViewObject(): BaseTv<PopularTvCarousel> = BaseTv(
    page = page,
    results = results.map { it.toPopularTv() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun BaseTvResponses<TopRatedTvResponse>.toTopRatedTvResponseViewObject(): BaseTv<TopRatedTvCarousel> = BaseTv(
    page = page,
    results = results.map { it.toTopRatedTv() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun BaseTvResponses<OnAirResponse>.toOnAirEntity(): BaseTvEntity<OnAirEntity> = BaseTvEntity(
    page = page,
    results = results.map { it.toOnAirEntity() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun BaseTvResponses<PopularTvResponse>.toPopularEntity(): BaseTvEntity<PopularTvEntity> = BaseTvEntity(
    page = page,
    results = results.map { it.toPopularTvEntity() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun BaseTvResponses<TopRatedTvResponse>.toTopRatedTvEntity(): BaseTvEntity<TopRatedTvEntity> = BaseTvEntity(
    page = page,
    results = results.map { it.toTopRatedTvEntity() },
    totalPages = totalPages,
    totalResults = totalResults
)
