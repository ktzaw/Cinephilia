package com.ktz.cinephilia.di

import android.app.Application
import android.content.Context
import com.ktz.cinephilia.data.db.MoviesDatabase
import com.ktz.cinephilia.data.db.dao.FavouriteDao
import com.ktz.cinephilia.data.db.dao.ReviewsDao
import com.ktz.cinephilia.service.ApiService
import com.ktz.cinephilia.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttpClient: OkHttpClient =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideMoviesApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideMovieDatabase(application: Application): MoviesDatabase =
        MoviesDatabase.getInstance(application)

    @Provides
    @Singleton
    fun provideFavouriteDao(db: MoviesDatabase): FavouriteDao {
        return db.favouriteDao
    }

    @Provides
    @Singleton
    fun provideReviewsDao(db: MoviesDatabase): ReviewsDao {
        return db.reviewDao
    }


}