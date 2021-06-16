package com.fullstack.catawiki.di.screens

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    //nothing is provided at activity level. either at application level or at fragment level

}