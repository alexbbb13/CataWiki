package com.fullstack.catawiki.di.screens

import com.fullstack.catawiki.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MyApplicationModule {
    @ContributesAndroidInjector
    internal abstract fun contributeActivityInjector(): MainActivity
}