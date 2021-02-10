package com.fullstack.catawiki.di.screens

import android.content.Context
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
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkStorageModule {

    @Provides
    @Singleton
    fun provideVisualsInteractor(repo:VisualsRepository, localCache: LocalCache): VisualsInteractor
            = VisualsInteractorImpl(repo, localCache)

    @Provides
    @Singleton
    fun provideVisualsRepository(provider:CatNetworkProvider): VisualsRepository
            = VisualsRepositoryImpl(provider)

    @Provides
    @Singleton
    fun provideNetworkProvider(api:RxCatawikiApi): CatNetworkProvider = CatNetworkProvider(api)

    @Provides
    @Singleton
    fun provideRxCatawikiApi():RxCatawikiApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.URL_API)
            .client(createAuthV2OkHttp3Client())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return  retrofit.create(RxCatawikiApi::class.java)
    }

    fun createAuthV2OkHttp3Client(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(AccessTokenInterceptor())
            .addNetworkInterceptor(provideLoggingInterceptor())

        return builder.build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }
}

