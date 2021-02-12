package com.fullstack.catawiki.presenters
import android.util.Log
import com.fullstack.catawiki.api.ResultWrapper
import com.fullstack.catawiki.fragments.CatInfoFragment
import com.fullstack.catawiki.fragments.CatInfoView
import com.fullstack.catawiki.interactors.VisualsInteractor
import com.fullstack.catawiki.models.CatItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CatInfoPresenter constructor(val interactor: VisualsInteractor) : MvpPresenter<CatInfoView>() {

    private fun onServerError(t: Throwable) {
        viewState.setProgressBarVisibility(false)
        t.printStackTrace()
    }

    private fun onNetworkError() {
        //display "No network" message{
        viewState.setProgressBarVisibility(false)
        Log.w("CataWiki","Network error")
    }

    private fun onNewDataLoaded(data: CatItem) {
        viewState.setProgressBarVisibility(false)
        data.pictureUrl?.let {viewState.setCatPicUrl(it)}
        viewState.setCatName(data.name)
        viewState.setCatInfo(data.description)
    }

    fun loadImages(args: CatInfoFragment.Arguments) {
                        viewState.setProgressBarVisibility(true)
                        GlobalScope.launch {
                            val result = interactor.getOneVisual(args.catId)
                                    when(result) {
                                        is ResultWrapper.Success -> onNewDataLoaded(result.value)
                                        is ResultWrapper.GenericError -> onServerError(result.throwable)
                                        is ResultWrapper.NetworkError -> onNetworkError()
                                    }
                        }
     }
}