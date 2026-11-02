package dev.jn.enois.presentation.components.cardtheme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Cores do card de perfil (`--p-*` na web), mais os tokens do app que dependem do tom do card.
 * Token nulo = o card mantém o valor do design system.
 */
data class CardTheme(
    val top: Color,                    // --p-top
    val bottom: Color,                 // --p-bottom
    val accent: Color,                 // --p-accent
    val fg: Color,                     // --p-fg
    val muted: Color,                  // --p-muted
    val surface: Color,                // --p-surface
    val primary: Color? = null,                // --primary
    val primaryForeground: Color? = null,      // --primary-foreground
    val primaryText: Color? = null,            // --primary-text
    val opportunityForeground: Color? = null,  // --opportunity-foreground
)

val LocalCardTheme = compositionLocalOf<CardTheme?> { null }

private fun hex(v: Long) = Color(0xFF000000L or v)

fun mixSrgb(a: Color, b: Color, fractionA: Float): Color = Color(
    red = a.red * fractionA + b.red * (1f - fractionA),
    green = a.green * fractionA + b.green * (1f - fractionA),
    blue = a.blue * fractionA + b.blue * (1f - fractionA),
    alpha = a.alpha * fractionA + b.alpha * (1f - fractionA),
)

private fun dark(top: Long, bottom: Long, accent: Long): CardTheme {
    val acc = hex(accent)
    return CardTheme(
        top = hex(top),
        bottom = hex(bottom),
        accent = acc,
        fg = hex(0xF5F3FF),
        muted = Color(245, 243, 255, (0.7f * 255).toInt()),
        surface = Color(255, 255, 255, (0.09f * 255).toInt()),
        primary = acc,
        primaryForeground = hex(0x1C1917),
        primaryText = mixSrgb(acc, Color.White, 0.30f),
        opportunityForeground = hex(0xFFE4CA),
    )
}

private fun light(top: Long, bottom: Long, accent: Long) = CardTheme(
    top = hex(top),
    bottom = hex(bottom),
    accent = hex(accent),
    fg = hex(0x1C1917),
    muted = Color(28, 25, 23, (0.62f * 255).toInt()),
    surface = Color(28, 25, 23, (0.07f * 255).toInt()),
    primaryText = hex(0x5736DB),
    opportunityForeground = hex(0x260F00), // oklch(0.2 0.05 60)
)

/** Temas de card das coleções sazonais (itens `CARD_THEME`). */
val CARD_THEMES: Map<String, CardTheme> = mapOf(
    "tema-saci" to dark(0x166534, 0x052E16, 0xEF4444),
    "tema-halloween" to dark(0x1E1B4B, 0x0B0A1F, 0xF97316),
    "tema-folclore" to dark(0x115E59, 0x042F2E, 0xF59E0B),
    "tema-natal" to dark(0x7F1D1D, 0x14532D, 0xFACC15),
    "tema-reveillon" to dark(0x0F172A, 0x1E3A8A, 0xFDE047),
    "tema-carnaval" to dark(0x86198F, 0x1E1B4B, 0xF472B6),
    "tema-junina" to dark(0x7C2D12, 0x1C1917, 0xFACC15),
    "tema-namorados" to dark(0x9D174D, 0x4C0519, 0xFDA4AF),
    "tema-flor" to light(0xFEF9C3, 0xFDE68A, 0xCA8A04),
    "tema-dev" to dark(0x020617, 0x111827, 0x4ADE80),
    "tema-criancas" to light(0xE0F2FE, 0xFEF3C7, 0xF43F5E),
)

fun cardTheme(id: String?): CardTheme? = id?.let { CARD_THEMES[it] }