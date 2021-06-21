package com.fullstack.catawiki.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fullstack.catawiki.api.ResultWrapper
import com.fullstack.catawiki.base.BaseViewModel
import com.fullstack.catawiki.fragments.CatInfoFragment.Companion.ARGS
import com.fullstack.catawiki.interactors.VisualsInteractor
import com.fullstack.catawiki.models.CatItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatInfoViewModel @Inject constructor(private val interactor:VisualsInteractor) : BaseViewModel() {

    init {
        //_showProgress.postValue(true)
    }

    val catResult: MutableLiveData<ResultWrapper<CatItem?>> by lazy {
        MutableLiveData<ResultWrapper<CatItem?>>()
    }

    override fun init(arguments: Bundle?) {
        arguments?.let {
                val catId = (it.getSerializable(ARGS) as CatInfoFragment.Arguments).catId
                viewModelScope.launch {
                    catResult.value = interactor.getOneVisual(catId)
                    //_showProgress.postValue(false)
                }
            }
    }
}