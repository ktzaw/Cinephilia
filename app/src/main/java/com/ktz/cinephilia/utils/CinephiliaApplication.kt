package com.ktz.cinephilia.utils

import android.app.Application
import com.ktz.cinephilia.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CinephiliaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        plantTimeberLog()
    }

    private fun plantTimeberLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
