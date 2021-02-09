package com.fullstack.catawiki.fragments

import com.fullstack.catawiki.models.CatItem
import moxy.MvpView

interface CatsGridView : MvpView {
    fun setData(pics: List<CatItem>)
    fun setProgressBarVisibility(visible: Boolean)
    fun startViewImage(id: String)
}