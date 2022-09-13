package com.example.scanit.domain.repository

import com.example.scanit.domain.model.Product
import com.example.scanit.domain.model.ProductApi
import com.example.scanit.domain.model.Receipt
import com.example.scanit.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import java.io.File

interface BaseApiRepository {
    fun uploadImage(file: File): Flow<Response<List<Product>>>
}