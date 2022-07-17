package com.ktz.cinephilia.ui.screens.adapters.movies.topRatedCarousel

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
import com.ktz.cinephilia.data.domain.TopRatedMovies
import com.ktz.cinephilia.data.domain.toTopRatedMoviesViewObject
import com.ktz.cinephilia.databinding.ListItemTopRatedCarouselBinding
import com.ktz.cinephilia.ui.screens.adapters.BaseAdapter
import com.ktz.cinephilia.ui.screens.adapters.BaseViewHolder
import com.ktz.cinephilia.ui.screens.adapters.movies.MoviesDelegate

class TopRatedCarouselAdapter(listener: MoviesDelegate) :
    BaseAdapter<TopRatedCarouselAdapter.TopRatedCarouselViewHolder, TopRatedMovies>() {

    private val mListener: MoviesDelegate = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedCarouselViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemTopRatedCarouselBinding.inflate(layoutInflater, parent, false)
        return TopRatedCarouselViewHolder(binding, mListener)
    }

    class TopRatedCarouselViewHolder(private val binding: ListItemTopRatedCarouselBinding, private val listenter: MoviesDelegate) : BaseViewHolder<TopRatedMovies>(binding.root) {

        override fun setData(data: TopRatedMovies) {

            Glide.with(binding.ivTopRatedPoster.context)
                .asBitmap()
                .placeholder(R.drawable.ic_cinephilia_place_holder)
                .load(BuildConfig.BASE_IMAGE_URL + data.posterPath)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {

                        binding.ivTopRatedPoster.setImageBitmap(resource)
                        Palette.from(resource).generate { palette ->
                            if (palette != null) {
                                binding.cardTopRated.setCardBackgroundColor(palette.getDarkMutedColor(R.color.primaryLightColor))
                            } else {
                                binding.cardTopRated.setCardBackgroundColor(R.color.primaryLightColor)
                            }
                        }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })

            binding.data = data.toTopRatedMoviesViewObject()

            binding.root.setOnClickListener {
                mData.let {
                    listenter.onItemClicked()
                }
            }
        }
    }
}
