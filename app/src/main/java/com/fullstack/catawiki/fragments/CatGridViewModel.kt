package com.fullstack.catawiki.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.fullstack.catawiki.R
import com.fullstack.catawiki.api.ResultWrapper
import com.fullstack.catawiki.base.BaseViewModel
import com.fullstack.catawiki.interactors.VisualsInteractor;
import com.fullstack.catawiki.models.CatItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CatGridViewModel @Inject constructor(private val interactor:VisualsInteractor) : BaseViewModel() {

    init {
        //_showProgress.postValue(true)
    }

    val catListResult: MutableLiveData<ResultWrapper<List<CatItem>>> by lazy {
        MutableLiveData<ResultWrapper<List<CatItem>>>()
    }

    override fun init(arguments: Bundle?) {
             viewModelScope.launch {
                 catListResult.value = interactor.getAllVisuals()
                 _showProgress.postValue(false)
            }
    }

    fun startViewImage(cat: CatItem, supportFragmentManager: FragmentManager?) {
        supportFragmentManager?.let {
            it.beginTransaction()
                .add(R.id.fragment_container, CatInfoFragment.getInstance(cat.id))
                .addToBackStack("cat_image")
                .commit()
        }
    }
}