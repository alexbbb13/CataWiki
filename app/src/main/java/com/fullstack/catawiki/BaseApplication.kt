package com.fullstack.catawiki

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication:  Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}