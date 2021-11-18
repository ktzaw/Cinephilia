package com.ktz.cinephilia.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ktz.cinephilia.service.MovieType
import com.ktz.cinephilia.ui.fragment.favourite.FavouriteMoviesFragment
import com.ktz.cinephilia.ui.fragment.movies.nowPlaying.NowPlayingFragment
import com.ktz.cinephilia.ui.fragment.movies.popular.PopularFragment
import com.ktz.cinephilia.ui.fragment.movies.upcoming.UpcomingFragment
import com.ktz.cinephilia.utils.isNetworkAvailable

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val context: Context
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val tabs = arrayOf("Now Playing", "Popular", "Upcoming")

    override fun getItemCount(): Int = tabs.size

    override fun createFragment(position: Int): Fragment {

        when (position) {

            0 -> {
                MovieType.NOW_PLAYING
                return NowPlayingFragment().newInstance()
            }

            1 -> {
                MovieType.POPULAR
                return PopularFragment().newInstance()
            }

            2 -> {
                MovieType.UPCOMING
                return UpcomingFragment().newInstance()
            }

        }

        return NowPlayingFragment().newInstance()

    }

    fun getTitle(position: Int): String {
        return tabs[position]
    }

}