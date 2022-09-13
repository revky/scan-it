package com.example.scanit.util

sealed class Response<out T> {
    object Loading: Response<Nothing>()

    data class Success<T>(
        var data: T?
    ): Response<T>()

    data class Failure(
        val e: Exception
    ): Response<Nothing>()
}