package com.example.scanit.data.repository

import com.example.scanit.domain.model.ProductApi
import com.example.scanit.domain.repository.RetrofitApiRepository
import com.example.scanit.util.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiRepository: RetrofitApiRepository
) {
    suspend fun uploadImage(file: File) = flow {
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

    private var _receiptsState: MutableStateFlow<Response<List<ProductApi>>> =
        MutableStateFlow(Response.Loading)

    val receiptsState: StateFlow<Response<List<ProductApi>>>
        get() = _receiptsState
}

