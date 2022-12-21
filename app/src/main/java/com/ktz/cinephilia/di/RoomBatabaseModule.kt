package com.ktz.cinephilia.di

import android.content.Context
import com.ktz.cinephilia.data.source.local.room.CinephiliaDatabase
import com.ktz.cinephilia.data.source.local.room.daos.movies.NowPlayingMoviesDao
import com.ktz.cinephilia.data.source.local.room.daos.movies.PopularMoviesDao
import com.ktz.cinephilia.data.source.local.room.daos.movies.TopRatedMoviesDao
import com.ktz.cinephilia.data.source.local.room.daos.movies.UpcomingMoviesDao
import com.ktz.cinephilia.data.source.local.room.daos.moviesList.NowPlayingMoviesListDao
import com.ktz.cinephilia.data.source.local.room.daos.moviesList.PopularMoviesListDao
import com.ktz.cinephilia.data.source.local.room.daos.moviesList.UpcomingMoviesListDao
import com.ktz.cinephilia.data.source.local.room.daos.remoteKeysDao.NowPlayingMoviesListRemoteKeysDao
import com.ktz.cinephilia.data.source.local.room.daos.remoteKeysDao.PopularMoviesListRemoteKeysDao
import com.ktz.cinephilia.data.source.local.room.daos.remoteKeysDao.UpcomingMoviesListRemoteKeysDao
import com.ktz.cinephilia.data.source.local.room.daos.tv.OnAirTvDao
import com.ktz.cinephilia.data.source.local.room.daos.tv.PopularTvDao
import com.ktz.cinephilia.data.source.local.room.daos.tv.TopRatedTvDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomBatabaseModule {

    @Singleton
    @Provides
    fun provideTopRatedMoviesDao(database: CinephiliaDatabase): TopRatedMoviesDao = database.topRatedMoviesDao()

    @Singleton
    @Provides
    fun provideNowPlayingMoviesDao(database: CinephiliaDatabase): NowPlayingMoviesDao = database.nowPlayingMoviesDao()

    @Singleton
    @Provides
    fun providePopularMoviesDao(database: CinephiliaDatabase): PopularMoviesDao = database.popularMoviesDao()

    @Singleton
    @Provides
    fun provideUpcomingMoviesDao(database: CinephiliaDatabase): UpcomingMoviesDao = database.upcomingMoviesDao()

    @Singleton
    @Provides
    fun provideTopRatedTvDao(database: CinephiliaDatabase): TopRatedTvDao = database.topRatedTvDao()

    @Singleton
    @Provides
    fun provideOnAirTvDao(database: CinephiliaDatabase): OnAirTvDao = database.onAirTvDao()

    @Singleton
    @Provides
    fun providePopularTvDao(database: CinephiliaDatabase): PopularTvDao = database.popularTvDao()

    @Singleton
    @Provides
    fun provideNowPlayingMoviesListDao(database: CinephiliaDatabase): NowPlayingMoviesListDao = database.nowPlayingMoviesListDao()

    @Singleton
    @Provides
    fun provideNowPlayingMoviesListRemoteKeysDao(database: CinephiliaDatabase): NowPlayingMoviesListRemoteKeysDao = database.nowPlayingMoviesListRemoteKeysDao()

    @Singleton
    @Provides
    fun providePopularMoviesListDao(database: CinephiliaDatabase): PopularMoviesListDao = database.popularMoviesListDao()

    @Singleton
    @Provides
    fun providePopularMoviesListRemoteKeysDao(database: CinephiliaDatabase): PopularMoviesListRemoteKeysDao = database.popularMoviesRemoteKeysDao()

    @Singleton
    @Provides
    fun provideUpcomingMoviesListDao(database: CinephiliaDatabase): UpcomingMoviesListDao = database.upcomingMoviesListDao()

    @Singleton
    @Provides
    fun provideUpcomingMoviesListRemoteKeysDao(database: CinephiliaDatabase): UpcomingMoviesListRemoteKeysDao = database.upcomingMoviesRemoteKeysDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CinephiliaDatabase = CinephiliaDatabase.getInstance(context)
}
