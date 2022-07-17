package com.ktz.cinephilia.ui.screens.fragments

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.ktz.cinephilia.ui.screens.CinephiliaEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<T> : ViewModel() {

    private val _navFlow: Channel<NavDirections?> = Channel(Channel.RENDEZVOUS)
    val navFlow: Flow<NavDirections?> = _navFlow.receiveAsFlow()

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

    protected fun navigateTo(direction: NavDirections) {
        _navFlow.trySend(direction)
    }

}
