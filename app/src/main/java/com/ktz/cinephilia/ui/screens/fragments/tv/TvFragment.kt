package com.ktz.cinephilia.ui.screens.fragments.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ktz.cinephilia.databinding.FragmentTvBinding
import com.ktz.cinephilia.ui.screens.adapters.tv.TvDelegate
import com.ktz.cinephilia.ui.screens.adapters.tv.onAirHorizontal.OnAirHorizontalAdapter
import com.ktz.cinephilia.ui.screens.adapters.tv.popularHorizontal.PopularTvHorizontalAdapter
import com.ktz.cinephilia.ui.screens.adapters.tv.topRatedCarouselTv.TopRatedCarouselTvAdapter
import com.ktz.cinephilia.ui.screens.fragments.BaseFragment
import com.ktz.cinephilia.utils.onLoadingState
import com.ktz.cinephilia.utils.onSuccessState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn

@AndroidEntryPoint
class TvFragment : BaseFragment<TvViewModel>(), TvDelegate {

    private lateinit var binding: FragmentTvBinding

    override val viewModel: TvViewModel by viewModels()

    private val topRatedTvAdapter: TopRatedCarouselTvAdapter by lazy { TopRatedCarouselTvAdapter(this) }
    private val onAirAdapter: OnAirHorizontalAdapter by lazy { OnAirHorizontalAdapter(this) }
    private val popularTvAdapter: PopularTvHorizontalAdapter by lazy { PopularTvHorizontalAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUi() {
        setupRecyclerView()

        viewModel.loadTopRatedTv()
            .onSuccessState {
                topRatedTvAdapter.setData(it)
            }
            .onLoadingState {
                showQuickToastMessage("Loading")
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.loadOnAirTv().onSuccessState {
            onAirAdapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.loadPopularTv().onSuccessState {
            popularTvAdapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun setupListenteners() {
    }

    private fun setupRecyclerView() {
        binding.layoutTvList.rvTopRatedCarouselTv.adapter = topRatedTvAdapter
        binding.layoutTvList.rvTopRatedCarouselTv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.layoutTvList.rvOnAirHorizontal.adapter = onAirAdapter
        binding.layoutTvList.rvOnAirHorizontal.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.layoutTvList.rvPopularHorizontal.adapter = popularTvAdapter
        binding.layoutTvList.rvPopularHorizontal.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onItemClicked() {
        showQuickToastMessage("Item Clicked")
    }
}
