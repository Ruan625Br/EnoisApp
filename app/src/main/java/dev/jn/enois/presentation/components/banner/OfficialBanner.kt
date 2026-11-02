package dev.jn.enois.presentation.components.banner

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import coil3.compose.LocalPlatformContext
import dev.jn.enois.presentation.components.avatar.fx.SeededRandom
import dev.jn.enois.presentation.components.avatar.fx.r2
import dev.jn.enois.presentation.components.avatar.fx.seasonal.SeasonalIcons
import kotlin.math.max

private const val W = 300f
private const val H = 100f


private val GroundPath = Path().apply {
    moveTo(0f, 84f)
    cubicTo(60f, 72f, 110f, 94f, 170f, 82f)
    cubicTo(220f, 72f, 260f, 88f, 300f, 80f)
    lineTo(300f, 100f); lineTo(0f, 100f); close()
}

private data class Placed(
    val icon: String,
    val x: Float,
    val y: Float,
    val scale: Float,
    val rotation: Float,
)

private fun scatter(spec: OfficialBannerSpec): List<Placed> {
    val rnd = SeededRandom(spec.seed)

    return List(spec.count) { i ->
        val x = 16.0 + (i + rnd.next() * 0.6) * (268.0 / spec.count)
        val y = spec.y.start + rnd.next() * (spec.y.endInclusive - spec.y.start)
        val s = r2(spec.scale.start + rnd.next() * (spec.scale.endInclusive - spec.scale.start))
        val rot = (rnd.next() - 0.5) * 24.0

        Placed(
            icon = spec.icons[i % spec.icons.size],
            x = x.toFloat(),
            y = y.toFloat(),
            scale = s.toFloat(),
            rotation = rot.toFloat(),
        )
    }
}

@Composable
fun OfficialBanner(banner: String, modifier: Modifier = Modifier) {
    val spec = OFFICIAL_BANNERS[banner] ?: return
    val placed = remember(spec) { scatter(spec) }

    val context = LocalPlatformContext.current
    LaunchedEffect(spec) { SeasonalIcons.load(context, spec.icons.toSet()) }

    Canvas(modifier) {
        val s = max(size.width / W, size.height / H)
        val dx = (size.width - W * s) / 2f
        val dy = (size.height - H * s) / 2f

        clipRect {
            translate(dx, dy) {
                scale(s, s, pivot = Offset.Zero) {
                    drawRect(
                        brush = Brush.linearGradient(
                            listOf(spec.sky.first, spec.sky.second),
                            start = Offset.Zero,
                            end = Offset(W, H),
                        ),
                        size = Size(W, H),
                    )
                    spec.extra?.invoke(this)
                    spec.ground?.let { drawPath(GroundPath, it) }

                    placed.forEach { p ->
                        val img = SeasonalIcons.get(p.icon) ?: return@forEach
                        withTransform({
                            translate(p.x, p.y)
                            rotate(p.rotation, Offset.Zero)
                            scale(p.scale, p.scale, Offset.Zero)
                        }) {
                            drawImage(
                                img,
                                dstOffset = IntOffset(-12, -12),
                                dstSize = IntSize(24, 24),
                                filterQuality = FilterQuality.High,
                            )
                        }
                    }
                }
            }
        }
    }
}