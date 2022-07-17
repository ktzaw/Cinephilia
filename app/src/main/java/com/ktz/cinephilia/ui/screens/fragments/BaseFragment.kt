package com.ktz.cinephilia.ui.screens.fragments

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
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

abstract class BaseFragment<E, T : BaseViewModel<E>>() : Fragment() {

    abstract val viewModel: T

    private val eventScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private val navController: NavController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListenteners()

        observeEvents()
        observeNavigationEvents()
    }

    private fun observeEvents() {

        viewModel.cinephiliaEventFlow
            .filterNotNull()
            .onStart { Timber.tag("Cinephilial").d("Observing Global Events Now") }
            .onEach(::handleCinephiliaEvents)
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun observeNavigationEvents() {
        viewModel.navFlow
            .filterNotNull()
            .onEach(::handleNavigationEvents)
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    protected open fun handleNavigationEvents(direction: NavDirections) {
        try {
            navController.navigate(direction)
        } catch (e: IllegalArgumentException) {
            Timber.e(e)
        }
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

    abstract fun setupUi()

    abstract fun setupListenteners()

    private fun requestNetwork() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val connectivityManager = requireContext().getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        // network is available for use
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
        }

        // Network capabilities have changed for the network
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            val unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
        }

        // lost network connection
        override fun onLost(network: Network) {
            super.onLost(network)
            showQuickSnackBar("Check your network connection")
        }
    }

//    // Network Connectivity
//    private suspend fun isNetworkAvailable(): Boolean = withContext(Dispatchers.IO) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val connectivityManager = requireContext().getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//            if (capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true) {
//                return@withContext testConnection()
//            } else {
//                return@withContext false
//            }
//        }
//        return@withContext true
//    }
//
//    private fun testConnection(): Boolean =
//        try {
//            val socket = Socket()
//            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
//            socket.close()
//            true
//        } catch (e: Exception) {
//            false
//        }
}
