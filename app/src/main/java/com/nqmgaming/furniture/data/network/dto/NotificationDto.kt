package com.nqmgaming.furniture.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class NotificationDto(
    @SerialName("notification_id")
    val notificationId: Int? = null,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("product_id")
    val productId: Int,
    @SerialName("title")
    val title: String,
    @SerialName("message")
    val message: String,
    @SerialName("date")
    val date: String? = null,
    @SerialName("Products")
    val product: ProductDto = ProductDto()
)