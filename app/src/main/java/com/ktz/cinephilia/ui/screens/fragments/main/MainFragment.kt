package com.ktz.cinephilia.ui.screens.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.ktz.cinephilia.R
import com.ktz.cinephilia.databinding.FragmentMainBinding
import com.ktz.cinephilia.ui.screens.fragments.BaseFragment

class MainFragment : BaseFragment<MainViewModel>() {

    private lateinit var binding: FragmentMainBinding
    override val viewModel: MainViewModel by viewModels()

    override val navController: NavController
        get() {
            return findNavController()
        }

    private val childNavController: NavController by lazy {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUi() {
        NavigationUI.setupWithNavController(binding.bottomNavigationBar, childNavController)
    }

    override fun navigateTo(direction: NavDirections) {
        navController.navigate(direction)
    }

    fun handleNavigation(direction: NavDirections){
        navController.navigate(direction)
    }

    override fun setupListenteners() {
    }
}
