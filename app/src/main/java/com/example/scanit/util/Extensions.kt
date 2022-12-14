package com.example.scanit.util

import com.example.scanit.domain.model.Product
import com.example.scanit.domain.model.ProductApi
import com.example.scanit.domain.model.ProductState
import com.example.scanit.domain.model.Receipt
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun Date.toLongDateString(): String {
    val dateTime = LocalDateTime.ofInstant(this.toInstant(), ZoneId.systemDefault())
    return dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
}

fun Date.toDayOfTheWeek(): String {
    val dateTime = LocalDateTime.ofInstant(this.toInstant(), ZoneId.systemDefault())
    return dateTime.format(DateTimeFormatter.ofPattern("EEEE"))
}

fun Receipt.toMap(): Map<String, Any> {
    return mapOf<String, Any>(
        "idOwner" to this.idOwner,
        "date" to this.date
    )
}

fun ProductState.toMap(): Map<String, Any> {
    return mapOf<String, Any>(
        "name" to this.name,
        "quantity" to this.quantity,
        "price" to this.price
    )
}

fun ProductApi.toProductState(): ProductState {
    return ProductState(this.id, this.name, this.quantity, (this.price * 100).toInt())
}

fun ProductState.toProduct(): Product {
    return Product(this.id.toString(), this.name, this.quantity, this.price)
}