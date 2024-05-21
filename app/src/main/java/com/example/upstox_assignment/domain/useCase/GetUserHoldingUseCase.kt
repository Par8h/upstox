package com.example.upstox_assignment.domain.useCase

import android.util.Log
import com.example.upstox_assignment.domain.models.ApiResponse
import com.example.upstox_assignment.domain.repository.UserRepository
import javax.inject.Inject


open class GetUserHoldingsUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun execute(): ApiResponse {
        return userRepository.getUserHoldings()
        Log.d("APICALL", "usecase execute")
    }
}
