package com.nqmgaming.furniture.domain.repository

import com.nqmgaming.furniture.data.network.dto.NotificationDto
import com.nqmgaming.furniture.domain.model.notification.Notification

interface NotificationRepository {
    suspend fun getNotifications(userId: Int): List<Notification>
    suspend fun createNotification(notification: NotificationDto)
}