package com.arpantyagi.playground

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class NotificationService(private val context: Context) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification() {

        val activityIntent = Intent(context, MainActivity::class.java)

        val intent = Intent().apply {
            action = Intent.ACTION_MAIN
            addCategory(Intent.CATEGORY_LAUNCHER)
            setPackage("com.arpantyagi.wearplayground")
        }

        val viewIntent = Intent(context, MainActivity::class.java)

        //val wearIntent = Intent().apply {
           // component = ComponentName("com.arpantyagi.wearplayground", "com.arpantyagi.wearplayground.presentation.MainActivity") }


        val activityPendingIntent = PendingIntent.getActivity(
            context, 1, activityIntent, PendingIntent.FLAG_IMMUTABLE
        )

        val wearPendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )

        val watchAction = NotificationCompat.Action(
            androidx.core.R.drawable.notification_template_icon_low_bg,
            "Open on Watch",
            wearPendingIntent
        )


        val notification =
            NotificationCompat.Builder(context, "1").setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Test Notification")
                .setContentText("This is a Test Notification")
                .setContentIntent(activityPendingIntent)
                .extend(NotificationCompat.WearableExtender()
                    .addAction(watchAction)
                ).build()


        notificationManager.notify(1, notification)
    }


}