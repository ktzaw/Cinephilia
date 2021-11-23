package com.ktz.cinephilia.repository.favourite

import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import com.ktz.cinephilia.data.db.MoviesDatabase
import com.ktz.cinephilia.data.model.MovieDetail
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

}