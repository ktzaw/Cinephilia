package com.ktz.cinephilia.ui.screens.fragments.moviesList.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.ktz.cinephilia.R
import com.ktz.cinephilia.databinding.FragmentNowPlayingListBinding
import com.ktz.cinephilia.databinding.FragmentUpcomingMovieListBinding
import com.ktz.cinephilia.ui.screens.adapters.paging.NowPlayingMoviesPagingAdapter
import com.ktz.cinephilia.ui.screens.adapters.paging.UpcomingMoviesPagingAdapter
import com.ktz.cinephilia.ui.screens.fragments.BaseFragment
import com.ktz.cinephilia.ui.screens.fragments.BaseViewModel
import com.ktz.cinephilia.ui.screens.fragments.moviesList.nowPlaying.NowPlayingListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpcomingMoviesListFragment : BaseFragment<BaseViewModel>() {

    private lateinit var binding: FragmentUpcomingMovieListBinding

    override val viewModel: UpcomingMoviesListViewModel by viewModels()

    private val mAdapter = UpcomingMoviesPagingAdapter { showQuickToastMessage("Movies Clicked") }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpcomingMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUi() {
        setUpToolbar()

        with(binding.layoutMoviesFullList) {
            rvMoviesFullList.adapter = mAdapter
            lifecycleScope.launch {
                viewModel.loadUpcomingMoviesList().collectLatest {
                    mAdapter.submitData(it)
                }
            }

            moviesListSwipeRefresh.setOnRefreshListener {
                mAdapter.refresh()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                mAdapter.loadStateFlow.collect { loadState ->
                    val isListEmpty = loadState.refresh is LoadState.NotLoading && mAdapter.itemCount == 0

                    binding.layoutMoviesFullList.moviesListSwipeRefresh.isRefreshing = loadState.refresh is LoadState.Loading

                    val errorState = loadState.source.append as? LoadState.Error
                        ?: loadState.source.prepend as? LoadState.Error
                        ?: loadState.append as? LoadState.Error
                        ?: loadState.prepend as? LoadState.Error
                    errorState?.let {
                        showLongToastMessage("\uD83D\uDE28 Wooops ${it.error}")
                    }
                }
            }
        }
    }

    private fun setUpToolbar() {
        val activity = requireParentFragment().requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.layoutMoviesFullList.toolbarMoviesFullList)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        with(binding.layoutMoviesFullList) {
            toolbarMoviesFullList.title =
                this@UpcomingMoviesListFragment.requireContext().getString(R.string.upcoming_movies)

            toolbarMoviesFullList.setNavigationOnClickListener {
                navController.navigateUp()
            }
        }
    }

    override fun setupListenteners() {
    }
}
