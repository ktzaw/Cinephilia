package com.ktz.cinephilia

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ktz.cinephilia.adapters.MoviesListAdapter
import com.ktz.cinephilia.adapters.ViewPagerAdapter
import com.ktz.cinephilia.data.model.NowPlaying
import com.ktz.cinephilia.databinding.FragmentMoviesListBinding
import com.ktz.cinephilia.databinding.ListItemMoviesBinding
import com.ktz.cinephilia.utils.GridItemNumber
import com.ktz.cinephilia.utils.IMAGE_URL
import com.list.rados.fast_list.bind
import com.list.rados.fast_list.update
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NowPlayingFragment : Fragment(R.layout.fragment_movies_list) {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    private var _listItemBinding: ListItemMoviesBinding? = null
    private val listItemBinding get() = _listItemBinding!!

    private val viewModel: NowPlayingMoviesViewModel by viewModels()


    private val mAdapter = MoviesListAdapter(mutableListOf())

    fun newInstance(): NowPlayingFragment {
        return NowPlayingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.swipeRefresh.isRefreshing = true
        binding.swipeRefresh.setOnRefreshListener {
            loadNowPlayingMoviesData(1)
        }

        _listItemBinding = ListItemMoviesBinding.inflate(inflater, container, false)

        val noOfColumns = GridItemNumber().calculateNoOfColumns(requireActivity().applicationContext,120f)
        val gridLayoutManager = GridLayoutManager(context, noOfColumns)
        binding.rvMovieList.adapter = mAdapter
        binding.rvMovieList.layoutManager = gridLayoutManager


        binding.swipeRefresh.isRefreshing = true

        loadNowPlayingMoviesData(1)

        return view
    }


    private fun loadNowPlayingMoviesData(page: Int) {

        viewModel.loadNowPlayingMovies(page, {

            binding.swipeRefresh.isRefreshing = false
            mAdapter.setMovies(it.results)
        }, {

            binding.swipeRefresh.isRefreshing = false
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()

        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _listItemBinding = null
    }

}