package com.example.upstox_assignment.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UserHolding(
    val symbol: String,
    val quantity: Int,
    val ltp: Double,
    val avgPrice: Double,
    val close: Double
)

@Serializable
data class Data(
    val userHolding: List<UserHolding>
)

@Serializable
data class ApiResponse(
    val data: Data
)




