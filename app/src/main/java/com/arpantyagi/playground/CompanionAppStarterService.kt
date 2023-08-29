package com.arpantyagi.playground

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import androidx.wear.remote.interactions.RemoteActivityHelper

class CompanionAppStarterService: Service() {

    override fun onBind(p0: Intent?): IBinder? {
        println("I am here")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        RemoteActivityHelper(this).startRemoteActivity(
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("com.arpan.sandbox://deeplink")
                addCategory(Intent.CATEGORY_BROWSABLE)
            }
        )
        return START_NOT_STICKY
    }
}
