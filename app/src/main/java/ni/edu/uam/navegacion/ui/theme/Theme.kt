package ni.edu.uam.navegacion.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = lightColorScheme(
    primary = DustyLavender,
    onPrimary = DarkText,
    primaryContainer = SoftLavender,
    onPrimaryContainer = DarkText,
    secondary = SoftMint,
    onSecondary = DarkText,
    secondaryContainer = SoftMint,
    onSecondaryContainer = DarkText,
    background = MatteCream,
    onBackground = DarkText,
    surface = SoftWhite,
    onSurface = DarkText,
    outline = MutedText
)

@Composable
fun NavegacionTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}