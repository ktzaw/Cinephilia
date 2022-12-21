package com.ktz.cinephilia.ui.screens.fragments.tv

data class BaseTv<T>(
    val page: Int = 0,
    val results: List<T> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)
