package com.ktz.cinephilia.ui.screens.fragments.series

import com.ktz.cinephilia.ui.screens.fragments.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(): BaseViewModel<SeriesEvent>(){}
