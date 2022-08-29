package com.example.scanit.data.repository

import com.example.scanit.domain.model.Product
import com.example.scanit.domain.model.Receipt
import com.example.scanit.domain.repository.BaseProductsRepository
import com.example.scanit.util.Constants
import com.example.scanit.util.Constants.PRODUCTS_REF
import com.example.scanit.util.Response
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
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
                }
            }
            .addOnFailureListener {
                trySend(Response.Failure(it)).isFailure
            }
        awaitClose()
    }
}