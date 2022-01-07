package com.ktz.cinephilia.repository.movieDetail

import androidx.room.withTransaction
import com.ktz.cinephilia.data.db.MoviesDatabase
import com.ktz.cinephilia.data.model.MovieDetail
import com.ktz.cinephilia.data.model.ReviewResponses
import com.ktz.cinephilia.data.model.ReviewResult
import com.ktz.cinephilia.data.model.VideoResponses
import com.ktz.cinephilia.service.ApiService
import com.ktz.cinephilia.utils.API_KEY
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(

    private val apiService: ApiService,
    private val database: MoviesDatabase

) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return apiService.loadMovieDetail(movieId, API_KEY)
    }

    override suspend fun getVideo(movieId: Int): VideoResponses {
        return apiService.loadMovieTrailer(movieId, API_KEY)
    }

    override suspend fun addToFavourite(movieDetail: MovieDetail) {
        database.withTransaction {

            database.favouriteDao.insertFavouriteMovie(movieDetail)

        }
    }

    override suspend fun removeFromFavourite(movieId: Int) {
        database.withTransaction {

            database.favouriteDao.delete(movieId)

        }
    }

    override suspend fun addReviewToFavourite(movieReview: ReviewResult) {
        database.withTransaction {
            database.reviewDao.insertReviews(movieReview)
        }
    }

    override suspend fun getReviews(movieId: Int): ReviewResponses {
        return apiService.getReviews(movieId, API_KEY)
    }
}