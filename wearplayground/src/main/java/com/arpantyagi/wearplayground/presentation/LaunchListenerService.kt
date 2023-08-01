package com.arpantyagi.wearplayground.presentation

import android.content.Intent
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService

class LaunchListenerService : WearableListenerService() {

    companion object {
        private const val WEAR_APP_URI = "WEAR_APP_URI"
    }

    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)

        if(messageEvent.path == WEAR_APP_URI) {
            println("Received Message")
            val intent = Intent(this, ResultActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        }
    }
}