package com.ktz.cinephilia.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ktz.cinephilia.data.model.MovieDetail
import com.ktz.cinephilia.repository.favouriteDetail.FavouriteDetailRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteDetailViewModel @Inject constructor(

    private val repository: FavouriteDetailRepositoryImpl

) : ViewModel() {
//
//    fun getMovie(id: Int): MovieDetail {
//
//         viewModelScope.launch {
//              repository.getMovie(id)
//        }
//
//    }

}