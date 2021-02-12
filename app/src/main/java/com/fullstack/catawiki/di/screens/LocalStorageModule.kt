package com.fullstack.catawiki.di.screens

import com.fullstack.catawiki.providers.LocalCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalStorageModule {

    @Provides
    @Singleton
    fun provideCache(): LocalCache = LocalCache()
}

