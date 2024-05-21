package com.example.upstox_assignment.data.api



import android.util.Log
import com.example.upstox_assignment.domain.models.ApiResponse
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("/")
    suspend fun getUserHoldings(): ApiResponse
}

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://35dee773a9ec441e9f38d5fc249406ce.api.mockbin.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}

class ApiClient {
    suspend fun getUserHoldings(): ApiResponse {
        return RetrofitClient.apiService.getUserHoldings()
        Log.d("APICALL", "apiClient reached")
    }
}
