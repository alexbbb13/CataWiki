package com.fullstack.catawiki.repositories

import com.fullstack.catawiki.models.CatImageResponse
import com.fullstack.catawiki.models.CatItem
import com.fullstack.catawiki.models.CatResponseItem
import com.fullstack.catawiki.providers.CatNetworkProvider
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class VisualsRepositoryImpl(val catNetworkProvider: CatNetworkProvider):VisualsRepository {
    override fun getAllVisuals(): Observable<List<CatResponseItem>> {
        return catNetworkProvider.getAllVisuals().subscribeOn(Schedulers.io())
    }

    override fun getOneVisual(catId: String): Observable<CatImageResponse> {
        return catNetworkProvider.getOneVisual(catId).subscribeOn(Schedulers.io())
    }
}