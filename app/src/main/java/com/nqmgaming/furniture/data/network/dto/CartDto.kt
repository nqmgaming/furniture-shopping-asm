package com.nqmgaming.furniture.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartDto(
    @SerialName("cart_id")
    val cartId: Int = 0,
    @SerialName("quantity")
    val quantity: Int = 0,
    @SerialName("color")
    val colorString: String = "",
    @SerialName("product_id")
    val productId: Int = 0,
    @SerialName("user_id")
    val userId: Int = 0,
    @SerialName("Products")
    val product: ProductDto = ProductDto()
)
