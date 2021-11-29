package com.ktz.cinephilia.ui.fragment.favourite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ktz.cinephilia.R
import com.ktz.cinephilia.adapters.FavouriteMovieListAdapter
import com.ktz.cinephilia.data.model.MovieDetail
import com.ktz.cinephilia.databinding.FragmentFavouriteBinding
import com.ktz.cinephilia.databinding.LayoutToolbarViewBinding
import com.ktz.cinephilia.ui.activities.movieDetail.MovieDetailActivity
import com.ktz.cinephilia.ui.fragment.movies.nowPlaying.NowPlayingFragment
import com.ktz.cinephilia.utils.GridItemNumber
import com.ktz.cinephilia.viewmodels.FavouriteMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.RuntimeException

@AndroidEntryPoint
class FavouriteMoviesFragment : Fragment(R.layout.fragment_favourite) {

    private lateinit var toolbarBinding: LayoutToolbarViewBinding
    private val toolbar: Toolbar by lazy { toolbarBinding.customToolbar }

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: FavouriteMoviesViewModel by viewModels()

    private val mAdapter = FavouriteMovieListAdapter(mutableListOf()) {
        intentToMovieDetail(it.id)
    }

    fun newInstance(): FavouriteMoviesFragment {
        return FavouriteMoviesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)

        setUpToolbar()

        setUpAdapter()

        return binding.root
    }

    private fun setUpAdapter() {

        val noOfColumns =
            GridItemNumber().calculateNoOfColumns(requireActivity().applicationContext, 120f)
        val gridLayoutManager = GridLayoutManager(context, noOfColumns)

        binding.rvFavouriteMoviesList.adapter = mAdapter
        binding.rvFavouriteMoviesList.layoutManager = gridLayoutManager

        viewmodel.getFavouriteMovies().observe(viewLifecycleOwner, Observer {

            it.let {
                mAdapter.setMovies(it)
            }

        })

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

    private fun intentToMovieDetail(movieId: Int) {

        val intent = Intent(this.context, FavouriteMovieDetailActivity::class.java).apply {
            putExtra("MOVIE_ID", movieId)
        }

        startActivity(intent)

    }

}