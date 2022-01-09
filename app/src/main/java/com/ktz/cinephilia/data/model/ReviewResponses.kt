package com.ktz.cinephilia.data.model

import com.google.gson.annotations.SerializedName

data class ReviewResponses(
    @SerializedName("id")
    val id: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Reviews>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)