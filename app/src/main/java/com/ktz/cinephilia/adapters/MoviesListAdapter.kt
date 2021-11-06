package com.ktz.cinephilia.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ktz.cinephilia.R
import com.ktz.cinephilia.data.model.MovieResponse
import com.ktz.cinephilia.data.model.NowPlaying
import com.ktz.cinephilia.databinding.ListItemMoviesBinding
import com.ktz.cinephilia.utils.IMAGE_URL

class MoviesListAdapter(
    private val moviesList: MutableList<NowPlaying>
) : RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {

    fun setMovies(moviesList: List<NowPlaying>) {

        this.moviesList.clear()
        this.moviesList.addAll(moviesList)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ListItemMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = moviesList[position]
        holder.bindMovies(item)

    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class ViewHolder(private val binding: ListItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindMovies(data: NowPlaying) {

            Glide.with(itemView.rootView).load(IMAGE_URL + data.posterPath)
                .apply(
                    RequestOptions().placeholder(R.drawable.movie_placeholder).error(
                        R.drawable.movie_placeholder
                    )
                )
                .into(binding.ivItemMovie)

        }

    }

}