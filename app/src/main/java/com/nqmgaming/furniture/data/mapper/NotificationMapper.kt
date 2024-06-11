package com.nqmgaming.furniture.data.mapper

import com.nqmgaming.furniture.data.network.dto.NotificationDto
import com.nqmgaming.furniture.domain.model.notification.Notification

fun Notification.toDto() = NotificationDto(
    userId = userId,
    productId = productId,
    title = title,
    message = message,
)

fun NotificationDto.toModel() = Notification(
    notificationId = notificationId,
    userId = userId,
    productId = productId,
    title = title,
    message = message,
    date = date,
    image = product.imageList.firstOrNull()
)