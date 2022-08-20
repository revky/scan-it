package com.example.scanit.util

import com.example.scanit.domain.model.Product
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

fun Product.toMap(): Map<String, Any> {
    return mapOf<String, Any>(

    )
}
//
//
//val tripDays = currentTrip?.tripDays?.sortedBy { tripDay -> tripDay.date }
//items(tripDays ?: listOf()) { tripDay ->