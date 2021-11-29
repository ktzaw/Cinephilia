package com.ktz.cinephilia.ui.fragment.favourite

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.ktz.cinephilia.databinding.ActivityFavouriteDetailBinding
import com.ktz.cinephilia.utils.IMAGE_URL
import com.ktz.cinephilia.utils.toastShort
import com.ktz.cinephilia.viewmodels.FavouriteMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.app.NavUtils
import com.ktz.cinephilia.R


@AndroidEntryPoint
class FavouriteMovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavouriteDetailBinding

    private val viewModel: FavouriteMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.extras?.get("MOVIE_ID") as Int

        binding.customToolbarFavouriteDetail.setNavigationOnClickListener { onBackPressed() }

        bindData(movieId)

    }

    private fun bindData(movieId: Int) {

        viewModel.getFavouriteMovies().observe(this, Observer { moviesList ->

            moviesList.map { movieDetail ->

                if (movieId == movieDetail.id) {

                    Glide.with(this)
                        .load(IMAGE_URL + movieDetail.posterPath)
                        .placeholder(R.drawable.movie_placeholder)
                        .error(R.drawable.movie_placeholder)
                        .centerCrop()
                        .into(binding.ivDetailMovieImage)

                    binding.tvMovieNameDetail.text = movieDetail.title

                    movieDetail.genres.map {
                        Chip(this).apply {
                            text = it.name
                            setChipBackgroundColorResource(R.color.colorAccent)
                            setTextColor(ContextCompat.getColor(context, R.color.white))
                            isClickable = false
                            isCheckable = false
                        }
                    }.forEach { binding.cgGenreList.addView(it) }

                    binding.tvMovieOverview.text = movieDetail.overview
                    binding.tvReleaseDate.text = movieDetail.releaseDate

                    binding.cbFavouriteDetail.isChecked = true

                    binding.cbFavouriteDetail.setOnCheckedChangeListener { _, isChecked ->

                        if (!isChecked) {
                            val builder = AlertDialog.Builder(this)

                            builder.setMessage("Are you sure you want to remove ${movieDetail.title} from favourite?")
                            builder.setPositiveButton("Yes") { _, _ ->
                                viewModel.removeFromFavourite(
                                    movieId
                                )
                                onBackPressed()
                            }
                            builder.setNegativeButton("No") { _, _ -> }

                            val dialog = builder.create()
                            dialog.setCancelable(false)
                            dialog.show()
                            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                                .setTextColor(ContextCompat.getColor(this, R.color.black));
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                .setTextColor(ContextCompat.getColor(this, R.color.black))
                        } else {

                            toastShort(this, "Already removed from favourite")
                            binding.cbFavouriteDetail.isChecked = false

                        }

                    }

                }
            }

        })

    }

}