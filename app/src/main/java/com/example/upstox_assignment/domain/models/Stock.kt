package com.example.upstox_assignment.domain.models

data class Stock(
    val symbol: String,
    val quantity: Int,
    val ltp: Double,
    val avgPrice: Double,
    val close: Double,
    var isExpanded: Boolean = false,
    val currentValue: Double,
    val investedValue: Double,
    var pl: Double = 0.0,
    var plToday: Double = 0.0
)
