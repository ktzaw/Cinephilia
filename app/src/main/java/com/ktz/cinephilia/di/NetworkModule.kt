package com.ktz.cinephilia.di

import com.ktz.cinephilia.data.source.remote.KtorHttpClient
import com.ktz.cinephilia.data.source.remote.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetwrokModule {

    @Singleton
    @Provides
    fun provideKtorClient(): HttpClient = KtorHttpClient.create()

    @Singleton
    @Provides
    fun provideTestApi(client: HttpClient): MoviesApi = MoviesApi(client)
}
