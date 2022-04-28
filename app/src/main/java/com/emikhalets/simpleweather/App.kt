package com.emikhalets.simpleweather

import android.app.Application
import com.emikhalets.simpleweather.utils.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(applicationContext)
    }

    companion object {
        var prefs: Prefs? = null
    }
}