package com.fullstack.catawiki.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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

    val _catResult: MutableLiveData<ResultWrapper<CatItem?>> by lazy {
        MutableLiveData<ResultWrapper<CatItem?>>()
    }

    val catName: LiveData<String> = Transformations.map(_catResult, {r ->
        if(r is ResultWrapper.Success) {
            r.value?.name
        } else {
            ""
        }
    })
    val catPicUrl: LiveData<String> = Transformations.map(_catResult, { r ->
        if (r is ResultWrapper.Success) {
            r.value?.pictureUrl
        } else {
            ""
        }
    })
    val catDetails: LiveData<String> = Transformations.map(_catResult, {r ->
        if(r is ResultWrapper.Success) {
            Log.d("doxxxtor", "CatDetails wrapper")
            r.value?.description
        } else {
            ""
        }
    })

    override fun init(arguments: Bundle?) {
        arguments?.let {
                val catId = (it.getSerializable(ARGS) as CatInfoFragment.Arguments).catId
                viewModelScope.launch {
                    val result = interactor.getOneVisual(catId)
                    when(result) {
                        is ResultWrapper.Success -> {
                            _catResult.postValue(result)
                        }
                    }
                    //_showProgress.postValue(false)
                }
            }
    }
}