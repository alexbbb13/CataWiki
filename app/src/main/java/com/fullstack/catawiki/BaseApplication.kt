package com.fullstack.catawiki

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication:  Application() {
    override fun onCreate() {
        super.onCreate()

        //Timber.plant(Timber.DebugTree())
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}