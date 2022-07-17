package com.ktz.cinephilia.di

import com.ktz.cinephilia.data.source.remote.MoviesApi
import com.ktz.cinephilia.repositories.MoviesRepository
import com.ktz.cinephilia.repositories.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(moviesApi: MoviesApi): MoviesRepository = MoviesRepositoryImpl(moviesApi)
}
