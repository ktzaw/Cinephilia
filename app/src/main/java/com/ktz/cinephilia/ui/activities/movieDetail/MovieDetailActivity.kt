package com.ktz.cinephilia.ui.activities.movieDetail

import android.app.Activity
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val viewModel: MovieDetailViewModel by viewModels()

    private var movieId: Int = 0

    lateinit var movieDetail: MovieDetail

    lateinit var database: MoviesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.customToolbarDetail.setNavigationOnClickListener { onBackPressed() }
        setUpToolbar()

        movieId = intent.extras?.get("MOVIE_ID") as Int
        Timber.d(movieId.toString())
        bindData(movieId)

        database = MoviesDatabase.getInstance(application)

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

        lifecycleScope.launch {

            binding.cbFavourite.isChecked = viewModel.isFavourite(movieId)

        }
        binding.cbFavourite.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {

                saveMovie(movieDetail)
                toastShort(this@MovieDetailActivity, "Added to Favourite")

            } else {

                toastShort(this@MovieDetailActivity, "Removed from favourite")
                removeMovie(movieId)
            }
        }
    }

    private fun bindData(id: Int) {


        lifecycleScope.launch {

            movieDetail = viewModel.loadMovieDetail(id)

            Glide.with(this@MovieDetailActivity)
                .load(IMAGE_URL + movieDetail.posterPath)
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.movie_placeholder)
                .centerCrop()
                .into(binding.ivDetailMovieImage)

            binding.tvMovieName.text = movieDetail.title

            if (movieDetail.overview.isEmpty()) {
                binding.tvMovieOverview.text = "No Overview available"
            } else {
                binding.tvMovieOverview.text = movieDetail.overview
            }

            binding.tvReleaseDate.text = movieDetail.releaseDate



            setUpFavourite()

            bindTrailer(id)

        }
    }

    private fun saveMovie(movieDetail: MovieDetail) {

        lifecycleScope.launch {
            database.withTransaction {

                database.favouriteDao.insertFavouriteMovie(movieDetail)

            }
        }
    }

    private fun removeMovie(id: Int) {

        lifecycleScope.launch {
            database.withTransaction {
                database.favouriteDao.delete(id)
            }
        }

    }

    private suspend fun bindTrailer(id: Int) {

        val movieTrailer = viewModel.loadMovieTrailer(id)

        if (movieTrailer.results.isNotEmpty()) {
            movieTrailer.results.map {

                if (it.official && it.type == "Trailer") {

                    handlePlayer(it.key)

                } else if (it.type == "Trailer") {

                    handlePlayer(it.key)

                } else {

                    handlePlayer(it.key)

                }
            }
        } else {

            binding.cardYoutubePlayer.visibility = View.GONE
            binding.youtubePlayerView.visibility = View.GONE

        }
    }

    private fun handlePlayer(youtubeKey: String) {

        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {

            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.cueVideo(youtubeKey, 0f)
                Timber.d("Player Key $youtubeKey")
            }

        })


    }

    override fun onDestroy() {
        super.onDestroy()
        binding.youtubePlayerView.release()
    }

}