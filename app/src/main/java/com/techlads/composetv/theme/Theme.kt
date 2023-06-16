@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.lightColorScheme
import androidx.tv.material3.darkColorScheme
import md_theme_dark_background
import md_theme_dark_error
import md_theme_dark_errorContainer
import md_theme_dark_inverseOnSurface
import md_theme_dark_inversePrimary
import md_theme_dark_inverseSurface
import md_theme_dark_onBackground
import md_theme_dark_onError
import md_theme_dark_onErrorContainer
import md_theme_dark_onPrimary
import md_theme_dark_onPrimaryContainer
import md_theme_dark_onSecondary
import md_theme_dark_onSecondaryContainer
import md_theme_dark_onSurface
import md_theme_dark_onSurfaceVariant
import md_theme_dark_onTertiary
import md_theme_dark_onTertiaryContainer
import md_theme_dark_primary
import md_theme_dark_primaryContainer
import md_theme_dark_scrim
import md_theme_dark_secondary
import md_theme_dark_secondaryContainer
import md_theme_dark_surface
import md_theme_dark_surfaceTint
import md_theme_dark_surfaceVariant
import md_theme_dark_tertiary
import md_theme_dark_tertiaryContainer
import md_theme_light_background
import md_theme_light_error
import md_theme_light_errorContainer
import md_theme_light_inverseOnSurface
import md_theme_light_inversePrimary
import md_theme_light_inverseSurface
import md_theme_light_onBackground
import md_theme_light_onError
import md_theme_light_onErrorContainer
import md_theme_light_onPrimary
import md_theme_light_onPrimaryContainer
import md_theme_light_onSecondary
import md_theme_light_onSecondaryContainer
import md_theme_light_onSurface
import md_theme_light_onSurfaceVariant
import md_theme_light_onTertiary
import md_theme_light_onTertiaryContainer
import md_theme_light_primary
import md_theme_light_primaryContainer
import md_theme_light_scrim
import md_theme_light_secondary
import md_theme_light_secondaryContainer
import md_theme_light_surface
import md_theme_light_surfaceTint
import md_theme_light_surfaceVariant
import md_theme_light_tertiary
import md_theme_light_tertiaryContainer

private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    scrim = md_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    scrim = md_theme_dark_scrim,
)

@Composable
fun ComposeTvTheme(
    useDarkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = {
            CompositionLocalProvider(LocalContentColor provides colors.onSurface) {
                content()
            }
        }
    )
}