package com.ktz.cinephilia.ui.screens

sealed class CinephiliaEvent {

    data class ToastMessage(val message: String) : CinephiliaEvent()
    data class SnackBarMessage(val message: String) : CinephiliaEvent()
    data class Loading(val isLoading: Boolean = true) : CinephiliaEvent()

    object Nothing : CinephiliaEvent()
}
