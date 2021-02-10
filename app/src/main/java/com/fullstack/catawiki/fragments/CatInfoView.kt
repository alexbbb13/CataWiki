package com.fullstack.catawiki.fragments

import com.fullstack.catawiki.models.CatItem
import moxy.MvpView

interface CatInfoView : MvpView {
    fun setCatPicUrl(url: String)
    fun setCatName(name: String)
    fun setCatInfo(info: String)
    fun setProgressBarVisibility(visible: Boolean)
}