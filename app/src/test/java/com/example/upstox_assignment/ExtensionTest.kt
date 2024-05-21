package com.example.upstox_assignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.upstox_assignment.presentation.utility.Extensions.formatToTwoDecimalPlacesWithRupeeSymbol
import com.example.upstox_assignment.presentation.utility.Extensions.toDoubleString
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class ExtensionsTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

    }

    @Test
    fun testFormatToTwoDecimalPlacesWithRupeeSymbol_positiveValue() {
        val value = 123.456
        val formattedValue = value.formatToTwoDecimalPlacesWithRupeeSymbol()
        assertEquals("₹123.46", formattedValue)
    }

    @Test
    fun testFormatToTwoDecimalPlacesWithRupeeSymbol_negativeValue() {
        val value = -123.456
        val formattedValue = value.formatToTwoDecimalPlacesWithRupeeSymbol()
        assertEquals("-₹123.46", formattedValue)
    }

    @Test
    fun testToDoubleString_positiveValue() {
        val liveData = MutableLiveData<Double>()
        val observer = mock(Observer::class.java) as Observer<String>
        liveData.toDoubleString().observeForever(observer)

        liveData.value = 123.456
        verify(observer).onChanged("₹123.46")
    }

    @Test
    fun testToDoubleString_negativeValue() {
        val liveData = MutableLiveData<Double>()
        val observer = mock(Observer::class.java) as Observer<String>
        liveData.toDoubleString().observeForever(observer)

        liveData.value = -123.456
        verify(observer).onChanged("-₹123.46")
    }
}
