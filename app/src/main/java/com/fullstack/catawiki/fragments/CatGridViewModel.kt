package com.fullstack.catawiki.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.bumptech.glide.Glide
import com.fullstack.catawiki.base.BaseViewModel
import com.fullstack.catawiki.interactors.VisualsInteractor;
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatGridViewModel @Inject constructor(private val interactor:VisualsInteractor) : BaseViewModel() {

    init {
        _showProgress.postValue(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }
}