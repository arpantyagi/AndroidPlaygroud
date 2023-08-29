package com.arpantyagi.playground

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.Action.SEMANTIC_ACTION_MARK_AS_READ


class NotificationService(private val context: Context) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification() {

        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context, 1, activityIntent, PendingIntent.FLAG_IMMUTABLE)

        val wearIntent = Intent(context, CompanionAppStarterService::class.java)
        val wearPendingIntent = PendingIntent.getService(
            context, 1, wearIntent, PendingIntent.FLAG_IMMUTABLE)

        val simpleIntent = Intent(context, SimpleClass::class.java)
        val simplePendingIntent = PendingIntent.getActivity(
            context, 1, simpleIntent, PendingIntent.FLAG_IMMUTABLE)

        val watchAction = NotificationCompat.Action(
            androidx.core.R.drawable.notification_icon_background,
            "Open Task Details on Watch", wearPendingIntent)

        val simpleWatchAction = NotificationCompat.Action(
            androidx.core.R.drawable.notification_icon_background,
            "Simple Action", simplePendingIntent)

        val notificationIntent = Intent(context, NotificationBroadcastReceiver::class.java)
        val notificationPendingIntent = PendingIntent.getBroadcast(
            context, 1, notificationIntent, PendingIntent.FLAG_IMMUTABLE)

        val anotherWatchActions = NotificationCompat.Action(
            androidx.core.R.drawable.notification_icon_background,
            "Another Action", notificationPendingIntent)

        val notification =
            NotificationCompat.Builder(context, "1").setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Task Center Notification")
                .setContentText("New Task Received")
                .setContentIntent(activityPendingIntent)
                .extend(NotificationCompat.WearableExtender()
                    .addAction(watchAction).addAction(simpleWatchAction)
                    .addAction(anotherWatchActions)
                ).build()

        notificationManager.notify(1, notification)
    }


}