package com.fullstack.catawiki.di.screens

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

//    @Provides
//    @Singleton
//    fun provideAppRoom(@ApplicationContext context: Context): AppRoomDatabase {
//        return Room.databaseBuilder(
//                context.applicationContext,
//                AppRoomDatabase::class.java, "app_database")
//                .fallbackToDestructiveMigration() // Cleans the database without the migrations
//                .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideAppRoomSingle(appRoomLazy: Lazy<AppRoomDatabase>): Single<AppRoomDatabase> {
//        return Single.fromCallable { appRoomLazy.get() }
//    }

}