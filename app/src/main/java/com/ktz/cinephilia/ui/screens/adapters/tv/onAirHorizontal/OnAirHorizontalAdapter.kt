package com.ktz.cinephilia.ui.screens.adapters.tv.onAirHorizontal

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ktz.cinephilia.BuildConfig
import com.ktz.cinephilia.R
import com.ktz.cinephilia.databinding.ListItemPostersBinding
import com.ktz.cinephilia.ui.screens.adapters.BaseAdapter
import com.ktz.cinephilia.ui.screens.adapters.BaseViewHolder
import com.ktz.cinephilia.ui.screens.adapters.tv.TvDelegate
import com.ktz.cinephilia.ui.screens.fragments.tv.OnAirTvCarousel

class OnAirHorizontalAdapter(listener: TvDelegate) : BaseAdapter<OnAirHorizontalAdapter.OnAirViewHolder, OnAirTvCarousel>() {

    private val mListener: TvDelegate = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnAirViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemPostersBinding.inflate(layoutInflater, parent, false)
        return OnAirViewHolder(binding, mListener)
    }

    class OnAirViewHolder(private val binding: ListItemPostersBinding, private val listenter: TvDelegate) : BaseViewHolder<OnAirTvCarousel>(binding.root) {

        override fun setData(data: OnAirTvCarousel) {
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
