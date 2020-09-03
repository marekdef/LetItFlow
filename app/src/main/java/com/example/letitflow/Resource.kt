package com.example.letitflow

sealed class Resource<T>(
    open val data: T? = null,
) {
    data class Success<T>(override val data: T) : Resource<T>(data)
    data class Loading<T>(override val data: T? = null) : Resource<T>(data)
    data class Error<T>(val throwable: Throwable, override val data: T? = null) : Resource<T>(data)
}