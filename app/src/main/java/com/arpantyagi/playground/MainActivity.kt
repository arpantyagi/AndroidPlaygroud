package com.arpantyagi.playground

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.wear.remote.interactions.RemoteActivityHelper
import com.arpantyagi.playground.ui.theme.AndroidPlaygroundTheme
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notificationService = NotificationService(this)
        setContent {
            AndroidPlaygroundTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp(notificationService)
                }
            }
        }
    }
}

@Composable
fun MainApp(service: NotificationService) {

    val contextForToast = LocalContext.current.applicationContext
    val coroutine = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            service.showNotification()
        }) {
            Text(text = "Test Notification")
        }
        Button(onClick = {
            coroutine.launch { sendRequestToWatch(contextForToast) }
        }) {
            Text(text = "Open on Watch")
        }
    }

}

suspend fun sendRequestToWatch(ctx: Context) {
    runCatching {
        RemoteActivityHelper(ctx).startRemoteActivity(
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("com.arpan.sandbox://deeplink")
                addCategory(Intent.CATEGORY_BROWSABLE)
            },
            null
        ).await()
    }.onFailure {
        it.printStackTrace()
    }.onSuccess { println("SUCCESS!!!!!") }
}