package com.nqmgaming.furniture.data.repository

import com.nqmgaming.furniture.data.network.dto.NotificationDto
import com.nqmgaming.furniture.domain.model.notification.Notification
import com.nqmgaming.furniture.domain.repository.NotificationRepository
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
) : NotificationRepository {
    override suspend fun getNotifications(userId: Int): List<Notification> {
        try {
            val result = postgrest.from("Notifications")
                .select(
                    columns = Columns.list(
                        "notification_id",
                        "user_id",
                        "message",
                        "title",
                        "date",
                        "product_id",
                        "Products(image_list)"
                    )
                ) {
                    filter {
                        eq("user_id", userId)
                    }
                }.decodeList<NotificationDto>()
            return result.map {
                Notification(
                    notificationId = it.notificationId ?: 0,
                    userId = it.userId,
                    productId = it.productId,
                    title = it.title,
                    message = it.message,
                    date = it.date ?: "",
                    image = it.product.imageList.firstOrNull() ?: ""
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    override suspend fun createNotification(notification: NotificationDto) {
        try {
            postgrest.from("Notifications")
                .insert(notification)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}