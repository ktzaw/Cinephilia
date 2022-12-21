package com.ktz.cinephilia.ui.screens.adapters.movies.upcomingHorizontal

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ktz.cinephilia.BuildConfig
import com.ktz.cinephilia.R
import com.ktz.cinephilia.databinding.ListItemPostersBinding
import com.ktz.cinephilia.ui.screens.adapters.BaseAdapter
import com.ktz.cinephilia.ui.screens.adapters.BaseViewHolder
import com.ktz.cinephilia.ui.screens.adapters.movies.MoviesDelegate
import com.ktz.cinephilia.ui.screens.fragments.movies.UpcomingMovies

class UpcomingHorizontalAdapter(listener: MoviesDelegate) : BaseAdapter<UpcomingHorizontalAdapter.UpcomingHorizontalViewHolder, UpcomingMovies>() {

    private val mListener: MoviesDelegate = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingHorizontalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemPostersBinding.inflate(layoutInflater, parent, false)
        return UpcomingHorizontalViewHolder(binding, mListener)
    }

    class UpcomingHorizontalViewHolder(private val binding: ListItemPostersBinding, private val listenter: MoviesDelegate) : BaseViewHolder<UpcomingMovies>(binding.root) {

        override fun setData(data: UpcomingMovies) {
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
