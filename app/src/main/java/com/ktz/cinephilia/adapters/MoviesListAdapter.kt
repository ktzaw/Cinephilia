package com.ktz.cinephilia.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ktz.cinephilia.R
import com.ktz.cinephilia.data.model.Movies
import com.ktz.cinephilia.databinding.ListItemMoviesBinding
import com.ktz.cinephilia.utils.IMAGE_URL

class MoviesListAdapter(private val movieClicked: (Movies) -> Unit) :
    PagingDataAdapter<Movies, MoviesListAdapter.ViewHolder>(ListItemCallBack()) {

    class ListItemCallBack : DiffUtil.ItemCallback<Movies>() {
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindData(it)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class ViewHolder(private val binding: ListItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: Movies) = with(itemView) {

            Glide.with(context)
                .load(IMAGE_URL + data.posterPath)
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.movie_placeholder)
                .centerCrop()
                .into(binding.ivItemMovie)

            binding.tvMovieName.text = data.title

            val voteCount = "Rating : ${data.voteAverage} / 10"

            binding.tvRating.text = voteCount


            this.setOnClickListener {
                movieClicked.invoke(data)
            }

        }


    }

}

