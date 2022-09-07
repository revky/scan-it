package com.example.scanit.presentation.main.receipts_tab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scanit.domain.model.Receipt
import com.example.scanit.domain.repository.BaseReceiptsRepository
import com.example.scanit.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceiptsViewModel @Inject constructor(
    private val receiptsRepository: BaseReceiptsRepository
) : ViewModel() {
    val receiptsState = receiptsRepository.receiptsState

    fun deleteReceipt(receipt: Receipt?) = viewModelScope.launch {
        if (receipt != null) {
            receiptsRepository.deleteReceipt(receipt.id).collect {
                if (it is Response.Success) {
                    receiptsRepository.fetchData()
                }
            }
        }
    }

    init {
        receiptsRepository.fetchData()
    }
}