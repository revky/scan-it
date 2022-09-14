package com.example.scanit.domain.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ProductState(
    var id: Int = -1,
    initName: String = "",
    initQuantity: Int = 0,
    initPrice: Int = 0
) {
    var name by mutableStateOf(initName)
    var quantity by mutableStateOf(initQuantity)
    var price by mutableStateOf(initPrice)
}