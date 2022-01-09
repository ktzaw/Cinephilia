package com.ktz.cinephilia.ui.fragment.movies.popular

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ktz.cinephilia.R
import com.ktz.cinephilia.adapters.MovieLoadSateAdapter
import com.ktz.cinephilia.adapters.MoviesListAdapter
import com.ktz.cinephilia.data.model.Movies
import com.ktz.cinephilia.databinding.FragmentMoviesListBinding
import com.ktz.cinephilia.ui.activities.movieDetail.MovieDetailActivity
import com.ktz.cinephilia.utils.GridItemNumber
import com.ktz.cinephilia.utils.isNetworkAvailable
import com.ktz.cinephilia.viewmodels.PopularViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularFragment : Fragment(R.layout.fragment_movies_list) {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!


    private val viewModel: PopularViewModel by viewModels()

    private val mAdapter = MoviesListAdapter() { movies: Movies ->

        intentToMovieDetail(movies.id)

    }

    fun newInstance(): PopularFragment {
        return PopularFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        val view = binding.root

        setUpAdapter()
        setUpLoadStateAdapter()

        submitData()

        binding.swipeRefresh.isEnabled = true

        binding.swipeRefresh.setOnRefreshListener { mAdapter.retry() }

        return view
    }

    private fun setUpLoadStateAdapter() {
        val header = MovieLoadSateAdapter()
        binding.rvMovieList.adapter = mAdapter.withLoadStateHeaderAndFooter(

            header = header,
            footer = MovieLoadSateAdapter()

        )

        lifecycleScope.launch {

            try {
                mAdapter.loadStateFlow.collectLatest { loadState ->

                    header.loadState = loadState.mediator?.refresh
                        ?.takeIf { it is LoadState.Error && mAdapter.itemCount > 0 }
                        ?: loadState.prepend

                    val isListEmpty =
                        loadState.refresh is LoadState.NotLoading || mAdapter.itemCount == 0

                    binding.swipeRefresh.isRefreshing = loadState.mediator?.refresh is LoadState.Loading

                 }
            } catch (e: Exception) {
            }

        }
    }

    private fun setUpAdapter() {
        val noOfColumns =
            GridItemNumber().calculateNoOfColumns(requireActivity().applicationContext, 120f)
        val gridLayoutManager = GridLayoutManager(context, noOfColumns)

        binding.rvMovieList.adapter = mAdapter
        binding.rvMovieList.layoutManager = gridLayoutManager
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun intentToMovieDetail(movieId: Int) {

        val intent = Intent(this.context, MovieDetailActivity::class.java).apply {
            putExtra("MOVIE_ID", movieId)
        }

        startActivity(intent)

    }

    private fun submitData() {

        if (requireContext().isNetworkAvailable()) {

            binding.tvError.visibility = View.GONE
            binding.rvMovieList.visibility = View.VISIBLE

            lifecycleScope.launch {
                viewModel.loadPopularMovies().collectLatest {
                    mAdapter.submitData(it)

                }
            }

        } else {

            binding.tvError.visibility = View.VISIBLE
            binding.swipeRefresh.setOnRefreshListener { mAdapter.retry() }

            lifecycleScope.launch {
                viewModel.loadPopularMovies().collectLatest {
                    mAdapter.submitData(it)

                }
            }
        }
    }

}