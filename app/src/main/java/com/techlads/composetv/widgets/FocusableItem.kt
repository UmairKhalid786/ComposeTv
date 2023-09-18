@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.widgets

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ClickableSurfaceColors
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ClickableSurfaceGlow
import androidx.tv.material3.ClickableSurfaceScale
import androidx.tv.material3.ClickableSurfaceShape
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.Surface
import androidx.tv.material3.Text

@Composable
fun FocusableItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: ClickableSurfaceShape = ClickableSurfaceDefaults.shape(
        shape = ShapeDefaults.Small,
        focusedShape = ShapeDefaults.Small
    ),
    color: ClickableSurfaceColors = ClickableSurfaceDefaults.colors(
        containerColor = colorScheme.onSurface,
        focusedContainerColor = colorScheme.surface,
        contentColor = colorScheme.surface,
        focusedContentColor = colorScheme.onSurface
    ),
    glow: ClickableSurfaceGlow = ClickableSurfaceDefaults.glow(
        focusedGlow = Glow(
            elevation = 5.dp,
            elevationColor = colorScheme.surface.copy(alpha = 0.5f)
        )
    ),
    scale: ClickableSurfaceScale = ClickableSurfaceDefaults.scale(focusedScale = 1.1f),
    content: @Composable (BoxScope.() -> Unit)
) {
    Surface(
        onClick = { onClick() },
        scale = scale,
        colors = color,
        glow = glow,
        shape = shape,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        content()
    }
}

@Preview
@Composable
fun FocusableItemPrev() {
    FocusableItem(onClick = {}) {
        Text(text = "Preview Text")
    }
}
