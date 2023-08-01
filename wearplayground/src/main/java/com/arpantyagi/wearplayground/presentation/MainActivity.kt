package com.arpantyagi.wearplayground.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.wear.remote.interactions.RemoteActivityHelper
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting()
        }
    }
}

@Composable
fun Greeting() {
    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {
                coroutine.launch {
                    kotlin.runCatching {
                        RemoteActivityHelper(context).startRemoteActivity(
                            Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse("com.arpan.sandbox.mobile://deeplink")
                                addCategory(Intent.CATEGORY_BROWSABLE)
                            },
                            null
                        ).await()
                    }.onFailure {
                        it.printStackTrace()
                    }.onSuccess {
                        println("SUCCESS!!!!!123")
                    }
                }

            }) {
            Text(text = "Task Details Screen")
        }
    }
}
