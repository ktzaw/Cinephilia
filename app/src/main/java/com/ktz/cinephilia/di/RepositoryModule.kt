package com.ktz.cinephilia.di

import android.os.Build.VERSION_CODES.P
import com.ktz.cinephilia.data.source.local.movies.MoviesLocalDataSource
import com.ktz.cinephilia.data.source.local.room.CinephiliaDatabase
import com.ktz.cinephilia.data.source.local.tv.TvLocalDataSource
import com.ktz.cinephilia.data.source.remote.api.MoviesApi
import com.ktz.cinephilia.data.source.remote.movies.MoviesRemoteDataSource
import com.ktz.cinephilia.data.source.remote.tv.TvRemoteDataSource
import com.ktz.cinephilia.repositories.movies.MoviesRepository
import com.ktz.cinephilia.repositories.movies.MoviesRepositoryImpl
import com.ktz.cinephilia.repositories.moviesList.NowPlayingMoviesListRepository
import com.ktz.cinephilia.repositories.moviesList.NowPlayingMoviesListRepositoryImpl
import com.ktz.cinephilia.repositories.moviesList.PopularMoviesListRepository
import com.ktz.cinephilia.repositories.moviesList.PopularMoviesListRepositoryImpl
import com.ktz.cinephilia.repositories.moviesList.UpcomingMoviesListRepository
import com.ktz.cinephilia.repositories.moviesList.UpcomingMoviesListRepositoryImpl
import com.ktz.cinephilia.repositories.tv.TvReposiory
import com.ktz.cinephilia.repositories.tv.TvRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @OptIn(FlowPreview::class)
    @Singleton
    @Provides
    fun provideMoviesRepository(
        localDataSource: MoviesLocalDataSource,
        remoteDataSource: MoviesRemoteDataSource
    ): MoviesRepository = MoviesRepositoryImpl(localDataSource, remoteDataSource)

    @Singleton
    @Provides
    fun provideTvRepository(
        localDataSource: TvLocalDataSource,
        remoteDataSource: TvRemoteDataSource
    ): TvReposiory = TvRepositoryImpl(localDataSource, remoteDataSource)

    @Singleton
    @Provides
    fun provideNowPlayingMoviesListRepository(
        moviesApi: MoviesApi,
        database: CinephiliaDatabase
    ): NowPlayingMoviesListRepository = NowPlayingMoviesListRepositoryImpl(moviesApi, database)

    @Singleton
    @Provides
    fun providePopularMoviesListRepository(
        moviesApi: MoviesApi,
        database: CinephiliaDatabase
    ): PopularMoviesListRepository = PopularMoviesListRepositoryImpl(moviesApi, database)

    @Singleton
    @Provides
    fun provideUpcomingMoviesListRepository(
        moviesApi: MoviesApi,
        database: CinephiliaDatabase
    ): UpcomingMoviesListRepository = UpcomingMoviesListRepositoryImpl(moviesApi, database)
}
