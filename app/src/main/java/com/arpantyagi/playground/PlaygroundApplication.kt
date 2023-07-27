package com.arpantyagi.playground

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class PlaygroundApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            "1",
            "LocalNotification",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.description = "Test Notifications"

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}