package com.fullstack.catawiki.di

import android.app.Application
import com.fullstack.catawiki.BaseApplication
import com.fullstack.catawiki.di.screens.AppModule
import com.fullstack.catawiki.di.screens.LocalStorageModule
import com.fullstack.catawiki.di.screens.MyApplicationModule
import com.fullstack.catawiki.di.screens.NetworkStorageModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class,
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,
    MyApplicationModule::class,
    LocalStorageModule::class,
    NetworkStorageModule::class

])
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
