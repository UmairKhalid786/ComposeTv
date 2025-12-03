package com.techlads.uicomponents.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults

object CardItemDefaults {
    @ReadOnlyComposable
    @Composable
    fun border(borderRadius: Dp, color: Color) = ClickableSurfaceDefaults.border(
        focusedBorder = Border(
            BorderStroke(
                width = 2.dp, color = color
            ), shape = RoundedCornerShape(borderRadius)
        )
    )

    @ReadOnlyComposable
    @Composable
    fun shape(borderRadius: Dp) = ClickableSurfaceDefaults.shape(
        shape = RoundedCornerShape(borderRadius), focusedShape = RoundedCornerShape(borderRadius)
    )
}