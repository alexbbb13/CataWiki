package com.fullstack.catawiki.api;

import com.fullstack.catawiki.models.CatImageResponse
import com.fullstack.catawiki.models.CatResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RxCatawikiApi {

    @Headers("Content-Type: application/json")
    @GET("v1/breeds")
    suspend fun getAllVisuals(): List<CatResponseItem>

    @Headers("Content-Type: application/json")
    @GET("v1/images/{image_id}")
    suspend fun getOneVisual(@Path("image_id") catId:String): CatImageResponse
}