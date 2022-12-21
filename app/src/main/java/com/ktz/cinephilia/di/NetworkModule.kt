package com.ktz.cinephilia.di

import com.ktz.cinephilia.data.source.remote.api.KtorHttpClient
import com.ktz.cinephilia.data.source.remote.api.MoviesApi
import com.ktz.cinephilia.data.source.remote.api.MoviesApiImpl
import com.ktz.cinephilia.data.source.remote.api.TvApi
import com.ktz.cinephilia.data.source.remote.api.TvApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideKtorClient(): HttpClient = KtorHttpClient.create()

    @Singleton
    @Provides
    fun provideMoviesApi(client: HttpClient): MoviesApi = MoviesApiImpl(client)

    @Singleton
    @Provides
    fun provideTvApi(client: HttpClient): TvApi = TvApiImpl(client)
}
