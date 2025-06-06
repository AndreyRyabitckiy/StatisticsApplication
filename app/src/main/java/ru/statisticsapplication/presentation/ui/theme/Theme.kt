package ru.statisticsapplication.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    background = Grey,
    primaryContainer = White,
    onPrimary = Black,
    error = Red,
    onError = GreenOnline,
    errorContainer = Orange,
    secondary = DarkGrey,
    secondaryContainer = DividerGrey,
    onSecondary = DarkGreyBorder,
    onSecondaryContainer = BorderGrey
)

//   private val DarkColorScheme = darkColorScheme()

@Composable
fun StatisticsApplicationTheme(
    content: @Composable () -> Unit,
//    darkTheme:Boolean = isSystemInDarkTheme()
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        //if (darkTheme) DarkColorScheme else LightColorScheme
        typography = Typography,
        content = content
    )
}