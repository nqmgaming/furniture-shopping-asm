package com.nqmgaming.furniture.presentation.main.order

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val application: Application
) : AndroidViewModel(application) {
    private val userId = SharedPrefUtils.getInt(application, "userId")
    private val _orders = MutableStateFlow(emptyList<OrderDto>())
    val orders = _orders.asStateFlow()

    private val _tabIndex: MutableLiveData<Int> = MutableLiveData(0)
    val tabIndex: LiveData<Int> = _tabIndex
    val tabs = listOf("Delivered", "Processing", "Canceled")

    fun updateTabIndexBasedOnSwipe(isSwipeToTheLeft: Boolean) {
        _tabIndex.value = when (isSwipeToTheLeft) {
            true -> Math.floorMod(_tabIndex.value!!.plus(1), tabs.size)
            false -> Math.floorMod(_tabIndex.value!!.minus(1), tabs.size)
        }
    }

    fun updateTabIndex(i: Int) {
        _tabIndex.value = i
    }
    init {
        getOrders()
    }

    fun getOrders() {
        viewModelScope.launch {
            _orders.value = orderRepository.getOrders(userId)
        }
    }
}