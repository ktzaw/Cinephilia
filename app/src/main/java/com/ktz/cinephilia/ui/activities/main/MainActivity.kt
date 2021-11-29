package com.ktz.cinephilia.ui.activities.main

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ktz.cinephilia.R
import com.ktz.cinephilia.databinding.ActivityMainBinding
import com.ktz.cinephilia.databinding.LayoutToolbarViewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavView()

        Timber.d("Main Activity")

    }

    private fun setUpNavView() {
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_movies, R.id.navigation_search, R.id.navigation_favourite
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        if (isDarkTheme(activity = this)) {
            window.navigationBarColor = ContextCompat.getColor(this, R.color.statusBarColorNight)
        } else {
            window.navigationBarColor = ContextCompat.getColor(this, R.color.statusBarColor)
        }
    }

    private fun isDarkTheme(activity: Activity): Boolean {

        return activity.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

    }

}