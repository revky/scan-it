package com.example.scanit.presentation.main.receipt_form

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scanit.data.repository.ApiRepositoryImpl
import com.example.scanit.domain.model.Product
import com.example.scanit.domain.model.Receipt
import com.example.scanit.domain.repository.BaseReceiptsRepository
import com.example.scanit.util.Response
import com.example.scanit.util.toMap
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ReceiptFormViewModel @Inject constructor(
    private val apiRepository: ApiRepositoryImpl,
    private val user: FirebaseUser?,
    private val receiptsRepository: BaseReceiptsRepository
) : ViewModel() {

    private val _imageUploadState: MutableStateFlow<Response<Boolean>> =
        MutableStateFlow(Response.Loading)
    val imageUploadState: StateFlow<Response<Boolean>>
        get() = _imageUploadState

    private var _products: SnapshotStateList<Product> = mutableStateListOf()
    val products: List<Product>
        get() = _products

    private fun addReceipt() = viewModelScope.launch {
        val receipt = Receipt(
            idOwner = user!!.uid,
            date = Date()
        )
        receiptsRepository.addReceipt(receipt.toMap()).collect()
    }

    fun uploadImage(file: File) = viewModelScope.launch {
        apiRepository.uploadImage(file).collect {
            when (it) {
                is Response.Loading -> _imageUploadState.value = Response.Loading
                is Response.Success -> {
                    _imageUploadState.value = Response.Success(false)
                    _products = it.data?.toMutableStateList() ?: mutableStateListOf()
                }
                is Response.Failure -> _imageUploadState.value =Response.Failure(it.e)
            }
        }
    }

    fun changeProduct(product: Product, name: String, price: Int, quantity: Int) = _products.find {
        it.id == product.id
    }?.let {
        it.name = name
        it.price = price
        it.quantity = quantity
    }

    fun deleteProduct(product: Product) {
        _products.remove(product)
    }

    fun addProduct() {
        val lastId = _products.last().id
        _products.add(Product(lastId + 1, "", 1, 0))
    }

//    class WellnessTask(
//        val id: Int,
//        val label: String,
//        var initialChecked: MutableState<Boolean> = mutableStateOf(false)
//    ) {
//        var checked by mutableStateOf(initialChecked)
//    }
}