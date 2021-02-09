package com.fullstack.catawiki.api;

import com.fullstack.catawiki.models.CatImageResponse
import com.fullstack.catawiki.models.CatResponseItem
import io.reactivex.Observable
import retrofit2.http.*

interface RxCatawikiApi {

    @Headers("Content-Type: application/json")
    @GET("v2/sbp/settings/getTransferStatus")
    fun getAllVisuals(): Observable<List<CatResponseItem>>

    @Headers("Content-Type: application/json")
    @GET("v2/sbp/settings/switchTransferStatus/{id}")
    fun getOneVisual(@Path("id") catId:String):Observable<CatImageResponse>
}