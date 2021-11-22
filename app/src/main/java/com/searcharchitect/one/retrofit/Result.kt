package com.searcharchitect.one.retrofit

import okhttp3.Headers

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T? = null, val headers: Headers? = null) : Result<T>()
    data class Error(val failure: Failure) : Result<Nothing>()

}