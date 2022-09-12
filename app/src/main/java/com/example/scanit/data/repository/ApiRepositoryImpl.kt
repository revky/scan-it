package com.example.scanit.data.repository

import android.util.Log
import com.example.scanit.domain.model.ProductApi
import com.example.scanit.domain.repository.RetrofitApiRepository
import com.example.scanit.util.Response
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiRepository: RetrofitApiRepository
){
    suspend fun uploadImage(file: File):Boolean{
        val response = apiRepository.uploadPicture(
            image = MultipartBody.Part
                .createFormData(
                    "image",
                    file.name,
                    file.asRequestBody()
                )
        )
        val result = response.body()
        _productsResponse = Response.Success(result)
        return true
    }
    private var _productsResponse: Response<List<ProductApi>> = Response.Loading
    val productsResponse: Response<*>
        get() = _productsResponse
}

