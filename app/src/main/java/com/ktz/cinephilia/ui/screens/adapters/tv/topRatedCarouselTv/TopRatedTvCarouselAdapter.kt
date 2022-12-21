package com.ktz.cinephilia.ui.screens.adapters.tv.topRatedCarouselTv

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.ktz.cinephilia.BuildConfig
import com.ktz.cinephilia.R
import com.ktz.cinephilia.databinding.ListItemTopRatedTvCarouselBinding
import com.ktz.cinephilia.ui.screens.adapters.BaseAdapter
import com.ktz.cinephilia.ui.screens.adapters.BaseViewHolder
import com.ktz.cinephilia.ui.screens.adapters.tv.TvDelegate
import com.ktz.cinephilia.ui.screens.fragments.tv.TopRatedTvCarousel

class TopRatedCarouselTvAdapter(listener: TvDelegate) : BaseAdapter<TopRatedCarouselTvAdapter.TopRatedCarouselTvViewHolder, TopRatedTvCarousel>() {

    private val mListener: TvDelegate = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedCarouselTvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemTopRatedTvCarouselBinding.inflate(layoutInflater, parent, false)
        return TopRatedCarouselTvViewHolder(binding, mListener)
    }

    class TopRatedCarouselTvViewHolder(private val binding: ListItemTopRatedTvCarouselBinding, private val listenter: TvDelegate) : BaseViewHolder<TopRatedTvCarousel>(binding.root) {

        override fun setData(data: TopRatedTvCarousel) {
            Glide.with(binding.ivTopRatedTvPoster.context)
                .asBitmap()
                .placeholder(R.drawable.ic_cinephilia_place_holder)
                .load(BuildConfig.BASE_IMAGE_URL + data.posterPath)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        binding.ivTopRatedTvPoster.setImageBitmap(resource)
                        Palette.from(resource).generate { palette ->
                            if (palette != null) {
                                binding.cardTopRatedTv.setCardBackgroundColor(
                                    palette.getDarkMutedColor(
                                        R.color.primaryLightColor
                                    )
                                )
                            } else {
                                binding.cardTopRatedTv.setCardBackgroundColor(R.color.primaryLightColor)
                            }
                        }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })

            binding.data = data

            binding.root.setOnClickListener {
                it.let {
                    listenter.onItemClicked()
                }
            }
        }
    }
}
