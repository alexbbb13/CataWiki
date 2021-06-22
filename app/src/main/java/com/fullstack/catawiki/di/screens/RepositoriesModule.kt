package com.fullstack.catawiki.di.screens

import com.fullstack.catawiki.Constants
import com.fullstack.catawiki.api.RxCatawikiApi
import com.fullstack.catawiki.interactors.VisualsInteractor
import com.fullstack.catawiki.interactors.VisualsInteractorImpl
import com.fullstack.catawiki.network.AccessTokenInterceptor
import com.fullstack.catawiki.providers.CatNetworkProvider
import com.fullstack.catawiki.providers.LocalCache
import com.fullstack.catawiki.repositories.VisualsRepository
import com.fullstack.catawiki.repositories.VisualsRepositoryImpl
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Provides
    fun provideVisualsRepository(provider:CatNetworkProvider): VisualsRepository
            = VisualsRepositoryImpl(provider)
}

