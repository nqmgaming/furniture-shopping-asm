package com.nqmgaming.furniture

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val notificationChannel = NotificationChannel(
            "app_notification_channel",
            "Furniture App Notification Channel",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // Setting up the channel
        notificationManager.createNotificationChannel(notificationChannel)
    }
}