package com.nqmgaming.furniture.presentation.main.cart

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.domain.mapper.asDomainModel
import com.nqmgaming.furniture.domain.model.cart.Cart
import com.nqmgaming.furniture.domain.usecase.cart.GetCartByIdUseCase
import com.nqmgaming.furniture.domain.usecase.cart.GetCartsByUserIdUseCase
import com.nqmgaming.furniture.domain.usecase.product.GetProductByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartsByUserIdUseCase: GetCartsByUserIdUseCase,
    private val getCartByIdUseCase: GetCartByIdUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _cartIdList = MutableStateFlow<List<String>>(emptyList())
    val cartIdList = _cartIdList

    private val _cartList = MutableStateFlow<List<Cart>>(emptyList())
    val cartList = _cartList

    private val _total = MutableStateFlow(0)
    val total = _total

    init {
        viewModelScope.launch {
            delay(1000)
            getCartList()
            getCartItems(cartIdList.value)
        }
    }

    private suspend fun getCartList() {
        val result = getCartsByUserIdUseCase.execute(GetCartsByUserIdUseCase.Input(1))
        if (result is GetCartsByUserIdUseCase.Output.Success) {
            _cartIdList.value = result.carts
            Log.d("CartScreen", "CartIdList: $cartIdList")
        }
    }

    private suspend fun getCartItems(listId: List<String>) {
        val cartList = mutableListOf<Cart>()
        listId.forEach {
            val result = getCartByIdUseCase.execute(GetCartByIdUseCase.Input(it))
            if (result is GetCartByIdUseCase.Output.Success) {
                cartList.add(result.cart.asDomainModel())

                // Get product by id
                val productResult =
                    getProductByIdUseCase.execute(GetProductByIdUseCase.Input(result.cart.productId))
                if (productResult is GetProductByIdUseCase.Output.Success) {
                    cartList.last().product = productResult.product.asDomainModel()
                }

            }
        }
        _total.value = cartList.sumOf { it.product.price * it.quantity }
        _cartList.value = cartList
        Log.d("CartScreen", "Total: $total")
        Log.d("CartScreen", "CartList: $cartList")
    }

}