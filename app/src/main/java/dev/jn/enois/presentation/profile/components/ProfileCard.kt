package dev.jn.enois.presentation.profile.components

import android.graphics.BlurMaskFilter
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dev.jn.enois.presentation.components.cardtheme.LocalCardColors
import dev.jn.enois.presentation.components.cardtheme.LocalCardTheme
import dev.jn.enois.presentation.components.cardtheme.cardTheme
import dev.jn.enois.presentation.components.cardtheme.resolveCardColors

private val CssEase = CubicBezierEasing(0.25f, 0.1f, 0.25f, 1f)

@Composable
fun ProfileCard(
    themeId: String?,
    modifier: Modifier = Modifier,
    rx: Float = 0f,
    ry: Float = 0f,
    content: @Composable () -> Unit,
) {
    val theme = cardTheme(themeId)
    val c = resolveCardColors(theme)
    val shape = RoundedCornerShape(20.dp)
    val density = LocalDensity.current.density

    val tiltX by animateFloatAsState(rx, tween(400, easing = CssEase), label = "rx")
    val tiltY by animateFloatAsState(ry, tween(400, easing = CssEase), label = "ry")

    val base = MaterialTheme.colorScheme
    val themedScheme = if (theme == null) base else base.copy(
        primary = theme.primary ?: base.primary,
        onPrimary = theme.primaryForeground ?: base.onPrimary,
        onSurface = c.fg,
        onBackground = c.fg,
        onSurfaceVariant = c.muted,
        surfaceContainer = c.surface,
        surfaceContainerHigh = c.surface,
        surfaceVariant = c.surface,
        outlineVariant = c.fg.copy(alpha = 0.18f),
    )

    Box(
        modifier
            .zIndex(1f)
            .graphicsLayer {
                rotationX = tiltX
                rotationY = tiltY
                cameraDistance = 1000f * density
            }
            .dropShadow(
                shape = RoundedCornerShape(20.dp),
                shadow = Shadow(
                    radius = 70.dp,
                    spread = (-28).dp,
                    offset = DpOffset(0.dp, 28.dp),
                    color = c.accent,
                    alpha = 0.75f,
                ),
            )
            .clip(shape)
            .background(Brush.verticalGradient(listOf(c.top, c.bottom)))
            .border(1.dp, c.accent.copy(alpha = 0.40f), shape)
    ) {
        CompositionLocalProvider(LocalCardTheme provides theme, LocalCardColors provides c) {
            content()

        }
    }
}

private fun Modifier.accentGlow(color: Color): Modifier = drawBehind {
    val inset = 28.dp.toPx()
    val paint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
        this.color = color.toArgb()
        maskFilter = BlurMaskFilter(60.dp.toPx(), BlurMaskFilter.Blur.NORMAL) // sigma ≈ 35
    }
    drawIntoCanvas { canvas ->
        canvas.nativeCanvas.drawRect(
            inset,
            inset + 28.dp.toPx(),
            size.width - inset,
            size.height,
            paint,
        )
    }
}