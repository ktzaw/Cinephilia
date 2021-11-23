package com.ktz.cinephilia.ui.fragment.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ktz.cinephilia.databinding.FragmentSearchBinding
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ktz.cinephilia.adapters.MovieLoadSateAdapter
import com.ktz.cinephilia.adapters.SearchMoviesAdapter
import com.ktz.cinephilia.ui.activities.movieDetail.MovieDetailActivity
import com.ktz.cinephilia.utils.GridItemNumber
import com.ktz.cinephilia.utils.isNetworkAvailable
import com.ktz.cinephilia.utils.toastShort
import com.ktz.cinephilia.viewmodels.SearchMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchMoviesViewModel by viewModels()

    private val mAdapter = SearchMoviesAdapter() {
        intentToMovieDetail(it.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)



        setUpAdapters()

        binding.swipeRefresh.isEnabled = false


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpLoadStateAdapter()

        binding.searchViewMovies.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    toastShort(context, "Please enter a valid movie name")
                } else {

                    submitData(query)

                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })


    }

    private fun setUpAdapters() {

        val noOfColumns =
            GridItemNumber().calculateNoOfColumns(requireActivity().applicationContext, 120f)
        val gridLayoutManager = GridLayoutManager(context, noOfColumns)

        binding.rvSearchList.adapter = mAdapter
        binding.rvSearchList.layoutManager = gridLayoutManager

    }

    private fun setUpLoadStateAdapter() {
        val header = MovieLoadSateAdapter()
        binding.rvSearchList.adapter = mAdapter.withLoadStateHeaderAndFooter(

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
                        loadState.refresh is LoadState.NotLoading && mAdapter.itemCount == 0

                    binding.swipeRefresh.isRefreshing =
                        loadState.mediator?.refresh is LoadState.Loading

                }
            } catch (e: Exception) {
            }

        }
    }

    private fun submitData(query: String) {

        binding.swipeRefresh.isRefreshing = true

        if (requireContext().isNetworkAvailable()) {

            binding.rvSearchList.visibility = View.VISIBLE

            lifecycleScope.launch {
                viewModel.searchMovies(query).collectLatest {
                    mAdapter.submitData(it)

                }
            }

            binding.swipeRefresh.isRefreshing = mAdapter.itemCount == 0

        } else {

            binding.swipeRefresh.isRefreshing = false
            lifecycleScope.launch {
                viewModel.searchMovies(query).collectLatest {
                    mAdapter.submitData(it)

                }
            }

            val snack = Snackbar.make(
                requireContext(),
                requireView(),
                "Check your network and try again!",
                Snackbar.LENGTH_INDEFINITE
            )
            snack.setAction("Retry", View.OnClickListener {
                submitData(query)
            })
            snack.show()
        }
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

}