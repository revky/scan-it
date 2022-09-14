package com.example.scanit.domain.repository

import com.example.scanit.domain.model.Product
import com.example.scanit.util.Response
import kotlinx.coroutines.flow.Flow

interface BaseProductsRepository {
    fun getProducts(idReceipt: String): Flow<Response<List<Product>>>

    fun addProduct(receiptId: String, productMap: Map<String, Any>): Flow<Response<Boolean>>
}