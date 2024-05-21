package com.example.upstox_assignment.presentation.utility

import com.example.upstox_assignment.domain.models.Stock
import com.example.upstox_assignment.domain.models.UserHolding

object ModelConversionUtility {

    fun getStocksFromHolding(holding: UserHolding): Stock{
        return Stock(
            symbol = holding.symbol,
            quantity = holding.quantity,
            ltp = holding.ltp,
            avgPrice = holding.avgPrice,
            close = holding.close,
            isExpanded = false,
            currentValue = holding.ltp * holding.quantity,
            investedValue = holding.avgPrice * holding.quantity
        ).apply {
            this.pl = this.currentValue - this.investedValue
            this.plToday = (this.close - this.ltp)*this.quantity
        }
    }

    fun getStockListFromHoldings(list: List<UserHolding>, converter: (UserHolding) -> Stock) : List<Stock>{
        return list.map(converter)
    }
}