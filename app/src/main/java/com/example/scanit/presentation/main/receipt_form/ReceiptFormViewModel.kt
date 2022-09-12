package com.example.scanit.presentation.main.receipt_form

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scanit.data.repository.ApiRepositoryImpl
import com.example.scanit.domain.model.ProductApi
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

    private val _imageUploadState: MutableStateFlow<Response<List<ProductApi>>> =
        MutableStateFlow(Response.Loading)

    val imageUploadState: StateFlow<Response<List<ProductApi>>>
        get() = _imageUploadState

    private fun addReceipt() = viewModelScope.launch {
        val receipt = Receipt(
            idOwner = user!!.uid,
            date = Date()
        )
        receiptsRepository.addReceipt(receipt.toMap()).collect()
    }

    fun uploadImage(file: File) = viewModelScope.launch {
        apiRepository.uploadImage(file).collect {
            _imageUploadState.value = it
            Log.e("tag",_imageUploadState.value.toString())
        }
    }
}