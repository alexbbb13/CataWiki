package com.fullstack.catawiki.network

import com.fullstack.catawiki.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("x-api-key", Constants.API_KEY)
            .build()
        return chain.proceed(request)
    }
}