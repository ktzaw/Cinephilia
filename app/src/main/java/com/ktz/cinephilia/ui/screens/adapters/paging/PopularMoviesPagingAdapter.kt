package com.ktz.cinephilia.ui.screens.adapters.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ktz.cinephilia.BuildConfig
import com.ktz.cinephilia.R
import com.ktz.cinephilia.data.models.entities.moviesList.PopularMoviesListEntity
import com.ktz.cinephilia.databinding.ListItemMoviesFullListBinding

class PopularMoviesPagingAdapter(val onMovieClicked: (PopularMoviesListEntity) -> Unit) :
    PagingDataAdapter<PopularMoviesListEntity, PopularMoviesPagingAdapter.PopularMoviesViewHolder>(MOVIES_COMPARATOR) {

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val data = getItem(position)
        holder.bindData(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(ListItemMoviesFullListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    companion object {
        private val MOVIES_COMPARATOR = object : DiffUtil.ItemCallback<PopularMoviesListEntity>() {
            override fun areItemsTheSame(oldItem: PopularMoviesListEntity, newItem: PopularMoviesListEntity): Boolean =
                oldItem.originalTitle == newItem.originalTitle

            override fun areContentsTheSame(oldItem: PopularMoviesListEntity, newItem: PopularMoviesListEntity): Boolean =
                oldItem == newItem
        }
    }

    inner class PopularMoviesViewHolder(private val binding: ListItemMoviesFullListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: PopularMoviesListEntity?) {
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
