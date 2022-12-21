package com.ktz.cinephilia.ui.screens.fragments

import androidx.lifecycle.ViewModel
import com.ktz.cinephilia.ui.screens.CinephiliaEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {

    val cinephiliaEventFlow: MutableStateFlow<CinephiliaEvent> = MutableStateFlow(CinephiliaEvent.Nothing)

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _toastMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    val toastMessage: StateFlow<String?> = _toastMessage

    protected fun emitEvent(event: CinephiliaEvent) {
        cinephiliaEventFlow.value = event
    }

    protected fun setLoading(isLoading: Boolean) {
        _loading.value = isLoading
        Thread.sleep(500)
        _toastMessage.value = null
    }
}
