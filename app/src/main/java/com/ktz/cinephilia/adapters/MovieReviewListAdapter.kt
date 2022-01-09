package com.ktz.cinephilia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ktz.cinephilia.data.model.ReviewResponses
import com.ktz.cinephilia.databinding.ListItemReviewsBinding

class MovieReviewListAdapter(
    private val movieReviews: MutableList<ReviewResponses>
) :
    RecyclerView.Adapter<MovieReviewListAdapter.ReviewViewHolder>() {

    fun setMovies(reviewList: List<ReviewResponses>) {
        this.movieReviews.clear()
        this.movieReviews.addAll(reviewList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            ListItemReviewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bindData(movieReviews[position])
    }

    override fun getItemCount(): Int {
        return movieReviews.size
    }

    inner class ReviewViewHolder(val binding: ListItemReviewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: ReviewResponses) = with(itemView) {

            if (data.results.isEmpty()){

                binding.tvMovieReview.text = "No reviews available"

            }else{

            binding.tvMovieReview.text = data.results[0].content

            val author = "- ${data.results[0].author} -"
            binding.tvMovieReviewAuthor.text = author
        }}
    }

}

