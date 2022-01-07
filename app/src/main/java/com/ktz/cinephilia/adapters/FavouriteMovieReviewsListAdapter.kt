package com.ktz.cinephilia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ktz.cinephilia.data.model.ReviewResponses
import com.ktz.cinephilia.data.model.ReviewResult
import com.ktz.cinephilia.databinding.ListItemReviewsBinding

class FavouriteMovieReviewsListAdapter(

    private val movieReviews: MutableList<ReviewResult>
) : RecyclerView.Adapter<FavouriteMovieReviewsListAdapter.FavouriteReviewViewHolder>() {

    fun setMovies(reviewList: List<ReviewResult>) {
        this.movieReviews.clear()
        this.movieReviews.addAll(reviewList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteReviewViewHolder {
        return FavouriteReviewViewHolder(
            ListItemReviewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavouriteReviewViewHolder, position: Int) {
        holder.bindData(movieReviews[position])
    }

    override fun getItemCount(): Int {
        return movieReviews.size
    }

    inner class FavouriteReviewViewHolder(val binding: ListItemReviewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: ReviewResult) = with(itemView) {

            binding.tvMovieReview.text = data.content

            val author = "- ${data.author} -"
            binding.tvMovieReviewAuthor.text = author

        }

    }

}