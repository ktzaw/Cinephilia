package com.ktz.cinephilia.ui.screens.fragments.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ktz.cinephilia.data.models.remote.movies.toNowPlayingMovies
import com.ktz.cinephilia.data.models.remote.movies.toPopularMovies
import com.ktz.cinephilia.data.models.remote.movies.toUpcomingMovies
import com.ktz.cinephilia.databinding.FragmentMoviesBinding
import com.ktz.cinephilia.ui.screens.adapters.movies.MoviesDelegate
import com.ktz.cinephilia.ui.screens.adapters.movies.nowPlayingHorizontal.NowPlayingHorizontalAdapter
import com.ktz.cinephilia.ui.screens.adapters.movies.popularHorizontal.PopularHorizontalAdapter
import com.ktz.cinephilia.ui.screens.adapters.movies.topRatedCarousel.TopRatedCarouselAdapter
import com.ktz.cinephilia.ui.screens.adapters.movies.upcomingHorizontal.UpcomingHorizontalAdapter
import com.ktz.cinephilia.ui.screens.fragments.BaseFragment
import com.ktz.cinephilia.ui.screens.fragments.main.MainFragment
import com.ktz.cinephilia.ui.screens.fragments.main.MainFragmentDirections
import com.ktz.cinephilia.utils.onLoadingState
import com.ktz.cinephilia.utils.onSuccessState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<MoviesViewModel>(), MoviesDelegate {

    private lateinit var binding: FragmentMoviesBinding

    private val parent: MainFragment
        get() = requireParentFragment().requireParentFragment() as MainFragment

    override val viewModel: MoviesViewModel by viewModels()

    private val topRatedCarouselAdapter: TopRatedCarouselAdapter by lazy { TopRatedCarouselAdapter(this) }
    private val nowPlayingHorizontalAdapter: NowPlayingHorizontalAdapter by lazy { NowPlayingHorizontalAdapter(this) }
    private val popularHorizontalAdapter: PopularHorizontalAdapter by lazy { PopularHorizontalAdapter(this) }
    private val upcomingHorizontalAdapter: UpcomingHorizontalAdapter by lazy { UpcomingHorizontalAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUi() {
        setupRecyclerView()
        handleLoading()

        viewModel.loadTopRatedMovies()
            .onSuccessState {
                topRatedCarouselAdapter.setData(it)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.loadNowPlayingMovies()
            .onSuccessState {
                nowPlayingHorizontalAdapter.setData(
                    it.map { nowPlayingMoviesResponse -> nowPlayingMoviesResponse.toNowPlayingMovies() }
                )
                //  handleLoading()
            }.onLoadingState {
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.loadPopularMovies()
            .onSuccessState {
                popularHorizontalAdapter.setData(
                    it.map { popularMoviesResponse -> popularMoviesResponse.toPopularMovies() }
                )
                //  handleLoading()
            }.onLoadingState {
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.loadUpcomingMovies()
            .onSuccessState {
                //   handleLoading()

                upcomingHorizontalAdapter.setData(
                    it.map { upcomingMoviesResponse -> upcomingMoviesResponse.toUpcomingMovies() }
                )
            }.onLoadingState {
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun setupListenteners() {
        binding.swipeRefreshLayoutMoviesList.setOnRefreshListener {
            viewModel.refresh()
        }

        with(binding.layoutMoviesList){
            tvNowPlayingViewAll.setOnClickListener {
                parent.handleNavigation(MainFragmentDirections.actionMainFragmentToNowPlayingListFragment())
            }

            tvPopularViewAll.setOnClickListener {
                parent.handleNavigation(MainFragmentDirections.actionMainFragmentToPopularMoviesListFragment())
            }

            tvUpcomingViewAll.setOnClickListener {
                parent.handleNavigation(MainFragmentDirections.actionMainFragmentToUpcomingMoviesListFragment())
            }
        }
    }

    private fun handleLoading() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadingState.collect {
                binding.swipeRefreshLayoutMoviesList.isRefreshing = it
            }
        }
    }

    private fun setupRecyclerView() {
        binding.layoutMoviesList.rvTopRatedCarousel.adapter = topRatedCarouselAdapter
        binding.layoutMoviesList.rvTopRatedCarousel.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.layoutMoviesList.rvNowPlayingHorizontal.adapter = nowPlayingHorizontalAdapter
        binding.layoutMoviesList.rvNowPlayingHorizontal.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.layoutMoviesList.rvPopularHorizontal.adapter = popularHorizontalAdapter
        binding.layoutMoviesList.rvPopularHorizontal.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.layoutMoviesList.rvUpcomingHorizontal.adapter = upcomingHorizontalAdapter
        binding.layoutMoviesList.rvUpcomingHorizontal.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onItemClicked() {
        showQuickToastMessage("Item Clicked")
    }

    private fun stopRefresh() {
        binding.swipeRefreshLayoutMoviesList.isRefreshing = false
    }
}
