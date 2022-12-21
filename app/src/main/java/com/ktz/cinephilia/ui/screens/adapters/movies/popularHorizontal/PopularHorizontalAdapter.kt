package com.ktz.cinephilia.ui.screens.adapters.movies.popularHorizontal

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ktz.cinephilia.BuildConfig
import com.ktz.cinephilia.R
import com.ktz.cinephilia.databinding.ListItemPostersBinding
import com.ktz.cinephilia.ui.screens.adapters.BaseAdapter
import com.ktz.cinephilia.ui.screens.adapters.BaseViewHolder
import com.ktz.cinephilia.ui.screens.adapters.movies.MoviesDelegate
import com.ktz.cinephilia.ui.screens.fragments.movies.PopularMovies

class PopularHorizontalAdapter(listener: MoviesDelegate) : BaseAdapter<PopularHorizontalAdapter.PopularHorizontalViewHolder, PopularMovies>() {

    private val mListener: MoviesDelegate = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularHorizontalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemPostersBinding.inflate(layoutInflater, parent, false)
        return PopularHorizontalViewHolder(binding, mListener)
    }

    class PopularHorizontalViewHolder(private val binding: ListItemPostersBinding, private val listenter: MoviesDelegate) : BaseViewHolder<PopularMovies>(binding.root) {

        override fun setData(data: PopularMovies) {
            Glide.with(binding.ivPoster.context)
                .load(BuildConfig.BASE_IMAGE_URL + data.posterPath)
                .placeholder(R.drawable.ic_cinephilia_place_holder)
                .into(binding.ivPoster)

            binding.rating = data.voteAverage.toString()

            binding.root.setOnClickListener {
                it.let {
                    listenter.onItemClicked()
                }
            }
        }
    }
}
