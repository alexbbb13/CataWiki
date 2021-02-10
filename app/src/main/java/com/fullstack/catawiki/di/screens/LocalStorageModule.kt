package com.fullstack.catawiki.di.screens

import android.content.Context
import com.fullstack.catawiki.providers.LocalCache
import com.fullstack.catawiki.repositories.VisualsRepository
import com.fullstack.catawiki.repositories.VisualsRepositoryImpl
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import javax.inject.Singleton

@Module
class LocalStorageModule {

    @Provides
    @Singleton
    fun provideCache(): LocalCache = LocalCache()
}

