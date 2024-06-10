package com.nqmgaming.furniture.domain.model.order


data class Order(
    val orderId: Int? = null,
    val orderDate: String? = null,
    val quantity: Int,
    val total: Double,
    val status: String,
    val userId: Int,
    val productId: List<Int>
)