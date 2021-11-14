package com.ktz.cinephilia.ui.activities.movieDetail

import android.app.Activity
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.room.withTransaction
import com.bumptech.glide.Glide
import com.ktz.cinephilia.R
import com.ktz.cinephilia.data.db.MoviesDatabase
import com.ktz.cinephilia.data.model.MovieDetail
import com.ktz.cinephilia.databinding.ActivityMovieDetailBinding
import com.ktz.cinephilia.utils.IMAGE_URL
import com.ktz.cinephilia.utils.toastShort
import com.ktz.cinephilia.viewmodels.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.concurrent.thread
import kotlin.properties.Delegates

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val viewModel: MovieDetailViewModel by viewModels()

    private var movieId by Delegates.notNull<Int>()

    lateinit var movieDetail: MovieDetail

    lateinit var database: MoviesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customToolbarDetail.setNavigationOnClickListener { onBackPressed() }
        setUpToolbar()

        movieId = intent.extras?.get("MOVIE_ID") as Int

        database = MoviesDatabase.getInstance(application)
        setUpFavourite()
        bindData()

    }

    private fun setUpToolbar() {

        if (isDarkTheme(activity = this)) {

            binding.customToolbarDetail.setNavigationIcon(R.drawable.ic_baseline_arrow_back_white_24)


        } else {

            binding.customToolbarDetail.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        }

    }

    private fun isDarkTheme(activity: Activity): Boolean {

        return activity.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

    }

    private fun setUpFavourite() {

        binding.cbFavourite.isChecked = isFavourite()

        binding.cbFavourite.setOnCheckedChangeListener { button, isChecked ->

            if (isChecked) {

                saveMovie(movieDetail)
                toastShort(this@MovieDetailActivity, "Added to Favourite")

            } else {

                toastShort(this@MovieDetailActivity, "Removed from favourite")
                removeMovie(movieId)
            }
        }
    }

    private fun bindData() {


        lifecycleScope.launch {

            movieDetail = viewModel.loadMovieDetail(movieId)

            Glide.with(this@MovieDetailActivity)
                .load(IMAGE_URL + viewModel.loadMovieDetail(movieId).posterPath)
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.movie_placeholder)
                .centerCrop()
                .into(binding.ivDetailMovieImage)

            binding.tvMovieName.text = movieDetail.title
            binding.tvMovieOverview.text = movieDetail.overview
            binding.tvDirectorName.text = " ${movieDetail.releaseDate}"


        }
    }

    private fun saveMovie(movieDetail: MovieDetail) {

        lifecycleScope.launchWhenCreated {
            database.withTransaction {

                database.favouriteDao.insertFavouriteMovie(movieDetail)

            }
        }
    }

    private fun removeMovie(id: Int) {

        lifecycleScope.launchWhenCreated {
            database.withTransaction {
                database.favouriteDao.delete(id)
            }
        }

    }

    private fun isFavourite(): Boolean {

        var isFavouriteMovie = false

        thread {
            isFavouriteMovie = database.favouriteDao.isFavourite(movieId)
        }

        return isFavouriteMovie

    }

}