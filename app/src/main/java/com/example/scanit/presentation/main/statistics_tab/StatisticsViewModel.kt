package com.example.scanit.presentation.main.statistics_tab

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.scanit.domain.model.Product
import com.example.scanit.domain.model.Receipt
import com.example.scanit.domain.repository.BaseReceiptsRepository
import com.example.scanit.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    receiptsRepository: BaseReceiptsRepository
) : ViewModel() {
    val receiptsState = receiptsRepository.receiptsState

    fun statColor(prodPrice: Double, allProductsPrice: Double): Color {

        val ratio: Double = prodPrice / (allProductsPrice)
        val color = when {
            ratio > 0.4 -> Color(0xffff0000)
            ratio < 0.2 -> Color(0xFF4CAF50)
            else -> {Color(0xFFBB86FC)}
        }
        return color
    }
}