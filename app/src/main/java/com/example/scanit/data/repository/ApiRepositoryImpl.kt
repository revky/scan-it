package com.example.scanit.data.repository

import com.example.scanit.domain.model.Product
import com.example.scanit.domain.model.ProductState
import com.example.scanit.domain.repository.BaseApiRepository
import com.example.scanit.domain.repository.RetrofitApiRepository
import com.example.scanit.util.Response
import com.example.scanit.util.toProductState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiRepository: RetrofitApiRepository
):BaseApiRepository {
    override fun uploadImage(file: File): Flow<Response<List<ProductState>>> = flow {
        try {
            emit(Response.Loading)
            val resultApi = apiRepository.uploadPicture(
                image = MultipartBody.Part
                    .createFormData(
                        "image",
                        file.name,
                        file.asRequestBody()
                    )
            ).body()
            val result = resultApi!!.map {
                it.toProductState()
            }
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }
}
