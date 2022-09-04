package com.example.scanit.presentation.main.receipt_form

import androidx.lifecycle.ViewModel
import com.example.scanit.domain.model.Product
import com.example.scanit.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ReceiptFormViewModel @Inject constructor(
    // retrofit repo
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
}