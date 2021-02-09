package com.fullstack.catawiki.repositories

import com.fullstack.catawiki.models.CatImageResponse
import com.fullstack.catawiki.models.CatItem
import com.fullstack.catawiki.models.CatResponseItem
import io.reactivex.Observable

interface VisualsRepository {
    fun getAllVisuals(): Observable<List<CatResponseItem>>
    fun getOneVisual(catId: String): Observable<CatImageResponse>
}