package cz.stepanzalis.spacexlifts.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.White,
    primaryVariant = LiftingInverted,
    secondary = LiftingGrey,
    background = LiftingBlack,
)

private val LightColorPalette = lightColors(
    primary = LiftingBlack,
    primaryVariant = LiftingInverted,
    secondary = LiftingGrey,
    background = Color.White,
)

@Composable
fun SpaceXLiftsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}