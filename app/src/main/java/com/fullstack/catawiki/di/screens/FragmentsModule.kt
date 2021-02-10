package com.fullstack.catawiki.di.screens

import com.fullstack.catawiki.interactors.VisualsInteractor
import com.fullstack.catawiki.presenters.CatsGridPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentsModule {

    @Provides
    fun provideCatsGridPresenter(interactor: VisualsInteractor) = CatsGridPresenter(interactor)

//    @Provides
//    fun provideCatDetailsPresenter(interactor: VisualsInteractor) = CatDetailsPresenter(interactor)

}