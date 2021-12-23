package com.searcharchitect.two.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = LightBlue400,
    primaryVariant = Amber500,
    onPrimary = Color.White,

    secondary = Teal400,
    secondaryVariant = Pink500,
    onSecondary = Color.White,

    background = Color(0xFF121212),
    onBackground = Gray200,

    surface = Color(0xFF121212),
    onSurface = Gray200,

    error = Color(0xFFCF6679),
    onError = Color.Black
)

private val LightColorPalette = lightColors(
    primary = LightBlue400,
    primaryVariant = Amber500,
    onPrimary = Color.White,

    secondary = Teal400,
    secondaryVariant = Pink500,
    onSecondary = Color.White,

    background = Color.White,
    onBackground = Gray800,

    surface = Color.White,
    onSurface = Gray800,

    error = Color(0xFFB00020),
    onError = Color.White
)

@Composable
fun SearchArchitectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    // Сейчас цвет SystemBars устанавливается через xml
    // Данный способ работает с задеркой, при запуске видно предыдущий цвет
    /*val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Amber500
        )
    }*/

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}