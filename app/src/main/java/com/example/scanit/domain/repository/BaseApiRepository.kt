package com.example.scanit.domain.repository

import com.example.scanit.domain.model.ProductApi
import com.example.scanit.util.Response
import kotlinx.coroutines.flow.StateFlow
import java.io.File

interface BaseApiRepository {
    suspend fun uploadImage(file: File): Boolean

    val uploadImageState: StateFlow<Response<List<ProductApi>>>
}