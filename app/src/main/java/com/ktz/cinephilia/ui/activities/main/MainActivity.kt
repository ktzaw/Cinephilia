package com.ktz.cinephilia.ui.activities.main

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ktz.cinephilia.R
import com.ktz.cinephilia.databinding.ActivityMainBinding
import com.ktz.cinephilia.databinding.LayoutToolbarViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbarBinding: LayoutToolbarViewBinding
    private val toolbar: Toolbar by lazy { toolbarBinding.customToolbar }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbarBinding = LayoutToolbarViewBinding.inflate(layoutInflater)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        window.navigationBarColor = getColor(R.color.colorPrimaryDark)

    }
}