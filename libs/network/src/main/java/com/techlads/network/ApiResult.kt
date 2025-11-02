package com.techlads.network

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
}

fun <T> ApiResult<T>.getOrElse() : T? {
    return when (this) {
        is ApiResult.Success -> this.data
        is ApiResult.Error -> null
    }
}