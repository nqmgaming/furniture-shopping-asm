package com.nqmgaming.furniture.domain.model.cart

data class Cart(
    val cartId: Int,
    val quantity: Int,
    val colorString: String,
    val productId: Int
)
