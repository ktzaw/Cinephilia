package com.ktz.cinephilia.di

import com.ktz.cinephilia.data.source.local.movies.MoviesLocalDataSource
import com.ktz.cinephilia.data.source.local.movies.MoviesLocalDataSourceImpl
import com.ktz.cinephilia.data.source.local.room.daos.movies.NowPlayingMoviesDao
import com.ktz.cinephilia.data.source.local.room.daos.movies.PopularMoviesDao
import com.ktz.cinephilia.data.source.local.room.daos.movies.TopRatedMoviesDao
import com.ktz.cinephilia.data.source.local.room.daos.movies.UpcomingMoviesDao
import com.ktz.cinephilia.data.source.local.room.daos.tv.OnAirTvDao
import com.ktz.cinephilia.data.source.local.room.daos.tv.PopularTvDao
import com.ktz.cinephilia.data.source.local.room.daos.tv.TopRatedTvDao
import com.ktz.cinephilia.data.source.local.tv.TvLocalDataSource
import com.ktz.cinephilia.data.source.local.tv.TvLocalDataSourceImpl
import com.ktz.cinephilia.data.source.remote.api.MoviesApi
import com.ktz.cinephilia.data.source.remote.api.TvApi
import com.ktz.cinephilia.data.source.remote.movies.MoviesRemoteDataSource
import com.ktz.cinephilia.data.source.remote.movies.MoviesRemoteDataSourceImpl
import com.ktz.cinephilia.data.source.remote.tv.TvRemoteDataSource
import com.ktz.cinephilia.data.source.remote.tv.TvRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideMoviesLocalDataSource(
        topRatedMoviesDao: TopRatedMoviesDao,
        nowPlayingMoviesDao: NowPlayingMoviesDao,
        popularMoviesDao: PopularMoviesDao,
        upcomingMoviesDao: UpcomingMoviesDao
    ): MoviesLocalDataSource = MoviesLocalDataSourceImpl(topRatedMoviesDao, nowPlayingMoviesDao, popularMoviesDao, upcomingMoviesDao)

    @Singleton
    @Provides
    fun provideMoviesRemoteDataSource(moviesApi: MoviesApi): MoviesRemoteDataSource = MoviesRemoteDataSourceImpl(moviesApi)

    @Singleton
    @Provides
    fun provideTvLocalDataSource(
        topRatedTvDao: TopRatedTvDao,
        onAirTvDao: OnAirTvDao,
        popularTvDao: PopularTvDao
    ): TvLocalDataSource = TvLocalDataSourceImpl(topRatedTvDao, onAirTvDao, popularTvDao)

    @Provides
    @Singleton
    fun provideTvRemoteDataSource(tvApi: TvApi): TvRemoteDataSource = TvRemoteDataSourceImpl(tvApi)
}
