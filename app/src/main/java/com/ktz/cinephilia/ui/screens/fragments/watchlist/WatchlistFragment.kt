package com.ktz.cinephilia.ui.screens.fragments.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ktz.cinephilia.databinding.FragmentWatchlistBinding
import com.ktz.cinephilia.ui.screens.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchlistFragment : BaseFragment<WatchListViewModel>() {

    private lateinit var binding: FragmentWatchlistBinding

    override val viewModel: WatchListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUi() {
    }

    override fun setupListenteners() {
    }
}
