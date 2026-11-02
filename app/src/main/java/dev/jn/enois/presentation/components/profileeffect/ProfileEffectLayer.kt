package dev.jn.enois.presentation.components.profileeffect

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import coil3.compose.LocalPlatformContext
import dev.jn.enois.presentation.components.avatar.fx.seasonal.SeasonalIcons
import dev.jn.enois.presentation.components.styledname.rememberAnimationsEnabled
import kotlin.random.Random


@Composable
fun ProfileEffectLayer(
    effect: String?,
    modifier: Modifier = Modifier,
    replayKey: Any? = null,
    enabled: Boolean = true,
) {
    val platform = LocalPlatformContext.current
    val density = LocalDensity.current.density

    var box by remember { mutableStateOf(IntSize.Zero) }
    var particles by remember { mutableStateOf<List<Particle>>(emptyList()) }
    var elapsed by remember { mutableFloatStateOf(0f) }
    val ready =
        box.width > 0 && box.height > 0

    val animationsEnabled = rememberAnimationsEnabled()
    LaunchedEffect(effect, replayKey, enabled, ready) {
        val spec = profileEffect(effect)
        if (spec == null || !enabled || !ready || !animationsEnabled) return@LaunchedEffect

        if (spec.icons.isNotEmpty()) SeasonalIcons.load(platform, spec.icons.toSet())

        val list = profileEffectParticles(
            spec,
            box.width / density,
            box.height / density
        ) { Random.nextFloat() }
        val end = list.maxOf { it.endMs }
        particles = list
        elapsed = 0f
        try {
            val start = withFrameNanos { it }
            while (true) {
                val ms = (withFrameNanos { it } - start) / 1_000_000f
                elapsed = ms
                if (ms >= end) break
            }
        } finally {
            particles = emptyList()
        }
    }

    Canvas(
        modifier
            .onSizeChanged { box = it }
            .clipToBounds()) {
        val list = particles
        if (list.isEmpty()) return@Canvas
        val t = elapsed
        withTransform({ scale(density, density, Offset.Zero) }) {
            for (p in list) drawParticle(p, t)
        }
    }
}

private fun DrawScope.drawParticle(p: Particle, ms: Float) {
    val e = p.progressAt(ms)
    val alpha = p.opacityAt(e)
    if (alpha <= 0.001f) return
    val pose = p.poseAt(e)
    val half = p.size / 2f

    withTransform({
        translate(pose.x + half, pose.y + half)
        rotate(pose.rot, Offset.Zero)
        scale(pose.scale, pose.scale, Offset.Zero)
    }) {
        if (p.icon != null) {
            val img = SeasonalIcons.get(p.icon) ?: return@withTransform
            val k = p.size / 24f
            scale(k, k, Offset.Zero) {
                drawImage(
                    img,
                    dstOffset = IntOffset(-12, -12),
                    dstSize = IntSize(24, 24),
                    alpha = alpha,
                    filterQuality = FilterQuality.High,
                )
            }
        } else {
            drawCircle(p.color!!, radius = p.size * 7f / 16f, center = Offset.Zero, alpha = alpha)
        }
    }
}