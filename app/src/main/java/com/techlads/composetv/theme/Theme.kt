package com.techlads.composetv.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.tv.material3.*

@OptIn(ExperimentalTvMaterial3Api::class)
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Neutral10
)

@OptIn(ExperimentalTvMaterial3Api::class)
private val LightColorScheme = lightColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Neutral10
)

val MidShape = staticCompositionLocalOf<Shape> { RoundedCornerShape(8.dp) }

object AppTheme {
    val midShape: Shape
        @Composable
        @ReadOnlyComposable
        get() = MidShape.current

    @OptIn(ExperimentalTvMaterial3Api::class)
    val MainColor: Color
        @Composable
        @ReadOnlyComposable
        get() = DarkColorScheme.primary

    @OptIn(ExperimentalTvMaterial3Api::class)
    val surface: Color
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme.surface

    @OptIn(ExperimentalTvMaterial3Api::class)
    val onSurface: Color
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme.onSurface
}


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun Material3Theme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = {
            CompositionLocalProvider(
                LocalContentColor provides AppTheme.onSurface
            ){
                content()
            }
        }
    )
}