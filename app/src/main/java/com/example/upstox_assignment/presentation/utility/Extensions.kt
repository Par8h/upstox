package com.example.upstox_assignment.presentation.utility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import java.text.DecimalFormat

object Extensions {

    fun Double.formatToTwoDecimalPlacesWithRupeeSymbol(): String {
        val formattedAmount = String.format("%.2f", this)
        return if (this < 0) {
            "-\u20B9${formattedAmount.substring(1)}"
        } else {
            "\u20B9$formattedAmount"
        }
    }

    fun LiveData<Double>.toDoubleString(): LiveData<String> {
        val result = MediatorLiveData<String>()
        val decimalFormat = DecimalFormat("#.##")
        result.addSource(this) { value ->
            val formattedValue = if (value < 0) {
                "-₹${decimalFormat.format(-value)}"
            } else {
                "₹${decimalFormat.format(value)}"
            }
            result.value = formattedValue
        }
        return result
    }


}