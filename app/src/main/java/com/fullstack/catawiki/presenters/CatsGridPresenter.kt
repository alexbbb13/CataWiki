package com.fullstack.catawiki.presenters
import android.util.Log
import com.fullstack.catawiki.api.ResultWrapper
import com.fullstack.catawiki.fragments.CatsGridFragment
import com.fullstack.catawiki.fragments.CatsGridView
import com.fullstack.catawiki.interactors.VisualsInteractor
import com.fullstack.catawiki.models.CatItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CatsGridPresenter constructor(val interactor: VisualsInteractor) : MvpPresenter<CatsGridView>() {

    private fun onServerError(t: Throwable) {
        t.printStackTrace()
    }

    private fun onNetworkError() {
        //display "No network" message{
        viewState.setProgressBarVisibility(false)
        Log.w("CataWiki","Network error")
    }

    fun loadImages(fragment: CatsGridFragment) {
                        viewState.setProgressBarVisibility(true)
        GlobalScope.launch {
            val result = interactor.getAllVisuals()
            when(result) {
                is ResultWrapper.Success -> viewState.setData(result.value)
                is ResultWrapper.GenericError -> onServerError(result.throwable)
                is ResultWrapper.NetworkError -> onNetworkError()
            }
        }
     }

    fun onItemClick(item: CatItem) {
        viewState.startViewImage(item.id)
    }
}