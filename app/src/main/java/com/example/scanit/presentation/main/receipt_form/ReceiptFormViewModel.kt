package com.example.scanit.presentation.main.receipt_form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ReceiptFormViewModel @Inject constructor(
    // retrofit repo
    private val user : FirebaseUser?,
    private val receiptsRepository : BaseReceiptsRepository
) : ViewModel() {
    val retrofitReceiptState: StateFlow<Response<List<Product>>> = MutableStateFlow(
        Response.Success(
            listOf(
                Product("", "BULKI MLECZNE", 1, 569),
                Product("", "CHLEB RAZOWY", 1, 289),
                Product("", "BULKI MLECZNE", 1, 569),
                Product("", "BULKI MLECZNE", 1, 569),
                Product("", "BULKI MLECZNE", 1, 569),
                Product("", "BULKI MLECZNE", 1, 569),
                Product("", "BULKI MLECZNE", 1, 569),
                Product("", "BULKI MLECZNE", 1, 569),
            )
        )
    )

    private fun addReceipt() = viewModelScope.launch{
        val receipt = Receipt(
            idOwner = user!!.uid,
            date = Date()
        )
        receiptsRepository.addReceipt(receipt.toMap()).collect()
    }
}