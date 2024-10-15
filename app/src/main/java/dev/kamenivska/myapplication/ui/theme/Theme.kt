package dev.kamenivska.myapplication.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val GidraColorScheme = darkColorScheme(
    surface = DefaultBackground,
    background = DefaultBackground,
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun GidraTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = GidraColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}