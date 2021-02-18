package com.fullstack.catawiki.providers

import com.fullstack.catawiki.api.RxCatawikiApi
import com.fullstack.catawiki.models.CatImageResponse
import com.fullstack.catawiki.models.CatResponseItem
import retrofit2.Response

class CatNetworkProvider(val api: RxCatawikiApi) {
    suspend fun getAllVisuals(): List<CatResponseItem>? {
        return  convertResponse(api.getAllVisuals())
//        Log.i("Okhttp", "getAllVisuals = " + Gson().toJson(res))
//        return res
    }
    suspend fun getOneVisual(catId: String): CatImageResponse? {
        return convertResponse(api.getOneVisual(catId))
//        Log.i("Okhttp", "getOneVisual =  " + Gson().toJson(res))
//        return res
    }

    fun <T>convertResponse (response: Response<T>): T? {
        return response.body()
    }
}