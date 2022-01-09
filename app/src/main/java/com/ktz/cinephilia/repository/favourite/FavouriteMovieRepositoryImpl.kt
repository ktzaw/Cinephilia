package com.ktz.cinephilia.repository.favourite

import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import com.ktz.cinephilia.data.db.MoviesDatabase
import com.ktz.cinephilia.data.model.MovieDetail
import com.ktz.cinephilia.data.model.Reviews
import javax.inject.Inject

class FavouriteMovieRepositoryImpl @Inject constructor(

    private val database: MoviesDatabase

) : FavouriteMovieRepository {


    override suspend fun getAllMovies(): LiveData<List<MovieDetail>> {
        return database.withTransaction { database.favouriteDao.getAllMovies() }
    }

    override suspend fun deleteMovie(movieId: Int) {
        database.withTransaction {

            database.favouriteDao.delete(movieId)

        }
    }

    override suspend fun getReview(movieId: Int): LiveData<List<Reviews>> {
        return database.withTransaction { database.reviewDao.getReview(movieId) }
    }

    override suspend fun deleteReview(movieId: Int) {
        database.withTransaction {
            database.reviewDao.delete(movieId)
        }
    }

}