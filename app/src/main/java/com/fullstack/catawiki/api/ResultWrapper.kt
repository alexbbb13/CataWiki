package com.fullstack.catawiki.api

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null, val throwable: Throwable): ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
}