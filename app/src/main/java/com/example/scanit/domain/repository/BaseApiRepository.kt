package com.example.scanit.domain.repository

import com.example.scanit.domain.model.Product
import com.example.scanit.domain.model.ProductState
import com.example.scanit.util.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface BaseApiRepository {
    fun uploadImage(file: File): Flow<Response<List<ProductState>>>
}