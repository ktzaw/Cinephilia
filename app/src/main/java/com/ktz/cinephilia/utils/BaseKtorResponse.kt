package com.ktz.cinephilia.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseKtorResponse<T>(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: T
)
