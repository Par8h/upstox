package com.example.upstox_assignment.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import javax.inject.Inject
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.upstox_assignment.MyApp
import com.example.upstox_assignment.R
import com.example.upstox_assignment.databinding.ActivityMainBinding
import com.example.upstox_assignment.domain.models.Stock
import com.example.upstox_assignment.presentation.ui.StockAdapter
import com.example.upstox_assignment.presentation.utility.ModelConversionUtility
import com.example.upstox_assignment.presentation.utility.ModelConversionUtility.getStocksFromHolding
import com.example.upstox_assignment.presentation.utility.SharedPrefManager
import com.example.upstox_assignment.presentation.viewmodel.UserHoldingsViewModel

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val userHoldingsViewModel: UserHoldingsViewModel by viewModels { viewModelFactory }

    private lateinit var totalPlTextView: TextView
    private lateinit var plDetailsLayout: ConstraintLayout
    private lateinit var arrowIcon: ImageView
    private lateinit var totalPlAmount: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StockAdapter
    private lateinit var sharedPrefManager: SharedPrefManager


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = userHoldingsViewModel
        sharedPrefManager = SharedPrefManager(this)

        userHoldingsViewModel.userHoldings.observe(this, Observer { holdings ->
            if(this::recyclerView.isInitialized) {
                adapter.updateStockList(
                    ModelConversionUtility.getStockListFromHoldings(
                        holdings,
                        ::getStocksFromHolding
                    )
                )
            }
            calculateTotalPL(ModelConversionUtility.getStockListFromHoldings(
                holdings,
                ::getStocksFromHolding
            ))
            sharedPrefManager.saveStockList(ModelConversionUtility.getStockListFromHoldings(
                holdings,
                ::getStocksFromHolding
            ))
        })

        userHoldingsViewModel.errorMessage.observe(this, Observer { message ->
            Log.d("APICALL", "api error - $message")
            if(this::recyclerView.isInitialized) {
                adapter.updateStockList(
                        sharedPrefManager.getStockList()
                )
            }
            calculateTotalPL(sharedPrefManager.getStockList())
            Toast.makeText(this, "Showing cached data,please refresh", Toast.LENGTH_LONG).show()
        })

        userHoldingsViewModel.isLoading.observe(this) {
            if (it == true) {
                binding.shimmerViewContainer.startShimmer()
            } else {
                binding.shimmerViewContainer.stopShimmer()
            }
        }

        userHoldingsViewModel.fetchUserHoldings()
        recyclerView= binding.recyclerView
        val stockList = mutableListOf<Stock>()
        initViews(binding)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StockAdapter(stockList)
        recyclerView.adapter = adapter
    }

    private fun initViews(binding: ActivityMainBinding) {
        totalPlTextView = binding.totalPl
        plDetailsLayout = binding.plDetails
        arrowIcon = findViewById(R.id.arrow_icon)
        totalPlAmount = findViewById(R.id.total_pl_amount)
        arrowIcon.setOnClickListener{
            if(userHoldingsViewModel.isLoading.value ==  true){
                return@setOnClickListener
            }
            val isExpanded = plDetailsLayout.visibility == View.VISIBLE
            plDetailsLayout.visibility = if (isExpanded) View.GONE else View.VISIBLE
            if(isExpanded){
                rotateArrow(arrowIcon, 180f, 0f)
            }
            else{
                rotateArrow(arrowIcon, 0f, 180f)
            }
        }
        binding.actionBar.refresh.setOnClickListener{
            userHoldingsViewModel.fetchUserHoldings()
        }
    }


    private fun calculateTotalPL(stockList: List<Stock>) {
        var overallCurrentValue = 0.0
        var overallTotalInvestment = 0.0
        var overallTodayPL = 0.0
        var overallPL = 0.0

        for (stock in stockList) {
            overallCurrentValue += stock.currentValue
            overallTotalInvestment += stock.investedValue
            overallTodayPL += stock.plToday
            overallPL += stock.currentValue - stock.investedValue
        }

        userHoldingsViewModel.setBottomBarValues(overallCurrentValue, overallTotalInvestment, overallTodayPL, overallPL)
    }

    private fun rotateArrow(arrow: ImageView, fromDegrees: Float, toDegrees: Float) {
        val rotate = RotateAnimation(
            fromDegrees, toDegrees,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )
        rotate.duration = 300
        rotate.fillAfter = true
        arrow.startAnimation(rotate)
    }
}
