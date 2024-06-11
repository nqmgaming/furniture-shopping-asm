package com.nqmgaming.furniture.presentation.main.order.tab.delivered

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nqmgaming.furniture.data.mapper.asDomainModel
import com.nqmgaming.furniture.presentation.main.order.component.OrderItem

@Composable
fun DeliveredScreen(
    viewModel: DeliveredViewModel = hiltViewModel()
) {

    val orders = viewModel.orders.collectAsState()
    val pagerState = rememberPagerState(pageCount = { 3 })
    LaunchedEffect(key1 = true) {
        Log.d("OrderScreen", "Orders: ${orders.value}")
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(orders.value.size) { index ->
            val order = orders.value[index]
            OrderItem(
                orderItem = order.asDomainModel()
            )
        }
    }
}