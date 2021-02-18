package com.fullstack.catawiki.api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            throwable
                .printStackTrace()
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.GenericError(code, errorResponse, throwable)
                }
                else -> {
                    ResultWrapper.GenericError(null, null, throwable)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse {
    var ret = ErrorResponse("")
    try {
        throwable.response()?.errorBody()?.source()?.let {
                    val myType = object : TypeToken<ErrorResponse>() {}.type
        val br = BufferedReader(InputStreamReader(it.inputStream(), Charset.defaultCharset()))
        ret = Gson().fromJson<ErrorResponse>(br.readLine(), myType)
        }
    } catch (exception: Exception) {
        ret = ErrorResponse("Exception in convertErrorBody:"+exception.message)
    }
    return ret
}