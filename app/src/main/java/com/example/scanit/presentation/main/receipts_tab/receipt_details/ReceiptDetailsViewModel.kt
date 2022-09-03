package com.example.scanit.presentation.main.receipts_tab.receipt_details

import androidx.lifecycle.ViewModel
import com.example.scanit.domain.repository.BaseReceiptsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReceiptDetailsViewModel @Inject constructor(
    receiptsRepository: BaseReceiptsRepository
) : ViewModel() {
    val receiptsState = receiptsRepository.receiptsState

    init {
        receiptsRepository.fetchData()
    }
}