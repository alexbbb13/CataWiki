package com.fullstack.catawiki.interactors

import com.fullstack.catawiki.models.CatItem
import io.reactivex.Observable

interface VisualsInteractor {
    fun getAllVisuals(): Observable<List<CatItem>>
    fun getOneVisual(catId: String): Observable<CatItem>
}