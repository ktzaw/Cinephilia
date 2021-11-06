package com.ktz.cinephilia

import androidx.lifecycle.ViewModel
import com.ktz.cinephilia.data.model.MovieResponse
import com.ktz.cinephilia.data.model.NowPlaying
import com.ktz.cinephilia.repository.nowplaying.NowPlayingMovieRepositoryImpl
import com.ktz.cinephilia.repository.nowplaying.NowPlayingMoviesRepository
import com.ktz.cinephilia.ui.addTo
import com.ktz.cinephilia.utils.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NowPlayingMoviesViewModel @Inject constructor(

    private val repository: NowPlayingMovieRepositoryImpl,
    private val compositeDisposable: CompositeDisposable

) : ViewModel() {

    fun loadNowPlayingMovies(
        page: Int,
        success: (MovieResponse<NowPlaying>) -> Unit,
        fail: (String) -> Unit
    ) {

        repository.loadNowPlayingMovies(API_KEY, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    success(it)
                }
            }, {
                fail(it.localizedMessage.toString())
            }).addTo(compositeDisposable)
    }

}