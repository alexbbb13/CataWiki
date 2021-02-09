package com.fullstack.catawiki.providers

import android.util.Log
import com.fullstack.catawiki.api.RxCatawikiApi
import com.fullstack.catawiki.models.CatImageResponse
import com.fullstack.catawiki.models.CatItem
import com.fullstack.catawiki.models.CatResponseItem
import com.google.gson.Gson
import io.reactivex.Observable

class CatNetworkProvider(val api: RxCatawikiApi) {
    fun getAllVisuals(): Observable<List<CatResponseItem>> {
        return api.getAllVisuals()
            .doOnNext {
                Log.i("Okhttp", "getAllVisuals = " + Gson().toJson(it))
            }
    }
    fun getOneVisual(catId: String): Observable<CatImageResponse> {
        return api.getOneVisual(catId)
            .doOnNext {
                Log.i("Okhttp", "getOneVisual = " + Gson().toJson(it))
            }
    }
}