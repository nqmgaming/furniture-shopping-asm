package com.nqmgaming.furniture.presentation.main.cart

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.data.mapper.asDomainModel
import com.nqmgaming.furniture.data.mapper.asDtoModel
import com.nqmgaming.furniture.domain.model.cart.Cart
import com.nqmgaming.furniture.domain.model.product.Product
import com.nqmgaming.furniture.domain.usecase.cart.AddCartUseCase
import com.nqmgaming.furniture.domain.usecase.cart.DecrementQuantityCartUseCase
import com.nqmgaming.furniture.domain.usecase.cart.GetCartsByUserIdUseCase
import com.nqmgaming.furniture.domain.usecase.cart.IncrementQuantityCartUseCase
import com.nqmgaming.furniture.domain.usecase.cart.RemoveCartItemUseCase
import com.nqmgaming.furniture.util.SharedPrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartsByUserIdUseCase: GetCartsByUserIdUseCase,
    private val removeCartItemUseCase: RemoveCartItemUseCase,
    private val incrementQuantityCartUseCase: IncrementQuantityCartUseCase,
    private val decrementQuantityCartUseCase: DecrementQuantityCartUseCase,
    private val addCartUseCase: AddCartUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val userId = SharedPrefUtils.getInt(getApplication(), "userId", 0)

    private val _cartList = MutableStateFlow<List<Cart>>(emptyList())
    val cartList = _cartList

    private val _total = MutableStateFlow(0)
    val total = _total

    init {
        viewModelScope.launch {
            getCartList()
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
        Toast.makeText(getApplication(), "Adding to cart click", Toast.LENGTH_SHORT).show()
        viewModelScope.launch {
            addToCart(product, color, quantity)
        }
    }

    fun onAddAllToCart(product: List<Product>, quantity: Int = 1) {
        Toast.makeText(getApplication(), "Adding to cart click", Toast.LENGTH_SHORT).show()
        viewModelScope.launch {
            product.forEach {
                addToCart(it, it.colors.first(), quantity)
            }
        }
    }

    private suspend fun getCartList() {
        try {
            val result = getCartsByUserIdUseCase.execute(GetCartsByUserIdUseCase.Input(userId))
            if (result is GetCartsByUserIdUseCase.Output.Success) {
                _cartList.value = result.carts.map { it.asDomainModel() }
                // update total price
                _total.value = _cartList.value.sumOf { it.product.price * it.quantity }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private suspend fun removeCartItem(cartId: String, cart: Cart) {

        // remove cart id from cartIdList flow
        _cartList.value = _cartList.value.filter { it.cartId != cartId.toInt() }

        val result = removeCartItemUseCase.execute(
            RemoveCartItemUseCase.Input(
                cart = cart.asDtoModel(),
                userId = userId,
                cartsId = _cartList.value.map { it.cartId.toString() }
            )
        )
        if (result is RemoveCartItemUseCase.Output.Success) {
            _cartList.value = _cartList.value.filter { it.cartId != cartId.toInt() }
            _total.value -= cart.product.price * cart.quantity
        }
    }

    private suspend fun incrementQuantity(cartId: String, quantity: Int = 1) {

        // Increment quantity of cart item in cartList flow
        val cart = _cartList.value.find { it.cartId == cartId.toInt() }
        cart?.let {
            it.quantity += quantity
            _total.value += it.product.price
        }

        val result = incrementQuantityCartUseCase.execute(
            IncrementQuantityCartUseCase.Input(
                cartId = cartId,
                quantity = cart?.quantity ?: 0
            )
        )

        if (result is IncrementQuantityCartUseCase.Output.Success) {
            // Update total price
            _total.value = _cartList.value.sumOf { it.product.price * it.quantity }
        }


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
                // Update total price
                _total.value = _cartList.value.sumOf { it.product.price * it.quantity }
            }


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
            incrementQuantity(cart.cartId.toString(), quantity)
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

                // refresh cartList flow
                getCartList()

                // Update total price
                _total.value += product.price * quantity

            } else if (result is AddCartUseCase.Output.Error)
                Toast.makeText(getApplication(), "Item already in cart error", Toast.LENGTH_SHORT)
                    .show()
        }

    }

    fun onGetAllQuantity(): Int {
        return _cartList.value.sumOf { it.quantity }
    }

    fun onGetAllTotal(): Int {
        return _cartList.value.sumOf { it.product.price * it.quantity }
    }

    fun onGetAllProductId(): List<Int> {
        return _cartList.value.map { it.product.productId }
    }

    fun onRemoveAllFromCart() {
        viewModelScope.launch {
            _cartList.value.forEach {
                removeCartItem(it.cartId.toString(), it)
            }
            _cartList.update { emptyList() }
        }
    }


}