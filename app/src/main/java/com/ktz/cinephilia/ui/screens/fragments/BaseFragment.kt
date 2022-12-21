package com.ktz.cinephilia.ui.screens.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ktz.cinephilia.ui.screens.CinephiliaEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import timber.log.Timber

abstract class BaseFragment<T : BaseViewModel>() : Fragment() {

    abstract val viewModel: T

    private val eventScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    open val navController: NavController
        get() = findNavController()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListenteners()

        observeEvents()
    }

    private fun observeEvents() {
        viewModel.cinephiliaEventFlow
            .filterNotNull()
            .onStart { Timber.tag("Cinephilial").d("Observing Global Events Now") }
            .onEach(::handleCinephiliaEvents)
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    protected open fun handleCinephiliaEvents(e: CinephiliaEvent) {
        when (e) {
            is CinephiliaEvent.Loading -> if (e.isLoading) showQuickToastMessage("Loading...")
            is CinephiliaEvent.SnackBarMessage -> showQuickSnackBar(e.message)
            is CinephiliaEvent.ToastMessage -> showQuickToastMessage(e.message)
            CinephiliaEvent.Nothing -> {}
        }
    }

    protected fun showQuickSnackBar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    protected fun showLongSnackBar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

    protected fun showQuickToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun showLongToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    protected open fun navigateTo(direction: NavDirections) {
        navController.navigate(direction)
    }

    abstract fun setupUi()

    abstract fun setupListenteners()
}
