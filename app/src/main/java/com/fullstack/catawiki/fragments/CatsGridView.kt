package com.fullstack.catawiki.fragments

import com.fullstack.catawiki.models.CatItem
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CatsGridView : MvpView {
    fun setData(pics: List<CatItem>)
    fun setProgressBarVisibility(visible: Boolean)
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun startViewImage(id: String)
}