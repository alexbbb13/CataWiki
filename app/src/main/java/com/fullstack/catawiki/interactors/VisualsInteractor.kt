package com.fullstack.catawiki.interactors

import com.fullstack.catawiki.api.ResultWrapper
import com.fullstack.catawiki.models.CatItem

interface VisualsInteractor {
    suspend fun getAllVisuals(): ResultWrapper<List<CatItem>>
    suspend fun getOneVisual(catId: String): ResultWrapper<CatItem?>
}