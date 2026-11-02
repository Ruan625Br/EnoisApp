package dev.jn.enois.presentation.components.avatar.fx

import androidx.compose.animation.core.LinearEasing
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.util.lerp
import dev.jn.enois.presentation.components.avatar.fx.seasonal.CssEaseIn
import kotlin.math.PI
import kotlin.math.sin

private val rand = SeededRandom(41)

private fun surface(level: Double, amp: Double, period: Double): List<Offset> {
    val pts = ArrayList<Offset>()
    var x = -100.0
    val step = period / 8
    while (x <= 100 + period) {
        pts.add(Offset(r2(x).toFloat(), r2(level + amp * sin(x / period * 2 * PI)).toFloat()))
        x += step
    }
    return pts
}

private fun linePath(pts: List<Offset>) = Path().apply {
    pts.forEachIndexed { i, p -> if (i == 0) moveTo(p.x, p.y) else lineTo(p.x, p.y) }
}

private fun waterPath(pts: List<Offset>, period: Float) = linePath(pts).apply {
    lineTo(100f + period, 100f)
    lineTo(-100f, 100f)
    close()
}

private val BACK = waterPath(surface(19.0, 2.2, 20.0), 20f)
private val FOAM_PTS = surface(23.0, 2.6, 26.0)
private val FOAM = linePath(FOAM_PTS)
private val FRONT = waterPath(FOAM_PTS, 26f)

private val FRONT_FILL = FRONT.getBounds().let {
    Brush.verticalGradient(
        0.2f to rgb(0x38BDF8), 0.75f to rgb(0x0369A1),
        startY = it.top, endY = it.bottom,
    )
}

private class MareBubble(val x: Float, val y: Float, val r: Float, val dur: Float, val delay: Float)

private val BUBBLES = List(7) {
    val o = polar(50 + rand.next() * 9, 140 + rand.next() * 80)
    MareBubble(
        o.x, o.y,
        r2(0.7 + rand.next() * 0.9).toFloat(),
        r2(2 + rand.next() * 1.6).toFloat(),
        r2(-rand.next() * 3.6).toFloat(),
    )
}

private val TUBE = fxRingPath(60.5f, 47.5f)

private val AURA = Brush.radialGradient(
    0.68f to rgb(0x38BDF8, 0f), 0.76f to rgb(0x38BDF8, 0.3f), 0.95f to rgb(0x38BDF8, 0f),
    center = Offset.Zero, radius = 80f,
)

private val FISH_TAIL = Path().apply {
    moveTo(-4.5f, 0f); lineTo(-8f, -2.8f); lineTo(-7f, 0f); lineTo(-8f, 2.8f); close()
}
private val FISH_BODY = Path().apply {
    moveTo(5f, 0f)
    cubicTo(3f, -3.4f, -3f, -3.4f, -4.8f, 0f)
    cubicTo(-3f, 3.4f, 3f, 3.4f, 5f, 0f)
    close()
}
private val FISH_GILL = Path().apply {
    moveTo(1.2f, -2.6f)
    cubicTo(0.4f, -1f, 0.4f, 1f, 1.2f, 2.6f)
}

private val GLINT = Path().apply {
    moveTo(-44f, -31f)
    circleArcTo(-44f, -31f, 54f, large = false, sweep = true, x2 = -21f, y2 = -50f)
}

internal fun DrawScope.drawMare(t: Float, still: Boolean = false) {
    drawCircle(AURA, 80f, Offset.Zero)
    ring(rgb(0xE0F2FE), 54f, 13f, 0.1f)

    clipPath(TUBE) {
        val level = if (still) 0f else -22f * CssEaseInOut.transform(fxAlternate(t, 8f))
        val sway = if (still) 0f else lerp(-6f, 6f, CssEaseInOut.transform(fxAlternate(t, 3.4f)))
        withTransform({
            translate(0f, level)
            rotate(sway, Offset.Zero)
        }) {
            withTransform({ translate(if (still) 0f else 20f * cycle(t, 2.6f), 0f) }) {
                drawPath(BACK, rgb(0x1D4ED8))
            }
            withTransform({ translate(if (still) 0f else -26f * cycle(t, 1.9f), 0f) }) {
                drawPath(FRONT, FRONT_FILL)
                drawPath(FOAM, rgb(0xF0F9FF), style = Stroke(1.3f, cap = StrokeCap.Round))
            }
        }

        if (!still) {
            for (b in BUBBLES) {
                val p = cycle(t, b.dur, b.delay)
                val m = CssEaseIn.transform(p)
                val op = kf(p, CssEaseIn, 0f to 0f, 0.2f to 1f, 0.8f to 0.8f, 1f to 0f)
                if (op <= 0.01f) continue
                val sc = lerp(0.5f, 1.1f, m)
                withTransform({
                    translate(b.x, b.y)
                    translate(0f, -12f * m)
                    scale(sc, sc, Offset.Zero)
                }) {
                    drawCircle(rgb(0xE0F2FE), b.r, Offset.Zero, 0.3f * op)
                    drawCircle(rgb(0xF0F9FF), b.r, Offset.Zero, op, Stroke(0.45f))
                }
            }
        }

        val p7 = cycle(t, 7f)
        val swim = if (still) 0f else kf(p7, CssEaseInOut, 0f to -40f, 0.5f to 40f, 1f to -40f)
        val flip =
            if (still) 1f else kf(p7, LinearEasing, 0f to -1f, 0.499f to -1f, 0.5f to 1f, 1f to 1f)
        withTransform({
            rotate(swim, Offset.Zero)
            translate(0f, 54f)
            scale(flip, 1f, Offset.Zero)
        }) {
            drawPath(FISH_TAIL, rgb(0xF97316))
            drawPath(FISH_BODY, rgb(0xFB923C))

            drawPath(FISH_GILL, Color.Black)
            drawPath(FISH_GILL, rgb(0xFFF7ED), style = Stroke(0.9f))
            drawCircle(rgb(0x0F172A), 0.55f, Offset(3f, -0.6f))
        }
    }

    ring(rgb(0xBAE6FD), 60.5f, 0.8f, 0.75f)
    ring(rgb(0xE0F2FE), 47.5f, 0.8f, 0.6f)
    drawPath(GLINT, Color.White, 0.55f, Stroke(2.2f, cap = StrokeCap.Round))
}