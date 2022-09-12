package com.example.scanit.domain.repository

import com.example.scanit.util.Constants
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.Response

interface RetrofitApiRepository {
    @Multipart
    @POST(Constants.ENDPOINT)
    suspend fun readProducts(
        @Part image: MultipartBody.Part
    ): Response<Any>
}