package com.fullstack.catawiki.di

import com.fullstack.catawiki.activities.MainActivity
import com.fullstack.catawiki.di.screens.ActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity

}