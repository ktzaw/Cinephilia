package com.ktz.cinephilia.data.model

import com.squareup.moshi.Json

data class NetworkResponse(

    @Json(name = "status_message")
    val errorMessage: String?,

    @Json(name = "status_code")
    val errorCode: Int?
)