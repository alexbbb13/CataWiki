package com.fullstack.catawiki.di.screens

import com.fullstack.catawiki.interactors.VisualsInteractor
import com.fullstack.catawiki.presenters.CatInfoPresenter
import com.fullstack.catawiki.presenters.CatsGridPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class FragmentsModule {

    @Provides
    fun provideCatsGridPresenter(interactor: VisualsInteractor) = CatsGridPresenter(interactor)

    @Provides
    fun provideCatInfoPresenter(interactor: VisualsInteractor) = CatInfoPresenter(interactor)

}