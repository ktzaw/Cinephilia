package com.ktz.cinephilia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ktz.cinephilia.R
import com.ktz.cinephilia.data.model.MovieDetail
import com.ktz.cinephilia.databinding.ListItemMoviesBinding
import com.ktz.cinephilia.utils.IMAGE_URL

class FavouriteMovieListAdapter(private val movies: MutableList<MovieDetail>) :
    RecyclerView.Adapter<FavouriteMovieListAdapter.MovieViewHolder>() {

    fun setMovies(movieList: List<MovieDetail>) {
        this.movies.clear()
        this.movies.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ListItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindData(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MovieViewHolder(val binding: ListItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: MovieDetail) = with(itemView) {

            Glide.with(context)
                .load(IMAGE_URL + data.posterPath)
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.movie_placeholder)
                .centerCrop()
                .into(binding.ivItemMovie)

            binding.tvMovieName.text = data.title

            val voteCount = "Rating : ${data.voteAverage} / 10"

            binding.tvRating.text = voteCount


        }

    }

}