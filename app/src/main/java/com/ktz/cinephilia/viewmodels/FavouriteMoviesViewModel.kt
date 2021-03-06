package com.ktz.cinephilia.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ktz.cinephilia.data.model.MovieDetail
import com.ktz.cinephilia.data.model.Reviews
import com.ktz.cinephilia.repository.favourite.FavouriteMovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteMoviesViewModel @Inject constructor(

    private val repository: FavouriteMovieRepositoryImpl

) : ViewModel() {

    fun getFavouriteMovies(): MediatorLiveData<List<MovieDetail>> {

        val moviesList = MediatorLiveData<List<MovieDetail>>()

        viewModelScope.launch {

            moviesList.addSource(repository.getAllMovies()) { movies ->

                moviesList.postValue(movies)

            }

        }

        return moviesList

    }

    fun getFavouriteMovieReview(movieId: Int):MediatorLiveData<List<Reviews>>{

        val movieReviewList = MediatorLiveData<List<Reviews>>()

        viewModelScope.launch {

            movieReviewList.addSource(repository.getReview(movieId)){

                movieReviewList.postValue(it)

            }

        }
        return movieReviewList

    }

     fun removeFromFavourite(movieId: Int) {

        viewModelScope.launch {
            repository.deleteMovie(movieId)
            repository.deleteReview(movieId)
        }
    }

}