package com.example.upstox_assignment.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkUserHolding(
    val symbol: String,
    val quantity: Int,
    val ltp: Double,
    val avgPrice: Double,
    val close: Double
)

@Serializable
data class NetworkUserHoldingsResponse(
    val data: NetworkUserHoldingsData
)

@Serializable
data class NetworkUserHoldingsData(
    val userHolding: List<NetworkUserHolding>
)
