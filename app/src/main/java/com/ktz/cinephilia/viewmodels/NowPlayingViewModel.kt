package com.ktz.cinephilia.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ktz.cinephilia.data.model.Movies
import com.ktz.cinephilia.repository.nowplaying.NowPlayingRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(

    private val repositoryImpl: NowPlayingRepositoryImpl

) : ViewModel() {

    fun loadNowPlayingMovies():Flow<PagingData<Movies>>{

        return repositoryImpl.getNowPlayingMovies().cachedIn(viewModelScope)

    }

}