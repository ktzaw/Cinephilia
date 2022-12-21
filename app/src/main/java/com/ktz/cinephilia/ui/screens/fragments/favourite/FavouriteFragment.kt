package com.ktz.cinephilia.ui.screens.fragments.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ktz.cinephilia.databinding.FragmentFavouriteBinding
import com.ktz.cinephilia.ui.screens.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FavouriteViewModel>() {

    private lateinit var binding: FragmentFavouriteBinding

    override val viewModel: FavouriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUi() {
    }

    override fun setupListenteners() {
    }
}