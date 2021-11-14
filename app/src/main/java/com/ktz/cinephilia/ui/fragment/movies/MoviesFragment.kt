package com.ktz.cinephilia.ui.fragment.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.ktz.cinephilia.adapters.ViewPagerAdapter
import com.ktz.cinephilia.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        binding.viewpager2.adapter = viewPagerAdapter

        binding.viewpager2.isUserInputEnabled = true

        TabLayoutMediator(binding.tabLayout, binding.viewpager2) { tabs, position ->

            tabs.text = viewPagerAdapter.getTitle(position)

        }.attach()


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}