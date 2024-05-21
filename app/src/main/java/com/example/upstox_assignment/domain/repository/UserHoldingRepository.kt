package com.example.upstox_assignment.domain.repository

import android.util.Log
import com.example.upstox_assignment.data.api.ApiClient
import com.example.upstox_assignment.data.api.ApiService
import com.example.upstox_assignment.domain.models.ApiResponse
import com.example.upstox_assignment.domain.models.UserHolding
import javax.inject.Inject


class UserRepository @Inject constructor() {
    suspend fun getUserHoldings(): ApiResponse {
        return ApiClient().getUserHoldings()
        Log.d("APICALL", "user repo")
    }
}
