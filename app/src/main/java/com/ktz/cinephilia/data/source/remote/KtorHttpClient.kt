package com.ktz.cinephilia.data.source.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import kotlinx.serialization.json.Json
import timber.log.Timber

private const val TIME_OUT = 60_000

interface KtorHttpClient {

    companion object {
        fun create(): HttpClient = HttpClient(Android) {

            install(ContentNegotiation) {
                val converter = KotlinxSerializationConverter(
                    Json {
                        prettyPrint = true
                        ignoreUnknownKeys = true
                    }
                )
                register(ContentType.Application.Json, converter)

                Json {
                    encodeDefaults = false
                    ignoreUnknownKeys = true
                    isLenient = true
                    allowSpecialFloatingPointValues = true
                    prettyPrint = false
                }
            }
            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.v(message)
                    }
                }
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Timber.d("HTTP status ==> ${response.status.value}")
                }
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }
}
