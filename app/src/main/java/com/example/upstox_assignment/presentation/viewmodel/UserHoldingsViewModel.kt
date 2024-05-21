package com.example.upstox_assignment.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upstox_assignment.domain.models.UserHolding
import com.example.upstox_assignment.domain.useCase.GetUserHoldingsUseCase
import com.example.upstox_assignment.presentation.utility.Extensions.toDoubleString
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserHoldingsViewModel @Inject constructor(private val getUserHoldingsUseCase: GetUserHoldingsUseCase) : ViewModel() {
    val userHoldings = MutableLiveData<List<UserHolding>>()
    val errorMessage = MutableLiveData<String>()
    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _overallCurrentValue = MutableLiveData<Double>().apply { value = 0.0 }
    val overallCurrentValue: LiveData<String> get() = _overallCurrentValue.toDoubleString()
    private val _overallTotalInvestment = MutableLiveData<Double>().apply { value = 0.0 }
    val overallTotalInvestment: LiveData<String> get() = _overallTotalInvestment.toDoubleString()
    private val _overallTodayPl = MutableLiveData<Double>().apply { value = 0.0 }
    val overallTodayPl: LiveData<String> get() = _overallTodayPl.toDoubleString()
    val overallTodayPlDouble: LiveData<Double> get() = _overallTodayPl
    private val _overallPl = MutableLiveData<Double>().apply { value = 0.0 }
    val overallPl: LiveData<String> get() = _overallPl.toDoubleString()
    val overallPlDouble: LiveData<Double> get() = _overallPl

    fun fetchUserHoldings() {
        viewModelScope.launch {
            try {
                _isLoading.postValue(true)
                val response = getUserHoldingsUseCase.execute()
                userHoldings.postValue(response.data.userHolding)
                _isLoading.postValue(false)
            } catch (e: Exception) {
                Log.d("APICALL", "view model error $e")
                errorMessage.postValue(e.message)
                _isLoading.postValue(false)
            }
        }
    }

    fun setBottomBarValues(overallCV: Double,overallTI: Double, overallTPL : Double, overallPL: Double ){
        _overallCurrentValue.postValue(overallCV)
        _overallTotalInvestment.postValue(overallTI)
        _overallTodayPl.postValue(overallTPL)
        _overallPl.postValue(overallPL)
    }
}
