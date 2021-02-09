package com.fullstack.catawiki

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.facebook.stetho.Stetho
import com.ftb.test.pokemon.di.app.DaggerMyApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class BaseApplication:  Application(),
HasActivityInjector,
HasSupportFragmentInjector {


        companion object {
            lateinit var INSTANCE: BaseApplication
        }
        @Inject
        lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

        @Inject
        lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
        override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> = supportFragmentInjector

        override fun onCreate() {
            super.onCreate()
            INSTANCE = this
            DaggerMyApplicationComponent.builder().application(this).build().inject(this);
            if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this);
        }

        override fun activityInjector(): DispatchingAndroidInjector<Activity> {
            return dispatchingAndroidInjector
        }
}