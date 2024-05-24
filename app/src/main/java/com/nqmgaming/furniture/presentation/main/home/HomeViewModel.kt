package com.nqmgaming.furniture.presentation.main.home

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.data.repository.ProductRepository
import com.nqmgaming.furniture.domain.mapper.asDomainModel
import com.nqmgaming.furniture.domain.model.cart.Cart
import com.nqmgaming.furniture.domain.model.product.Product
import com.nqmgaming.furniture.domain.usecase.cart.AddCartUseCase
import com.nqmgaming.furniture.domain.usecase.cart.AddIdCartToUserUseCase
import com.nqmgaming.furniture.domain.usecase.cart.GetCartByIdUseCase
import com.nqmgaming.furniture.domain.usecase.cart.GetCartsByUserIdUseCase
import com.nqmgaming.furniture.domain.usecase.cart.IncrementQuantityCartUseCase
import com.nqmgaming.furniture.domain.usecase.product.GetProductByIdUseCase
import com.nqmgaming.furniture.util.SharedPrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val getCartsByUserIdUseCase: GetCartsByUserIdUseCase,
    private val getCartByIdUseCase: GetCartByIdUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val incrementQuantityCartUseCase: IncrementQuantityCartUseCase,
    private val addCartUseCase: AddCartUseCase,
    private val addIdCartToUserUseCase: AddIdCartToUserUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val userId = SharedPrefUtils.getInt(getApplication(), "userId", 0)

    private val _cartIdList = MutableStateFlow<List<String>>(emptyList())
    private val cartIdList = _cartIdList

    private val _cartList = MutableStateFlow<List<Cart>>(emptyList())

    private val _productList = MutableStateFlow<List<Product>?>(listOf())
    val productList: Flow<List<Product>?> = _productList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    init {
        getProducts()
        viewModelScope.launch {
            getCartList()
            getCartItems(cartIdList.value)
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            val products = productRepository.getProducts()
            _productList.emit(products.map {
                it.asDomainModel()
            })
        }
    }

    fun onAddToCart(product: Product, color: String, quantity: Int = 1) {
        viewModelScope.launch {
            addToCart(product, color, quantity)
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
        _cartList.value = cartList
        Log.d("CartScreen", "CartList: $cartList")
    }

    private suspend fun incrementQuantity(cartId: String) {

        // Increment quantity of cart item in cartList flow
        val cart = _cartList.value.find { it.cartId == cartId.toInt() }
        cart?.let {
            it.quantity += 1
        }


        val result = incrementQuantityCartUseCase.execute(
            IncrementQuantityCartUseCase.Input(
                cartId = cartId,
                quantity = cart?.quantity ?: 0
            )
        )

        if (result is IncrementQuantityCartUseCase.Output.Success) {
            Toast.makeText(getApplication(), "Item quantity increased", Toast.LENGTH_SHORT).show()
        }

    }

    private suspend fun addToCart(product: Product, color: String, quantity: Int = 1) {

        // Search product in cartList flow with product id and color
        val cart = _cartList.value.find {
            it.product.productId == product.productId
                    && it.colorString == color
        }

        // If product is already in cartList flow, increment quantity and total price
        if (cart != null) {
            incrementQuantity(cart.cartId.toString())
            return
        } else {
            // Product is not in cartList flow, add product to cart and update total price
            val result = addCartUseCase.execute(
                AddCartUseCase.Input(
                    userId = userId,
                    productId = product.productId,
                    quantity = quantity,
                    colorString = color
                )
            )

            if (result is AddCartUseCase.Output.Success) {
                // Add cart id to list of cart id then insert to user
                val cartIdList = _cartIdList.value.toMutableList()
                cartIdList.add(result.cart?.cartId.toString())
                _cartIdList.value = cartIdList
                addIdCartToUserUseCase.execute(AddIdCartToUserUseCase.Input(userId, cartIdList))
            }
            Toast.makeText(getApplication(), "Item added to cart", Toast.LENGTH_SHORT).show()
        }

    }

}