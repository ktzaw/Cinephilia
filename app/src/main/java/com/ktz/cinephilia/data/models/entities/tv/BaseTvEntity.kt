package com.ktz.cinephilia.data.models.entities.tv

data class BaseTvEntity<T>(
    val page: Int = 0,
    val results: List<T> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)