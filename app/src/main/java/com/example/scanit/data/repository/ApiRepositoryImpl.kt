package com.example.scanit.data.repository

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
        return try {
            apiRepository.uploadPicture(
                image = MultipartBody.Part
                    .createFormData(
                        "image",
                        file.name,
                        file.asRequestBody()
                    )
            )
            true
        }catch (e: Exception){
            false
        }
    }

    private var _productsResponse: Response<Any> = Response.Loading
    val productsResponse: Response<*>
        get() = _productsResponse
}

