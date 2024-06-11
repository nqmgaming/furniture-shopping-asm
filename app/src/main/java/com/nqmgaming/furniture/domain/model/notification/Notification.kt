package com.nqmgaming.furniture.domain.model.notification


data class Notification(
    val notificationId: Int? = null,
    val userId: Int,
    val productId: Int,
    val title: String,
    val message: String,
    val date: String? = null,
    val image: String? = null,
)
