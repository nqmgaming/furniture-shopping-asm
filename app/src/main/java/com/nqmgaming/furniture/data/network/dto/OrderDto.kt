package com.nqmgaming.furniture.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderDto(
    @SerialName("order_id")
    val orderId: Int? = null,
    @SerialName("order_date")
    val orderDate: String? = null,
    val quantity: Int,
    val total: Double,
    val status: String,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("product_id")
    val productId: List<Int>
)
