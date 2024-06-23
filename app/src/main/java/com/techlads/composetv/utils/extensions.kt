package com.techlads.composetv.utils

import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_DPAD_CENTER
import android.view.KeyEvent.KEYCODE_DPAD_LEFT
import android.view.KeyEvent.KEYCODE_DPAD_RIGHT
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.KeyEvent.KEYCODE_NUMPAD_ENTER
import android.view.KeyEvent.KEYCODE_SYSTEM_NAVIGATION_LEFT
import android.view.KeyEvent.KEYCODE_SYSTEM_NAVIGATION_RIGHT
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.onPreviewKeyEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun <T> StateFlow<T>.toMutable() = this as MutableStateFlow

private val DPadEventsKeyCodes = listOf(
    KEYCODE_DPAD_LEFT,
    KEYCODE_SYSTEM_NAVIGATION_LEFT,
    KEYCODE_DPAD_RIGHT,
    KEYCODE_SYSTEM_NAVIGATION_RIGHT,
    KEYCODE_DPAD_CENTER,
    KEYCODE_ENTER,
    KEYCODE_NUMPAD_ENTER,
)

fun Modifier.handleDPadKeyEvents(
    onLeft: (() -> Unit)? = null,
    onRight: (() -> Unit)? = null,
    onEnter: (() -> Unit)? = null,
) = onPreviewKeyEvent {
    fun onActionUp(block: () -> Unit) {
        if (it.nativeKeyEvent.action == KeyEvent.ACTION_UP) block()
    }

    if (!DPadEventsKeyCodes.contains(it.nativeKeyEvent.keyCode)) return@onPreviewKeyEvent false

    when (it.nativeKeyEvent.keyCode) {
        KEYCODE_ENTER,
        KEYCODE_DPAD_CENTER,
        KEYCODE_NUMPAD_ENTER,
        -> {
            onEnter?.apply {
                onActionUp(::invoke)
                return@onPreviewKeyEvent true
            }
        }

        KEYCODE_DPAD_LEFT,
        KEYCODE_SYSTEM_NAVIGATION_LEFT,
        -> {
            onLeft?.apply {
                onActionUp(::invoke)
                return@onPreviewKeyEvent true
            }
        }

        KEYCODE_DPAD_RIGHT,
        KEYCODE_SYSTEM_NAVIGATION_RIGHT,
        -> {
            onRight?.apply {
                onActionUp(::invoke)
                return@onPreviewKeyEvent true
            }
        }
    }
    false
}

fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }