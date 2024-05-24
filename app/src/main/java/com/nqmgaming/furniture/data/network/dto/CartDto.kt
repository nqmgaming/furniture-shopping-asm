package com.nqmgaming.furniture.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartDto(
    @SerialName("cart_id")
    val cartId: Int,
    @SerialName("quantity")
    val quantity: Int,
    @SerialName("color")
    val colorString: String,
    @SerialName("product_id")
    val productId: Int
)
