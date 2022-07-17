package com.ktz.cinephilia.ui.screens.adapters.movies.nowPlayingHorizontal

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.bumptech.glide.Glide
import com.ktz.cinephilia.BuildConfig
import com.ktz.cinephilia.R
import com.ktz.cinephilia.data.domain.NowPlayingMovies
import com.ktz.cinephilia.databinding.ListItemPostersBinding
import com.ktz.cinephilia.ui.screens.adapters.BaseAdapter
import com.ktz.cinephilia.ui.screens.adapters.BaseViewHolder
import com.ktz.cinephilia.ui.screens.adapters.movies.MoviesDelegate

class NowPlayingHorizontalAdapter(listener: MoviesDelegate) :
    BaseAdapter<NowPlayingHorizontalAdapter.NowPlayerViewHolder, NowPlayingMovies>() {

    private val mListener: MoviesDelegate = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemPostersBinding.inflate(layoutInflater, parent, false)
        return NowPlayerViewHolder(binding, mListener)
    }

    class NowPlayerViewHolder(private val binding: ListItemPostersBinding, private val listenter: MoviesDelegate) : BaseViewHolder<NowPlayingMovies>(binding.root) {

        override fun setData(data: NowPlayingMovies) {

            Glide.with(binding.ivPoster.context)
                .load(BuildConfig.BASE_IMAGE_URL + data.posterPath)
                .placeholder(R.drawable.ic_cinephilia_place_holder)
                .into(binding.ivPoster)

            binding.rating = data.voteAverage.toString()

            binding.root.setOnClickListener {
                mData.let {
                    listenter.onItemClicked()
                }
            }
        }
    }
}
