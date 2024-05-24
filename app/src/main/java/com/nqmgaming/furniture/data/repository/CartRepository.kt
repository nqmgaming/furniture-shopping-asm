package com.nqmgaming.furniture.data.repository

import com.nqmgaming.furniture.data.network.dto.CartDto

interface CartRepository {
    suspend fun getCartsByUserId(userId: Int): List<CartDto>
    suspend fun getCartItem(cartId: String): CartDto
    suspend fun removeCartItem(cartItem: CartDto, userId: Int, cartsId: List<String>)
    suspend fun decreaseCartItem(cartId: String, quantity: Int)
    suspend fun increaseCartItem(cartId: String, quantity: Int)
    suspend fun removeAllCartId(userId: Int)
    suspend fun removeAllFromCart(cartId: String)
    suspend fun addToCart(productId: Int, quantity: Int, colorString: String): CartDto?
    suspend fun addIdCart(userId: Int, cartsId: List<String>)
}