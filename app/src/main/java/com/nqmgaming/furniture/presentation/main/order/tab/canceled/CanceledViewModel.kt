package com.nqmgaming.furniture.presentation.main.order.tab.canceled

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.data.network.dto.OrderDto
import com.nqmgaming.furniture.domain.repository.OrderRepository
import com.nqmgaming.furniture.util.SharedPrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CanceledViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val application: Application
): AndroidViewModel(application) {
    private val userId = SharedPrefUtils.getInt(application, "userId")
    private val _orders = MutableStateFlow(emptyList<OrderDto>())
    val orders = _orders.asStateFlow()

    init {
        getOrders()
    }

    fun getOrders() {
        viewModelScope.launch {
            _orders.value = orderRepository.getOrders(userId)
            _orders.value = _orders.value.filter { it.status == "Canceled" }
        }
    }

}