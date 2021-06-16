package com.fullstack.catawiki.di.screens

import com.fullstack.catawiki.providers.LocalCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalStorageModule {

    @Provides
    fun provideCache(): LocalCache = LocalCache()
}

