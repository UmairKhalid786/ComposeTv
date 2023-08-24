package com.techlads.composetv

import android.window.OnBackInvokedDispatcher
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.core.os.BuildCompat

@androidx.annotation.OptIn(BuildCompat.PrereleaseSdkCheck::class)
fun ComponentActivity.registerOnBackPress(onBackPress: () -> Unit) {
    if (BuildCompat.isAtLeastT()) {
        onBackInvokedDispatcher.registerOnBackInvokedCallback(
            OnBackInvokedDispatcher.PRIORITY_DEFAULT,
        ) {
            // Back is pressed... Finishing the activity
            onBackPress()
        }
    } else {
        onBackPressedDispatcher.addCallback(this /* lifecycle owner */) {
            // Back is pressed... Finishing the activity
            onBackPress()
        }
    }
}
