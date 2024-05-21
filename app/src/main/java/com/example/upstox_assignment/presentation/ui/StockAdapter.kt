package com.example.upstox_assignment.presentation.ui

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.upstox_assignment.R
import com.example.upstox_assignment.domain.models.Stock
import com.example.upstox_assignment.presentation.utility.Extensions.formatToTwoDecimalPlacesWithRupeeSymbol

class StockAdapter(private val stockList: MutableList<Stock>) : RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stockName: TextView = itemView.findViewById(R.id.stock_name)
        val netQtyVal: TextView = itemView.findViewById(R.id.net_quantity_value)
        val pnlVal: TextView = itemView.findViewById(R.id.pnl_value)
        val ltpVal: TextView = itemView.findViewById(R.id.stock_ltp_value)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stock, parent, false)
        return StockViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val stock = stockList[position]
        holder.stockName.text = stock.symbol
        holder.netQtyVal.text = stock.quantity.toString()
        holder.pnlVal.text = stock.pl.formatToTwoDecimalPlacesWithRupeeSymbol()
        if(stock.pl > 0){
            holder.pnlVal.setTextColor(ContextCompat.getColor(holder.pnlVal.context, R.color.green))
        }
        else{
            holder.pnlVal.setTextColor(ContextCompat.getColor(holder.pnlVal.context, R.color.red))
        }
        holder.ltpVal.text = stock.ltp.formatToTwoDecimalPlacesWithRupeeSymbol()
    }

    override fun getItemCount() = stockList.size

    fun updateStockList(newStockList: List<Stock>) {
        stockList.clear()
        stockList.addAll(newStockList)
        notifyDataSetChanged()
    }
}
