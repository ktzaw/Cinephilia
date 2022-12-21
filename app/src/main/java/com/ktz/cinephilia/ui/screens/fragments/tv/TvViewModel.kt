package com.ktz.cinephilia.ui.screens.fragments.tv

import com.ktz.cinephilia.data.models.remote.tv.toOnAir
import com.ktz.cinephilia.data.models.remote.tv.toPopularTv
import com.ktz.cinephilia.data.models.remote.tv.toTopRatedTv
import com.ktz.cinephilia.repositories.tv.TvReposiory
import com.ktz.cinephilia.ui.screens.CinephiliaEvent
import com.ktz.cinephilia.ui.screens.fragments.BaseViewModel
import com.ktz.cinephilia.utils.mapState
import com.ktz.cinephilia.utils.onErrorState
import com.ktz.cinephilia.utils.onLoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(private val repository: TvReposiory) : BaseViewModel() {

    fun loadTopRatedTv() = repository.getTopRatedTv(1)
        .mapState {
            it.map { topRatedTvResponse ->
                topRatedTvResponse.toTopRatedTv()
            }
        }
        .onLoadingState {
            emitEvent(CinephiliaEvent.Loading())
        }
        .onErrorState {
            // emitEvent(CinephiliaEvent.Nothing)
        }

    fun loadOnAirTv() = repository.getOnAirTv(1)
        .mapState {
            it.map { onAirResponse ->
                onAirResponse.toOnAir()
            }
        }
        .onLoadingState {
            emitEvent(CinephiliaEvent.Loading())
        }
        .onErrorState {
            // emitEvent(CinephiliaEvent.Nothing)
        }

    fun loadPopularTv() = repository.getPopularTv(1)
        .mapState {
            it.map { popularTvResponse ->
                popularTvResponse.toPopularTv()
            }
        }
        .onLoadingState {
            emitEvent(CinephiliaEvent.Loading())
        }
        .onErrorState {
            // emitEvent(CinephiliaEvent.Nothing)
        }
}
