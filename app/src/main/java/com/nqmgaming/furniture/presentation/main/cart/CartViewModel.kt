package com.nqmgaming.furniture.presentation.main.cart

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.domain.mapper.asDomainModel
import com.nqmgaming.furniture.domain.mapper.asDtoModel
import com.nqmgaming.furniture.domain.model.cart.Cart
import com.nqmgaming.furniture.domain.model.product.Product
import com.nqmgaming.furniture.domain.usecase.cart.AddCartUseCase
import com.nqmgaming.furniture.domain.usecase.cart.AddIdCartToUserUseCase
import com.nqmgaming.furniture.domain.usecase.cart.DecrementQuantityCartUseCase
import com.nqmgaming.furniture.domain.usecase.cart.GetCartByIdUseCase
import com.nqmgaming.furniture.domain.usecase.cart.GetCartsByUserIdUseCase
import com.nqmgaming.furniture.domain.usecase.cart.IncrementQuantityCartUseCase
import com.nqmgaming.furniture.domain.usecase.cart.RemoveCartItemUseCase
import com.nqmgaming.furniture.domain.usecase.product.GetProductByIdUseCase
import com.nqmgaming.furniture.util.SharedPrefUtils
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
    private val removeCartItemUseCase: RemoveCartItemUseCase,
    private val incrementQuantityCartUseCase: IncrementQuantityCartUseCase,
    private val decrementQuantityCartUseCase: DecrementQuantityCartUseCase,
    private val addCartUseCase: AddCartUseCase,
    private val addIdCartToUserUseCase: AddIdCartToUserUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val userId = SharedPrefUtils.getInt(getApplication(), "userId", 0)

    private val _cartIdList = MutableStateFlow<List<String>>(emptyList())
    private val cartIdList = _cartIdList

    private val _cartList = MutableStateFlow<List<Cart>>(emptyList())
    val cartList = _cartList

    private val _total = MutableStateFlow(0)
    val total = _total

    init {
        viewModelScope.launch {
            getCartList()
            getCartItems(cartIdList.value)
        }
    }

    fun onRemoveCartItem(cartId: String, cart: Cart) {
        viewModelScope.launch {
            removeCartItem(cartId, cart)
        }
    }

    fun onIncrementQuantity(cartId: String) {
        viewModelScope.launch {
            incrementQuantity(cartId)
        }
    }

    fun onDecrementQuantity(cartId: String) {
        viewModelScope.launch {
            decrementQuantity(cartId)
        }
    }

    fun onAddToCart(product: Product, color: String, quantity: Int = 1) {
        viewModelScope.launch {
            addToCart(product, color, quantity)
        }
    }

    private suspend fun getCartList() {
        val result = getCartsByUserIdUseCase.execute(GetCartsByUserIdUseCase.Input(userId))
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

    private suspend fun removeCartItem(cartId: String, cart: Cart) {

        // remove cart id from cartIdList flow
        _cartIdList.value = _cartIdList.value.filter { it != cartId }

        val result = removeCartItemUseCase.execute(
            RemoveCartItemUseCase.Input(
                cart = cart.asDtoModel(),
                userId = userId,
                cartsId = cartIdList.value
            )
        )
        if (result is RemoveCartItemUseCase.Output.Success) {

            _cartList.value = _cartList.value.filter { it.cartId != cartId.toInt() }
            _cartIdList.value = _cartIdList.value.filter { it != cartId }
            _total.value -= cart.product.price * cart.quantity

            Toast.makeText(getApplication(), "Item removed from cart", Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun incrementQuantity(cartId: String) {

        // Increment quantity of cart item in cartList flow
        val cart = _cartList.value.find { it.cartId == cartId.toInt() }
        cart?.let {
            it.quantity += 1
            _total.value += it.product.price
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

        // Update total price
        _total.value = _cartList.value.sumOf { it.product.price * it.quantity }
    }

    private suspend fun decrementQuantity(cartId: String) {

        if (_cartList.value.find { it.cartId == cartId.toInt() }?.quantity == 1) {
            removeCartItem(cartId, _cartList.value.find { it.cartId == cartId.toInt() }!!)
            return
        } else {
            // Decrement quantity of cart item in cartList flow
            val cart = _cartList.value.find { it.cartId == cartId.toInt() }
            cart?.let {
                it.quantity -= 1
                _total.value -= it.product.price
            }

            val result = decrementQuantityCartUseCase.execute(
                DecrementQuantityCartUseCase.Input(
                    cartId = cartId,
                    quantity = cart?.quantity ?: 0
                )
            )

            if (result is DecrementQuantityCartUseCase.Output.Success) {
                Toast.makeText(getApplication(), "Item quantity decreased", Toast.LENGTH_SHORT)
                    .show()
            }

            // Update total price
            _total.value = _cartList.value.sumOf { it.product.price * it.quantity }

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
                addIdCartToUser(cartIdList)

                // Add cart to cartList flow
                val cart = result.cart?.asDomainModel()
                cart?.let {
                    cart.product = product
                    _cartList.value += cart
                }

            }
            Toast.makeText(getApplication(), "Item added to cart", Toast.LENGTH_SHORT).show()
        }

    }

    private suspend fun addIdCartToUser(cartIdList: List<String>) {
        val result = addIdCartToUserUseCase.execute(AddIdCartToUserUseCase.Input(userId, cartIdList))
        if (result is AddIdCartToUserUseCase.Output.Success) {
            Log.d("CartScreen", "CartIdList added to user")
        }
    }


}