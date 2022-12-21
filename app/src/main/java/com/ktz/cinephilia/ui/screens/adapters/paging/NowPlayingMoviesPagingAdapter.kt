package com.ktz.cinephilia.ui.screens.adapters.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ktz.cinephilia.BuildConfig
import com.ktz.cinephilia.R
import com.ktz.cinephilia.data.models.entities.moviesList.NowPlayingMoviesListEntity
import com.ktz.cinephilia.databinding.ListItemMoviesFullListBinding
import java.util.Objects

class NowPlayingMoviesPagingAdapter(val onMovieClicked: (NowPlayingMoviesListEntity) -> Unit) :
    PagingDataAdapter<NowPlayingMoviesListEntity, NowPlayingMoviesPagingAdapter.NowPlayingMoviesPagingViewHolder>(MOVIES_COMPARATOR) {

    override fun onBindViewHolder(holder: NowPlayingMoviesPagingViewHolder, position: Int) {
        val data = getItem(position)
        holder.bindData(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowPlayingMoviesPagingViewHolder {
        return NowPlayingMoviesPagingViewHolder(ListItemMoviesFullListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    companion object {
        private val MOVIES_COMPARATOR = object : DiffUtil.ItemCallback<NowPlayingMoviesListEntity>() {
            override fun areItemsTheSame(oldItem: NowPlayingMoviesListEntity, newItem: NowPlayingMoviesListEntity): Boolean =
                oldItem.originalTitle == newItem.originalTitle

            override fun areContentsTheSame(oldItem: NowPlayingMoviesListEntity, newItem: NowPlayingMoviesListEntity): Boolean =
                oldItem == newItem
        }
    }

    inner class NowPlayingMoviesPagingViewHolder(private val binding: ListItemMoviesFullListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: NowPlayingMoviesListEntity?) {
            with(binding) {
                Glide.with(ivPoster.context)
                    .load(BuildConfig.BASE_IMAGE_URL + data?.posterPath)
                    .placeholder(R.drawable.ic_cinephilia_place_holder)
                    .into(ivPoster)

                title = data?.title
                rating = data?.voteAverage.toString()

                this.root.setOnClickListener {
                    data?.let {
                        onMovieClicked.invoke(data)
                    }
                }
            }
        }
    }
}
