package com.nqmgaming.furniture.presentation.main.order

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nqmgaming.furniture.presentation.main.order.tab.canceled.CanceledScreen
import com.nqmgaming.furniture.presentation.main.order.tab.delivered.DeliveredScreen
import com.nqmgaming.furniture.presentation.main.order.tab.processing.ProcessingScreen

@Composable
fun OrderScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: OrderViewModel = hiltViewModel()
) {
    val orders = viewModel.orders.collectAsState()
    val pagerState = rememberPagerState(pageCount = { 3 })
    LaunchedEffect(key1 = true) {
        Log.d("OrderScreen", "Orders: ${orders.value}")
    }

    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Delivered", "Processing", "Canceled")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                    color = TabRowDefaults.primaryContentColor,
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    icon = {
                        when (index) {
                            0 -> Icon(imageVector = Icons.Default.Home, contentDescription = null)
                            1 -> Icon(imageVector = Icons.Default.Info, contentDescription = null)
                            2 -> Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        }
        when (tabIndex) {
            0 -> DeliveredScreen()
            1 -> ProcessingScreen()
            2 -> CanceledScreen()
        }
    }
}

