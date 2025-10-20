package com.techlads.uicomponents.widgets

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ClickableSurfaceBorder
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ClickableSurfaceScale
import androidx.tv.material3.ClickableSurfaceShape
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
fun BorderedFocusableItem(
    modifier: Modifier = Modifier,
    borderRadius: Dp = 12.dp,
    scale: ClickableSurfaceScale = ClickableSurfaceDefaults.scale(focusedScale = 1.05f),
    border: ClickableSurfaceBorder? = null,
    shape: ClickableSurfaceShape = CardItemDefaults.shape(borderRadius = borderRadius),
    color : Color = MaterialTheme.colorScheme.surface,
    onClick: () -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable (BoxScope.() -> Unit)
) {
    val isFocused by interactionSource.collectIsFocusedAsState()

    val animatedFloat = remember { Animatable(1f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(isFocused) {
        if (isFocused) {
            delay(1.seconds)
            animatedFloat.animateTo(
                targetValue = 0f, animationSpec = infiniteRepeatable(
                    animation = tween(700, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
        } else {
            animatedFloat.stop()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            scope.launch {
                animatedFloat.stop()
            }
        }
    }

    Surface(
        onClick = { onClick() },
        scale = scale,
        border = border ?: CardItemDefaults.border(borderRadius, MaterialTheme.colorScheme.inverseSurface.copy(alpha = animatedFloat.value)),
        shape = shape,
        colors = ClickableSurfaceDefaults.colors(containerColor = color, focusedContainerColor = color),
        modifier = modifier.fillMaxWidth(),
        interactionSource = interactionSource
    ) {
        content()
    }
}

@Preview
@Composable
private fun BorderedFocusableItemPrev() {
    BorderedFocusableItem(onClick = {}) {
        Text(text = "Preview Text")
    }
}
