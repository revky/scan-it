package com.example.scanit.data.repository

import com.example.scanit.domain.model.Receipt
import com.example.scanit.domain.repository.BaseProductsRepository
import com.example.scanit.domain.repository.BaseReceiptsRepository
import com.example.scanit.util.Constants
import com.example.scanit.util.Response
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReceiptsRepositoryImpl @Inject constructor(
    private val receiptsRef: CollectionReference,
    private val currentUser: FirebaseUser?,
    private val productsRepository: BaseProductsRepository
) : BaseReceiptsRepository {
    private val _receiptsState: MutableStateFlow<Response<List<Receipt>>> =
        MutableStateFlow(Response.Loading)
    override val receiptsState: StateFlow<Response<List<Receipt>>>
        get() = _receiptsState

    override fun fetchData(): Job = CoroutineScope(Dispatchers.IO).launch {
        if (currentUser != null) {
            getReceipts(currentUser.uid).collect { receiptsResponse ->
                if (receiptsResponse is Response.Success) {
                    receiptsResponse.data?.forEach { receipt ->
                        productsRepository.getProducts(receipt.id).collect { productsResponse ->
                            if (productsResponse is Response.Success) {
                                receipt.products = productsResponse.data ?: listOf()
                            }
                        }
                    }
                }
                _receiptsState.value = receiptsResponse
            }
        }
    }

    override fun getReceipts(userId: String): Flow<Response<List<Receipt>>> = callbackFlow {
        val receipts = mutableListOf<Receipt>()
        receiptsRef
            .whereEqualTo(Constants.USER_ID, userId)
            .get()
            .addOnSuccessListener {
                launch {
                    for (doc in it.documents) {
                        val receipt = doc.toObject(Receipt::class.java)
                        // TODO VALIDATION
                        if (receipt != null) {
                            receipt.id = doc.id
                            receipts.add(receipt)
                        }
                    }
                    trySend(Response.Success(receipts)).isSuccess
                    close()
                }
            }
            .addOnFailureListener {
                trySend(Response.Failure(it)).isFailure
                close()
            }
        awaitClose()
    }

    override fun addReceipt(receiptMap: Map<String, Any>): Flow<Response<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun deleteReceipt(receiptId: String): Flow<Response<Boolean>> {
        TODO("Not yet implemented")
    }
}
