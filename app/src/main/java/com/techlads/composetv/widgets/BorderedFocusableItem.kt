package com.techlads.composetv.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.*

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun BorderedFocusableItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (BoxScope.() -> Unit)
) {
    Surface(
        onClick = { onClick() },
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
        ),
        modifier = modifier
            .fillMaxWidth()
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