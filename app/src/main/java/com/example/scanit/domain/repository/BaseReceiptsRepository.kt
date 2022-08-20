package com.example.scanit.domain.repository

import com.example.scanit.domain.model.Receipt
import com.example.scanit.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


interface BaseReceiptsRepository {

    val listOfReceipts: MutableStateFlow<Response<List<Receipt>>>

    fun getReceipts(userId: String) : Flow<Response<List<Receipt>>>

    fun addReceipt(receiptMap: Map<String, Any>): Flow<Response<Boolean>>

    fun deleteReceipt(receiptId: String): Flow<Response<Boolean>>
}



//
//
//val listOfReceipts = listOf<Receipt>().forEach{
//       it.products = repo.getProducts(it.id)
//}