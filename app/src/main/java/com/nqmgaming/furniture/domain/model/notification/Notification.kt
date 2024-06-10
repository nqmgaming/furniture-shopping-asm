package com.nqmgaming.furniture.domain.model.notification

data class Notification(
    val userId: String,
    val productId: String,
    val title: String,
    val message: String,
    val image: String,
)
