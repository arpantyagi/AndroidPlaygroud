package com.arpantyagi.playground

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.arpantyagi.playground.NOTIFICATION_ACTION") {
            print("Something has happened")
        }
    }
}