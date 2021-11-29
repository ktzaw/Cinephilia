package com.ktz.cinephilia.repository.favouriteDetail

import androidx.room.withTransaction
import com.ktz.cinephilia.data.db.MoviesDatabase
import com.ktz.cinephilia.data.model.MovieDetail
import javax.inject.Inject

class FavouriteDetailRepositoryImpl @Inject constructor(
    private val database: MoviesDatabase
) : FavouriteDetailRepository {

    override suspend fun getMovie(id: Int): MovieDetail {
        return database.favouriteDao.getSpecificMovie(id)

    }

    override suspend fun removeFromFavourite(id: Int) {
        database.favouriteDao.delete(id)
    }
}