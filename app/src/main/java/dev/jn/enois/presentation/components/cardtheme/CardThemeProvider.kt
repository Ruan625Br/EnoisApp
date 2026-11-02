package dev.jn.enois.presentation.components.cardtheme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@Composable
fun CardThemeProvider(
    id: String?,
    content: @Composable () -> Unit
) {
    if (id == null) {
        content(); return
    }

    val theme = cardTheme(id)
    CardThemeProvider(theme, content)
}

@Composable
fun CardThemeProvider(theme: CardTheme?, content: @Composable () -> Unit) {
    if (theme == null) {
        content(); return
    }

    val base = MaterialTheme.colorScheme
    val scheme = base.copy(
        primary = theme.primary ?: base.primary,
        onPrimary = theme.primaryForeground ?: base.onPrimary,
        onSurface = theme.fg,
        onSurfaceVariant = theme.muted,
    )
    CompositionLocalProvider(LocalCardTheme provides theme) {
        MaterialTheme(
            colorScheme = scheme,
            typography = MaterialTheme.typography,
            content = content
        )
    }
}
