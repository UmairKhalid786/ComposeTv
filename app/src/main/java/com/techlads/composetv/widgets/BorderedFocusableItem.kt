@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceBorder
import androidx.tv.material3.ClickableSurfaceColor
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ClickableSurfaceScale
import androidx.tv.material3.ClickableSurfaceShape
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import androidx.tv.material3.Text

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun BorderedFocusableItem(
    modifier: Modifier = Modifier,
    borderRadius: Dp = 12.dp,
    scale: ClickableSurfaceScale = ClickableSurfaceDefaults.scale(focusedScale = 1.1f),
    color: ClickableSurfaceColor = ClickableSurfaceDefaults.color(
        color = colorScheme.onSurface,
        focusedColor = colorScheme.surface
    ),
    border: ClickableSurfaceBorder = ClickableSurfaceDefaults.border(
        focusedBorder = Border(
            BorderStroke(
                width = 2.dp,
                color = colorScheme.surface
            ),
            shape = RoundedCornerShape(borderRadius)
        )
    ),
    shape: ClickableSurfaceShape = ClickableSurfaceDefaults.shape(
        shape = RoundedCornerShape(borderRadius),
        focusedShape = RoundedCornerShape(borderRadius)
    ),
    onClick: () -> Unit,
    content: @Composable (BoxScope.() -> Unit)
) {
    Surface(
        onClick = { onClick() },
        scale = scale,
        color = color,
        border = border,
        shape = shape,
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
