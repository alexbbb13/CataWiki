package com.fullstack.catawiki.api;

import com.fullstack.catawiki.models.CatImageResponse
import com.fullstack.catawiki.models.CatResponseItem
import io.reactivex.Observable
import retrofit2.http.*

interface RxCatawikiApi {

    @Headers("Content-Type: application/json")
    @GET("v1/breeds")
    fun getAllVisuals(): Observable<List<CatResponseItem>>

    @Headers("Content-Type: application/json")
    @GET("v1/images/{image_id}")
    fun getOneVisual(@Path("image_id") catId:String):Observable<CatImageResponse>
}