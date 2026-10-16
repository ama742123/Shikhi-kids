package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = ShikhiYellowDark,
    onPrimary = Color.White,
    primaryContainer = ShikhiYellowLight,
    onPrimaryContainer = ShikhiTextDark,
    secondary = ShikhiSkyBlue,
    onSecondary = Color.White,
    secondaryContainer = ShikhiBlueLight,
    onSecondaryContainer = ShikhiBlueDark,
    tertiary = ShikhiCoral,
    onTertiary = Color.White,
    tertiaryContainer = ShikhiCoralLight,
    onTertiaryContainer = ShikhiCoralDark,
    background = ShikhiBackground,
    onBackground = ShikhiTextDark,
    surface = ShikhiSurface,
    onSurface = ShikhiTextDark,
    surfaceVariant = ShikhiYellowLight,
    onSurfaceVariant = ShikhiTextMuted
)

private val DarkColorScheme = darkColorScheme(
    primary = ShikhiYellow,
    onPrimary = ShikhiTextDark,
    primaryContainer = ShikhiYellowDark,
    onPrimaryContainer = Color.White,
    secondary = ShikhiSkyBlue,
    onSecondary = Color.Black,
    tertiary = ShikhiCoral,
    background = Color(0xFF1E1B18),
    surface = Color(0xFF2C2724),
    onBackground = Color(0xFFFFF7ED),
    onSurface = Color(0xFFFFF7ED)
)

@Composable
fun ShikhiKidsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Preserve our hand-tuned kid palette by default
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// Keep alias for backwards compatibility
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) = ShikhiKidsTheme(darkTheme, dynamicColor, content)
