package com.fullstack.catawiki.di.screens

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Lazy
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import javax.inject.Singleton

@Module
class AppModule {

    @ApplicationContext
    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context = application.applicationContext

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

    /**
     * Если Module абстрактный:
     * Пользуемся аннотацией @Binds
     * Dagger 2 позволяет нам предоставлять зависимости без наличия @Provides методов.
     * Это достигается путем наличия @Inject над конструктором у класса, который нам необходимо создать.
     *
     * Например:
     * @Binds
     * @Singleton
     * abstract fun bindContext(application: Application): Context
     *
     * @Binds
     * abstract fun bindErrorMessageFactory(errorMessageFactory: ErrorMessageImpl): ErrorMessage
     *
     * Используем @Provides только для статических методов:
     *
     * @Module
     * companion object {
     *     @JvmStatic
     *     @Provides
     *     fun provideContext(application: Application): Context = application.applicationContext
     * }
     *
     *
     *
     * If Module is not abstract:
     * @Provides
     * @Singleton
     * fun provideApplicationContext(application: Application): Context = application.applicationContext
     *
     * @Provides
     * fun provideErrorMessageFactory(context: Context): ErrorMessage = ErrorMessageImpl(context)
     */
}