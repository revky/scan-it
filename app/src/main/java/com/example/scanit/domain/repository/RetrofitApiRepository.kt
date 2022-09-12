package com.example.scanit.domain.repository

import com.example.scanit.domain.model.Product
import com.example.scanit.util.Constants
import com.example.scanit.util.Response
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RetrofitApiRepository {
    @Multipart
    @POST(Constants.ENDPOINT)
    suspend fun uploadPicture(
        @Part image: MultipartBody.Part
    ):Response<List<Product>>
}