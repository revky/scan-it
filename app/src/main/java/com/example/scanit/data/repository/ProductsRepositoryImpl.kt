package com.example.scanit.data.repository

import com.example.scanit.domain.model.Product
import com.example.scanit.domain.repository.BaseProductsRepository
import com.example.scanit.util.Constants.PRODUCTS_REF
import com.example.scanit.util.Response
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val receiptsRef: CollectionReference
):BaseProductsRepository {
    override fun getProducts(idReceipt: String): Flow<Response<List<Product>>> = callbackFlow {
        val productsRef = receiptsRef.document(idReceipt).collection(PRODUCTS_REF)
        val products = mutableListOf<Product>()
        productsRef
            .get()
            .addOnSuccessListener {
                launch {
                    for (doc in it.documents) {
                        val product = doc.toObject(Product::class.java)
                        // TODO VALIDATION
                        if (product != null) {
                            product.id = doc.id
                            products.add(product)
                        }
                    }
                    trySend(Response.Success(products)).isSuccess
                    close()
                }
            }
            .addOnFailureListener {
                trySend(Response.Failure(it)).isFailure
                close()
            }
        awaitClose()
    }

    override fun addProduct(
        receiptId: String,
        productMap: Map<String, Any>
    ): Flow<Response<Boolean>> =
        flow {
            try {
                emit(Response.Loading)
                val productsRef = receiptsRef.document(receiptId).collection(PRODUCTS_REF)
                productsRef.add(productMap).await()
                emit(Response.Success(true))
            } catch (e: Exception) {
                emit(Response.Failure(e))
            }
        }
}