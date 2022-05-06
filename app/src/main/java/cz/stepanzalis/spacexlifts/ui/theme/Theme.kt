package cz.stepanzalis.spacexlifts.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val AppBarElevation = 2.dp

private val DarkColorPalette = darkColors(
    primary = Color.White,
    primaryVariant = LiftingInverted,
    secondary = LiftingGrey,
    background = LiftingBlack,
)

private val LightColorPalette = lightColors(
    primary = LiftingBlack,
    primaryVariant = LiftingInverted,
    secondary = LiftingInverted,
    background = Color.White,
)

data class Spacing(
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp,
)

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

private val LocalSpacing = compositionLocalOf { Spacing() }

@Composable
fun SpaceXLiftsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content,
        )
    }
}