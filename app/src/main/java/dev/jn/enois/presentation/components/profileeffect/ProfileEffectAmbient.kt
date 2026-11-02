package dev.jn.enois.presentation.components.profileeffect

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import coil3.compose.LocalPlatformContext
import dev.jn.enois.presentation.components.avatar.fx.CssEaseInOut
import dev.jn.enois.presentation.components.avatar.fx.LocalAvatarFxClock
import dev.jn.enois.presentation.components.avatar.fx.SeededRandom
import dev.jn.enois.presentation.components.avatar.fx.cycle
import dev.jn.enois.presentation.components.avatar.fx.kf
import dev.jn.enois.presentation.components.avatar.fx.r2
import dev.jn.enois.presentation.components.avatar.fx.rememberFreeRunningClock
import dev.jn.enois.presentation.components.avatar.fx.seasonal.SeasonalIcons
import dev.jn.enois.presentation.components.styledname.rememberAnimationsEnabled

private class Sparkle(
    val left: Float,
    val top: Float,
    val delay: Float,
    val duration: Float,
    val scale: Float,
)

private val AMBIENT: List<Sparkle> = run {
    val rnd = SeededRandom(77)
    List(16) {
        Sparkle(
            left = r2(rnd.next() * 94).toFloat(),
            top = r2(4 + rnd.next() * 92).toFloat(),
            delay = r2(-rnd.next() * 2.4).toFloat(),
            duration = r2(2 + rnd.next() * 2).toFloat(),
            scale = r2(0.6 + rnd.next() * 0.6).toFloat(),
        )
    }
}

@Composable
fun ProfileEffectAmbient(
    effect: String?,
    modifier: Modifier = Modifier,
) {
    val spec = profileEffect(effect) ?: return
    val platform = LocalPlatformContext.current
    val density = LocalDensity.current.density
    val animate = rememberAnimationsEnabled()
    val clock = LocalAvatarFxClock.current ?: rememberFreeRunningClock()

    LaunchedEffect(spec.icon) { SeasonalIcons.load(platform, setOf(spec.icon)) }

    Canvas(modifier) {
        val img = SeasonalIcons.get(spec.icon) ?: return@Canvas
        val t = if (animate) clock.floatValue else 0f
        val w = size.width / density
        val h = size.height / density

        withTransform({ scale(density, density, Offset.Zero) }) {   // coordenadas em dp
            for (s in AMBIENT) {
                val alpha = if (animate) {
                    kf(
                        cycle(t, s.duration, s.delay),
                        CssEaseInOut,
                        0f to 0.35f,
                        0.5f to 1f,
                        1f to 0.35f
                    )
                } else 0.4f

                val k = s.scale * 0.5f
                withTransform({
                    translate(s.left / 100f * w + 6f, s.top / 100f * h + 6f)
                    scale(k, k, Offset.Zero)
                }) {
                    drawImage(
                        img,
                        dstOffset = IntOffset(-12, -12),
                        dstSize = IntSize(24, 24),
                        alpha = alpha,
                        filterQuality = FilterQuality.High,
                    )
                }
            }
        }
    }
}