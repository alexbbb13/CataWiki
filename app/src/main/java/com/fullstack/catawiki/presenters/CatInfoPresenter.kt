package com.fullstack.catawiki.presenters
import android.Manifest
import com.fullstack.catawiki.fragments.CatInfoFragment
import com.fullstack.catawiki.fragments.CatInfoView
import com.fullstack.catawiki.interactors.VisualsInteractor
import com.fullstack.catawiki.models.CatItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CatInfoPresenter constructor(val interactor: VisualsInteractor) : MvpPresenter<CatInfoView>() {

    private fun onError(t: Throwable) {
        t.printStackTrace()
    }

    private fun onNewDataLoaded(data: CatItem) {
        data.pictureUrl?.let {viewState.setCatPicUrl(it)}
        viewState.setCatName(data.name)
        viewState.setCatInfo(data.description)
    }

    fun loadImages(args: CatInfoFragment.Arguments) {
                        viewState.setProgressBarVisibility(true)
                        interactor.getOneVisual(args.catId)
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete { viewState.setProgressBarVisibility(false) }
                            .subscribe({
                                catItem -> onNewDataLoaded(catItem)}
                                ,this::onError)
     }
}