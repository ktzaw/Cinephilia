package com.ktz.cinephilia.service

import com.ktz.cinephilia.data.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun loadNowPlayingMovies(
        @Query("api_key") apiKey: String, @Query("page") page: Int
    ): MovieResponse<Movies>

    @GET("movie/popular")
    suspend fun loadPopularMovies(
        @Query("api_key") apiKey: String, @Query("page") page: Int
    ): MovieResponse<Movies>

    @GET("movie/upcoming")
    suspend fun loadUpcomingMovies(
        @Query("api_key") apiKey: String, @Query("page") page: Int
    ): MovieResponse<Movies>

    @GET("movie/{movie_id}")
    suspend fun loadMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetail

    @GET("movie/{movie_id}/videos")
    suspend fun loadMovieTrailer(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): VideoResponses

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(
        @Path("movie_id")movieId: Int,
        @Query("api_key")apiKey: String
    ):ReviewResponses

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page")page: Int
    ):SearchResponse



}