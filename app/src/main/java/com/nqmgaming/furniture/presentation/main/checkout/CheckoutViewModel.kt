package com.nqmgaming.furniture.presentation.main.checkout

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.data.mapper.asDtoModel
import com.nqmgaming.furniture.domain.model.order.Order
import com.nqmgaming.furniture.domain.usecase.order.CreateOrderUseCase
import com.nqmgaming.furniture.util.SharedPrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val createOrderUseCase: CreateOrderUseCase,
    application: Application
):AndroidViewModel(application) {

    private val canNavigate = Channel<Boolean>()
    val navigate = canNavigate.receiveAsFlow()

    private val userId = SharedPrefUtils.getInt(getApplication(), "userId", 0)

    private val _total = MutableStateFlow(0)
    val total = _total.asStateFlow()

    private val _orderDate = MutableStateFlow("")
    val orderDate = _orderDate.asStateFlow()

    private val _quantity = MutableStateFlow(0)
    val quantity = _quantity.asStateFlow()

    private val _status = MutableStateFlow("")
    val status = _status

    private val _productId = MutableStateFlow<List<Int>>(emptyList())
    val productId = _productId

    fun onOrderDateChange(orderDate: String) {
        _orderDate.value = orderDate
    }

    fun onQuantityChange(quantity: Int) {
        _quantity.value = quantity
    }

    fun onTotalChange(total: Int) {
        _total.value = total
    }

    fun onStatusChange(status: String) {
        _status.value = status
    }

    fun onProductIdChange(productId: List<Int>) {
        _productId.value = productId
    }

    fun onCreateOrder() {
        viewModelScope.launch {
            val order = Order(
                quantity = quantity.value,
                total = total.value.toDouble(),
                status = status.value,
                userId = userId,
                productId = productId.value
            )
            Log.d("CheckoutViewModel", "onCreateOrder: $order")
            val result = createOrderUseCase.execute(CreateOrderUseCase.Input(order.asDtoModel()))
            canNavigate.send(result.added)
        }
    }

}