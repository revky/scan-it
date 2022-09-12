package com.example.scanit.data.repository

import com.example.scanit.domain.model.ProductApi
import com.example.scanit.domain.repository.BaseApiRepository
import com.example.scanit.domain.repository.RetrofitApiRepository
import com.example.scanit.util.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiRepository: RetrofitApiRepository
) : BaseApiRepository {
    private val _imageUploadState: MutableStateFlow<Response<List<ProductApi>>> = MutableStateFlow(Response.Loading)
    override val uploadImageState: StateFlow<Response<List<ProductApi>>>
        get() = _imageUploadState

    override suspend fun uploadImage(file: File): Boolean {
        return try {
            val result = apiRepository.uploadPicture(
                image = MultipartBody.Part
                    .createFormData(
                        "image",
                        file.name,
                        file.asRequestBody()
                    )
            ).body()
            _imageUploadState.value = Response.Success(result)
            true
        } catch (e: Exception) {
            false
        }
    }
}
