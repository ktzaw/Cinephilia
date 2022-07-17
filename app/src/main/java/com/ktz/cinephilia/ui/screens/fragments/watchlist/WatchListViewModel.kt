package com.ktz.cinephilia.ui.screens.fragments.watchlist

import com.ktz.cinephilia.ui.screens.fragments.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor() : BaseViewModel<WatchlistEvent>(){}
