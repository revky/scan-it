package com.example.scanit.domain.model

import java.util.*

data class Receipt(
    var id: String = "",
    var date: Date = Date(),
    var products: List<Product> = listOf(),
)
