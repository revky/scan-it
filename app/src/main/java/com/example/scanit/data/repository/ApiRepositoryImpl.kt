package com.example.scanit.data.repository

import com.example.scanit.domain.model.ProductApi
import com.example.scanit.domain.repository.BaseApiRepository
import com.example.scanit.domain.repository.RetrofitApiRepository
import com.example.scanit.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiRepository: RetrofitApiRepository
):BaseApiRepository {
    override fun uploadImage(file: File): Flow<Response<List<ProductApi>>> = flow {
        try {
            emit(Response.Loading)
            val result = apiRepository.uploadPicture(
                image = MultipartBody.Part
                    .createFormData(
                        "image",
                        file.name,
                        file.asRequestBody()
                    )
            ).body()
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }
    override var imageUploadState: MutableStateFlow<Response<List<ProductApi>>> =
        MutableStateFlow(Response.Loading)

}
