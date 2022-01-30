package com.devpub.domain.model

sealed class Result {

    data class Success<T>(val data: T, val code: Int = 200) : Result()

    object Loading : Result()

    data class ApiError(val message: String, val code: Int) : Result()

    data class NetworkError(val throwable: Throwable) : Result()

    object NullResult : Result()
}