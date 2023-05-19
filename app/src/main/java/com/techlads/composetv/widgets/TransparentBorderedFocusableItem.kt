package com.techlads.composetv.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.*

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TransparentBorderedFocusableItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (BoxScope.() -> Unit)
) {
    Surface(
        onClick = { onClick() },
        scale = ClickableSurfaceDefaults.scale(focusedScale = 1.2f),
        color = ClickableSurfaceDefaults.color(
            color = Color.Transparent,
            focusedColor = Color.Transparent
        ),
        contentColor = ClickableSurfaceDefaults.contentColor(
            color = colorScheme.surface,
            focusedColor = colorScheme.surface
        ),
        border = ClickableSurfaceDefaults.border(
            focusedBorder = Border(
                BorderStroke(
                    width = 2.dp,
                    color = colorScheme.surface
                ),
                shape = RoundedCornerShape(2.dp)
            )
        ),
        shape = ClickableSurfaceDefaults.shape(
            shape = RoundedCornerShape(2.dp),
            focusedShape = RoundedCornerShape(2.dp)
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
    TransparentBorderedFocusableItem(onClick = {}) {
        Text(text = "Preview Text")
    }
}