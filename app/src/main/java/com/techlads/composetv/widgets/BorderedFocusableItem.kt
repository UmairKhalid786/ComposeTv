package com.techlads.composetv.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceBorder
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ClickableSurfaceScale
import androidx.tv.material3.ClickableSurfaceShape
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text

@Composable
fun BorderedFocusableItem(
    modifier: Modifier = Modifier,
    borderRadius: Dp = 12.dp,
    scale: ClickableSurfaceScale = ClickableSurfaceDefaults.scale(focusedScale = 1.05f),
    border: ClickableSurfaceBorder = ClickableSurfaceDefaults.border(
        focusedBorder = Border(
            BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.inverseSurface
            ),
            shape = RoundedCornerShape(borderRadius)
        )
    ),
    shape: ClickableSurfaceShape = ClickableSurfaceDefaults.shape(
        shape = RoundedCornerShape(borderRadius),
        focusedShape = RoundedCornerShape(borderRadius)
    ),
    color : Color = MaterialTheme.colorScheme.surfaceVariant,
    onClick: () -> Unit,
    content: @Composable (BoxScope.() -> Unit)
) {
    Surface(
        onClick = { onClick() },
        scale = scale,
        border = border,
        shape = shape,
        colors = ClickableSurfaceDefaults.colors(containerColor = color, focusedContainerColor = color),
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
