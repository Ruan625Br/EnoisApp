package dev.jn.enois.presentation.components.cardtheme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

data class CardColors(
    val top: Color,
    val bottom: Color,
    val accent: Color,
    val fg: Color,
    val muted: Color,
    val surface: Color,
)

val LocalCardColors = compositionLocalOf<CardColors> { error("fora de ProfileCard") }

@Composable
fun resolveCardColors(theme: CardTheme?): CardColors {
    val s = MaterialTheme.colorScheme
    return CardColors(
        top = theme?.top ?: s.surfaceContainerHighest,       // --card
        bottom = theme?.bottom ?: s.surfaceContainerHighest,
        accent = theme?.accent ?: s.primary,                 // --primary
        fg = theme?.fg ?: s.onSurface,                       // --foreground
        muted = theme?.muted ?: s.onSurfaceVariant,          // --muted-foreground
        surface = theme?.surface ?: s.surfaceVariant,        // --muted
    )
}