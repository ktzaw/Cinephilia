package com.ktz.cinephilia.ui.fragment.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.ktz.cinephilia.R
import com.ktz.cinephilia.adapters.ViewPagerAdapter
import com.ktz.cinephilia.databinding.FragmentMoviesBinding
import com.ktz.cinephilia.databinding.LayoutToolbarViewBinding

class MoviesFragment : Fragment() {

    private lateinit var toolbarBinding: LayoutToolbarViewBinding
    private val toolbar: Toolbar by lazy { toolbarBinding.customToolbar }

    private var _binding: FragmentMoviesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        setUpToolbar()

        val viewPagerAdapter =
            ViewPagerAdapter(childFragmentManager, lifecycle, requireActivity().applicationContext)

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

    private fun setUpToolbar() {

        toolbarBinding = LayoutToolbarViewBinding.inflate(layoutInflater)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        val mToolbar = (activity as AppCompatActivity).supportActionBar
        mToolbar?.title = getString(R.string.app_name)

        mToolbar?.setDisplayShowTitleEnabled(true)

    }

}