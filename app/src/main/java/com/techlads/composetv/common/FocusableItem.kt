package com.techlads.composetv.common

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.*

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun FocusableItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (BoxScope.() -> Unit)
) {
    Surface(
        onClick = { onClick() },
        scale = ClickableSurfaceDefaults.scale(focusedScale = 1.05f),
        color = ClickableSurfaceDefaults.color(
            color = Color.Transparent
        ),
        glow = ClickableSurfaceDefaults.glow(
            focusedGlow = Glow(
                elevation = 5.dp,
                elevationColor = Color.Gray
            )
        ),
        shape = ClickableSurfaceDefaults.shape(
            shape = ShapeDefaults.Small,
            focusedShape = ShapeDefaults.Small
        ),
        modifier = modifier
            .padding(
                start = 16.dp, bottom = 8.dp
            )
            .fillMaxWidth()
    ) {
        content()
    }
}