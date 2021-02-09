package com.fullstack.catawiki.presenters
import android.Manifest
import com.fullstack.catawiki.fragments.CatsGridFragment
import com.fullstack.catawiki.fragments.CatsGridView
import com.fullstack.catawiki.interactors.VisualsInteractor
import com.fullstack.catawiki.models.CatItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CatsGridPresenter constructor(val interactor: VisualsInteractor) : MvpPresenter<CatsGridView>() {

    var selectedItems = emptySet<CatItem>()

    private fun onError(t: Throwable) {
        t.printStackTrace()
    }

    private fun onNewDataLoaded(data: List<CatItem>) {
        viewState.setData(data)
    }

    fun loadImages(fragment: CatsGridFragment) {
                        viewState.setProgressBarVisibility(true)
                        interactor.getAllVisuals()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete { viewState.setProgressBarVisibility(false) }
                            .subscribe({
                                viewState.setData(it)}
                                ,this::onError)
     }

    fun onItemClick(item: CatItem) {
        viewState.startViewImage(item.id)
    }
}