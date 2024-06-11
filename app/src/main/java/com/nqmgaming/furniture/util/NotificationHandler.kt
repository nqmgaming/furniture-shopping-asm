package com.nqmgaming.furniture.util

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.nqmgaming.furniture.R
import kotlin.random.Random

class NotificationHandler(private val context: Context) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    private val notificationChannelID = "notification_channel_id"

    // SIMPLE NOTIFICATION
    fun showSimpleNotification() {
        val notification = NotificationCompat.Builder(context, notificationChannelID)
            .setContentTitle("Simple Notification")
            .setContentText("Message or text with notification")
            .setSmallIcon(R.drawable.sample)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()  // finalizes the creation

        notificationManager.notify(Random.nextInt(), notification)
    }
}