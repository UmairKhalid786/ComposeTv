package com.techlads.composetv.widgets

import android.view.KeyEvent.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.*
import androidx.tv.material3.*
import kotlinx.coroutines.*

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun BorderedFocusableItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (BoxScope.() -> Unit)
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Surface(modifier = modifier
        .fillMaxWidth()
        .indication(interactionSource, LocalIndication.current),
        onClick = onClick,
        interactionSource = interactionSource,
        scale = ClickableSurfaceDefaults.scale(focusedScale = 1.2f),
        color = ClickableSurfaceDefaults.color(
            color = colorScheme.onSurface,
            focusedColor = colorScheme.onSurface
        ),
        contentColor = ClickableSurfaceDefaults.contentColor(
            color = colorScheme.surface,
            focusedColor = colorScheme.surface
        ),
        glow = ClickableSurfaceDefaults.glow(
            focusedGlow = Glow(
                elevation = 5.dp,
                elevationColor = colorScheme.surface.copy(alpha = 0.5f)
            )
        ),
        border = ClickableSurfaceDefaults.border(
            focusedBorder = Border(
                BorderStroke(
                    width = 2.dp,
                    color = colorScheme.surface
                )
            )
        ),
        shape = ClickableSurfaceDefaults.shape(
            shape = ShapeDefaults.Small
        )
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