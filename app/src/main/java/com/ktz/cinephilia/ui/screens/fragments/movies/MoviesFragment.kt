package com.ktz.cinephilia.ui.screens.fragments.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ktz.cinephilia.databinding.FragmentMoviesBinding
import com.ktz.cinephilia.ui.screens.adapters.movies.MoviesDelegate
import com.ktz.cinephilia.ui.screens.adapters.movies.nowPlayingHorizontal.NowPlayingHorizontalAdapter
import com.ktz.cinephilia.ui.screens.adapters.movies.popularHorizontal.PopularHorizontalAdapter
import com.ktz.cinephilia.ui.screens.adapters.movies.topRatedCarousel.TopRatedCarouselAdapter
import com.ktz.cinephilia.ui.screens.adapters.movies.upcomingHorizontal.UpcomingHorizontalAdapter
import com.ktz.cinephilia.ui.screens.fragments.BaseFragment
import com.ktz.cinephilia.utils.onLoadingState
import com.ktz.cinephilia.utils.onSuccessState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn

@AndroidEntryPoint
class MoviesFragment : BaseFragment<MoviesEvent, MoviesViewModel>(), MoviesDelegate {

    private lateinit var binding: FragmentMoviesBinding

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

        val genreMap = mutableMapOf<Int, String>()
        val genreList = mutableListOf<String>()

        viewModel.loadTopRatedMovies()
            .onSuccessState {
                topRatedCarouselAdapter.setData(it.results)
            }
            .onLoadingState {
                showQuickToastMessage("Loading")
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.loadNowPlayingMovies().onSuccessState {
            nowPlayingHorizontalAdapter.setData(it.results)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.loadPopularMovies().onSuccessState {
            popularHorizontalAdapter.setData(it.results)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.loadUpcomingMovies().onSuccessState {
            upcomingHorizontalAdapter.setData(it.results)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun setupListenteners() {
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
}
