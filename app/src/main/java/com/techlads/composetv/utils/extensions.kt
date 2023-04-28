package com.techlads.composetv.utils

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.key.NativeKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.debugInspectorInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun <T> StateFlow<T>.toMutable() = this as MutableStateFlow

fun Modifier.handleDPadEnter(
    enabled: Boolean,
    interactionSource: MutableInteractionSource,
    onClick: (() -> Unit)? = null,
    checked: Boolean = false,
    onCheckedChanged: ((Boolean) -> Unit)? = null
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "handleDPadEnter"
        properties["enabled"] = enabled
        properties["interactionSource"] = interactionSource
        properties["onClick"] = onClick
        properties["checked"] = checked
        properties["onCheckedChanged"] = onCheckedChanged
    }
) {
    val coroutineScope = rememberCoroutineScope()
    val pressInteraction = remember { PressInteraction.Press(Offset.Zero) }
    var isPressed by remember { mutableStateOf(false) }
    this.then(
        onPreviewKeyEvent {
            keyEvent ->
            if (AcceptableKeys.any { keyEvent.nativeKeyEvent.keyCode == it } && enabled) {
                when (keyEvent.nativeKeyEvent.action) {
                    NativeKeyEvent.ACTION_DOWN -> {
                        if (!isPressed) {
                            isPressed = true
                            coroutineScope.launch {
                                interactionSource.emit(pressInteraction)
                            }
                        }
                    }

                    NativeKeyEvent.ACTION_UP -> {
                        if (isPressed) {
                            isPressed = false
                            coroutineScope.launch {
                                interactionSource.emit(PressInteraction.Release(pressInteraction))
                            }
                            onClick?.invoke()
                            onCheckedChanged?.invoke(!checked)
                        }
                    }
                }
                return@onPreviewKeyEvent EventPropagation.StopPropagation
            }
            EventPropagation.ContinuePropagation
        }
    )
}

private val AcceptableKeys = listOf(
    NativeKeyEvent.KEYCODE_DPAD_CENTER,
    NativeKeyEvent.KEYCODE_ENTER,
    NativeKeyEvent.KEYCODE_NUMPAD_ENTER
)