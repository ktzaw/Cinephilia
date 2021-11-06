package com.ktz.cinephilia.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ktz.cinephilia.NowPlayingFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val tabs = arrayOf("Now Playing", "Popular", "Upcoming")

    override fun getItemCount(): Int = tabs.size

    override fun createFragment(position: Int): Fragment {

        when (position) {

            0 -> return NowPlayingFragment()

            1 -> return NowPlayingFragment()

            2 -> return NowPlayingFragment()

        }

        return NowPlayingFragment()

    }

    fun getTitle(position: Int): String {
        return tabs[position]
    }

}