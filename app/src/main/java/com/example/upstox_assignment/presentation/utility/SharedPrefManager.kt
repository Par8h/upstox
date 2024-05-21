package com.example.upstox_assignment.presentation.utility

import android.content.Context
import android.content.SharedPreferences
import com.example.upstox_assignment.domain.models.Stock
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("stocks_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    // Save the list of stocks to SharedPreferences
    fun saveStockList(stockList: List<Stock>) {
        val editor = sharedPreferences.edit()
        val jsonString = gson.toJson(stockList)
        editor.putString("stock_list", jsonString)
        editor.apply()
    }

    // Retrieve the list of stocks from SharedPreferences
    fun getStockList(): List<Stock> {
        val jsonString = sharedPreferences.getString("stock_list", null)
        if (jsonString.isNullOrEmpty()) {
            return emptyList()
        }
        val type = object : TypeToken<List<Stock>>() {}.type
        return gson.fromJson(jsonString, type)
    }
}
