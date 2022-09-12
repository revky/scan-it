package com.example.scanit.domain.repository

import com.example.scanit.domain.model.Receipt
import com.example.scanit.util.Response
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


interface BaseReceiptsRepository {

    val receiptsState: StateFlow<Response<List<Receipt>>>

    fun fetchData(): Job

    fun getReceipts(userId: String) : Flow<Response<List<Receipt>>>

    fun addReceipt(receiptMap: Map<String, Any>): Flow<Response<Boolean>>

    fun deleteReceipt(receiptId: String): Flow<Response<Boolean>>
}
