package com.example.scanit.domain.repository
import com.example.scanit.util.Response
import okhttp3.MultipartBody

interface BaseApiRepository {
    suspend fun readProducts(image: MultipartBody.Part): Response<Any>
}