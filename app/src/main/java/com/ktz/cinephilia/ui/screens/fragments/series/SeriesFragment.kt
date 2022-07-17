package com.ktz.cinephilia.ui.screens.fragments.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ktz.cinephilia.databinding.FragmentSeriesBinding
import com.ktz.cinephilia.ui.screens.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment : BaseFragment<SeriesEvent, SeriesViewModel>() {

    private lateinit var binding: FragmentSeriesBinding

    override val viewModel: SeriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUi() {
    }

    override fun setupListenteners() {
    }
}
