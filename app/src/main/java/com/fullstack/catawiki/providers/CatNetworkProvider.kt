package com.fullstack.catawiki.providers

import com.fullstack.catawiki.api.RxCatawikiApi
import com.fullstack.catawiki.models.CatImageResponse
import com.fullstack.catawiki.models.CatResponseItem

class CatNetworkProvider(val api: RxCatawikiApi) {
    suspend fun getAllVisuals(): List<CatResponseItem> {
        return  api.getAllVisuals()
//        Log.i("Okhttp", "getAllVisuals = " + Gson().toJson(res))
//        return res
    }
    suspend fun getOneVisual(catId: String): CatImageResponse {
        return api.getOneVisual(catId)
//        Log.i("Okhttp", "getOneVisual =  " + Gson().toJson(res))
//        return res
    }
}