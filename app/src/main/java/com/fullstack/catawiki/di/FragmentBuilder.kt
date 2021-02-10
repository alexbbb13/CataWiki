package com.fullstack.catawiki.di

import com.fullstack.catawiki.di.screens.FragmentsModule
import com.fullstack.catawiki.fragments.CatInfoFragment
import com.fullstack.catawiki.fragments.CatsGridFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentBuilder {
    /**
     * Example:
     * @ContributesAndroidInjector(modules = [NameModule::class])
     * abstract fun bindNameFragment(): NameFragment
     * */

    @ContributesAndroidInjector(modules = [FragmentsModule::class])
    abstract fun bindCatsGridFragment(): CatsGridFragment

    @ContributesAndroidInjector(modules = [FragmentsModule::class])
    abstract fun bindCatInfoFragment(): CatInfoFragment
}
