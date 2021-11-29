package com.ktz.cinephilia.repository.favouriteDetail

import com.ktz.cinephilia.data.model.MovieDetail

interface FavouriteDetailRepository {

    suspend fun getMovie(id:Int):MovieDetail
    suspend fun removeFromFavourite(id: Int)

}