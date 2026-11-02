package dev.jn.enois.presentation.components.avatar.fx

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import kotlin.math.pow

private val rand = SeededRandom(97)

private const val SWEEP = 4f
private const val INNER = 47.5f
private const val OUTER = 61f

private fun sector(from: Float, to: Float): Path = Path().apply {
    val p1 = polar(OUTER.toDouble(), from.toDouble())
    val p3 = polar(INNER.toDouble(), to.toDouble())
    moveTo(p1.x, p1.y)
    arcTo(Rect(-OUTER, -OUTER, OUTER, OUTER), from - 90f, to - from, false)
    lineTo(p3.x, p3.y)
    arcTo(Rect(-INNER, -INNER, INNER, INNER), to - 90f, from - to, false)
    close()
}

private val TRAIL = List(9) { i ->
    Pair(sector(-(i + 1) * 6f, -i * 6f), r2(0.42 * (1 - i / 9.0).pow(1.6)).toFloat())
}

private class RadarTick(val a: Offset, val b: Offset, val long: Boolean)

private val TICKS = List(36) { i ->
    val angle = i * 10.0
    val long = i * 10 % 30 == 0
    RadarTick(polar(OUTER.toDouble(), angle), polar(OUTER + (if (long) 3.5 else 2.0), angle), long)
}

private class RadarBlip(val x: Float, val y: Float, val r: Float, val delay: Float, val fill: Brush)

private fun contactBrush(r: Float) = Brush.radialGradient(
    0f to rgb(0xF0FDF4), 0.3f to rgb(0x4ADE80, 0.9f), 1f to rgb(0x22C55E, 0f),
    center = Offset.Zero, radius = r,
)

private val BLIPS = List(9) { i ->
    val angle = r2(i * 40 + rand.next() * 30)
    val far = i % 3 == 2
    val o = polar(if (far) 66 + rand.next() * 6 else 50.5 + rand.next() * 8, angle)
    val r = r2(if (far) 1.1 else 1.4 + rand.next() * 0.6).toFloat()
    RadarBlip(o.x, o.y, r, r2(angle / 360 * SWEEP - SWEEP).toFloat(), contactBrush(r * 2.4f))
}

private class RadarTarget(val x: Float, val y: Float, val delay: Float)

private val TARGETS = List(5) { i ->
    val angle = 228.0 + i * 5
    val o = polar(54 - i * 0.6, angle)
    RadarTarget(o.x, o.y, r2(angle / 360 * SWEEP - SWEEP * (5 - i)).toFloat())
}

private val TARGET_GLOW = contactBrush(3.6f)

private val CROSSHAIR = Path().apply {
    moveTo(-5f, -2.4f); lineTo(-5f, -5f); lineTo(-2.4f, -5f)
    moveTo(2.4f, -5f); lineTo(5f, -5f); lineTo(5f, -2.4f)
    moveTo(5f, 2.4f); lineTo(5f, 5f); lineTo(2.4f, 5f)
    moveTo(-2.4f, 5f); lineTo(-5f, 5f); lineTo(-5f, 2.4f)
}

private val NORTH =
    Path().apply { moveTo(0f, -66.5f); lineTo(-2.4f, -70.5f); lineTo(2.4f, -70.5f); close() }
private val SCREEN = fxRingPath(OUTER, INNER)
private val GREEN = rgb(0x22C55E)
private val DOTS = PathEffect.dashPathEffect(floatArrayOf(1f, 2.5f), 0f)

internal fun DrawScope.drawRadar(t: Float, still: Boolean = false) {
    drawPath(SCREEN, rgb(0x052E16), 0.92f)

    drawCircle(GREEN, 54.2f, Offset.Zero, 0.25f, Stroke(0.5f, pathEffect = DOTS))
    drawCircle(GREEN, OUTER, Offset.Zero, 0.8f, Stroke(0.9f))
    drawCircle(GREEN, INNER, Offset.Zero, 0.6f, Stroke(0.7f))
    for (m in TICKS) {
        drawLine(GREEN, m.a, m.b, if (m.long) 1f else 0.6f, alpha = if (m.long) 0.9f else 0.5f)
    }

    drawPath(NORTH, rgb(0x86EFAC))

    if (!still) {
        val p = cycle(t, SWEEP)
        val sc = kf(p, CssEaseOut, 0f to 1f, 0.6f to 1.22f, 1f to 1.22f)
        val op = kf(p, CssEaseOut, 0f to 0.7f, 0.6f to 0f, 1f to 0f)
        if (op > 0.01f) {
            withTransform({ scale(sc, sc, Offset.Zero) }) {
                drawCircle(rgb(0x4ADE80), OUTER, Offset.Zero, op, Stroke(0.8f))
            }
        }
    }

    withTransform({ rotate(if (still) 0f else 360f * cycle(t, SWEEP), Offset.Zero) }) {
        for ((path, op) in TRAIL) drawPath(path, rgb(0x4ADE80), op)
        drawLine(rgb(0xBBF7D0), Offset(0f, -INNER), Offset(0f, -OUTER), 1.1f)
    }

    for (b in BLIPS) {
        val op = if (still) 1f else kf(
            cycle(t, SWEEP, b.delay),
            CssEaseOut,
            0f to 1f,
            0.75f to 0.08f,
            1f to 0.08f
        )
        withTransform({ translate(b.x, b.y) }) { drawCircle(b.fill, b.r * 2.4f, Offset.Zero, op) }
    }

    if (!still) {
        for (g in TARGETS) {
            val p = cycle(t, 20f, g.delay)
            val op = kf(p, CssEaseOut, 0f to 1f, 0.2f to 0.35f, 0.6f to 0f, 1f to 0f)
            if (op > 0.01f) {
                withTransform({ translate(g.x, g.y) }) {
                    drawCircle(
                        TARGET_GLOW,
                        3.6f,
                        Offset.Zero,
                        op
                    )
                }
            }
        }
        for (g in TARGETS) {
            val p = cycle(t, 20f, g.delay)
            val op = kf(p, CssEaseOut, 0f to 0f, 0.02f to 1f, 0.09f to 1f, 0.14f to 0f, 1f to 0f)
            if (op <= 0.01f) continue
            val sc = kf(p, CssEaseOut, 0f to 1.8f, 0.02f to 1f, 0.14f to 1f, 1f to 1f)
            val rot = kf(p, CssEaseOut, 0f to 45f, 0.02f to 0f, 0.14f to 0f, 1f to 0f)
            withTransform({
                translate(g.x, g.y)
                scale(sc, sc, Offset.Zero)
                rotate(rot, Offset.Zero)
            }) { drawPath(CROSSHAIR, rgb(0xBBF7D0), op, Stroke(0.8f)) }
        }
    }
}