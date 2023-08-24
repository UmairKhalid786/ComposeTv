package com.techlads.composetv.widgets

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.Surface
import androidx.tv.material3.Text

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun FocusableItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (BoxScope.() -> Unit),
) {
    Surface(
        onClick = { onClick() },
        scale = ClickableSurfaceDefaults.scale(focusedScale = 1.1f),
        color = ClickableSurfaceDefaults.color(
            color = colorScheme.onSurface,
            focusedColor = colorScheme.surface,
        ),
        contentColor = ClickableSurfaceDefaults.contentColor(
            color = colorScheme.surface,
            focusedColor = colorScheme.onSurface,
        ),
        glow = ClickableSurfaceDefaults.glow(
            focusedGlow = Glow(
                elevation = 5.dp,
                elevationColor = colorScheme.surface.copy(alpha = 0.5f),
            ),
        ),
        shape = ClickableSurfaceDefaults.shape(
            shape = ShapeDefaults.Small,
            focusedShape = ShapeDefaults.Small,
        ),
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
