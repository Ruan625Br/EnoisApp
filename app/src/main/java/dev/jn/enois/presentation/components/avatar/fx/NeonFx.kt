package dev.jn.enois.presentation.components.avatar.fx

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.util.lerp
import dev.jn.enois.presentation.components.avatar.fx.seasonal.rf

private val rand = SeededRandom(83)

private const val TUBE_R = 54f
private const val GLOW_IN = 47f
private const val GLOW_OUT = 63f
private const val FLOOR = 73f

private class NeonRain(val dur: Float, val width: Float, val path: Path)

private val RAIN = listOf(0.8f, 1.05f, 1.4f).mapIndexed { layer, dur ->
    val path = Path().apply {
        repeat(9) {
            val x = r2(-74 + rand.next() * 148).toFloat()
            val y = r2(-80 + rand.next() * 160).toFloat()
            val len = r2(6 + rand.next() * 4 - layer).toFloat()
            moveTo(x, y); relativeLineTo(0f, len)
            moveTo(x, rf(y - 160f)); relativeLineTo(0f, len)
        }
    }
    NeonRain(dur, r2(0.9 - layer * 0.2).toFloat(), path)
}

private class NeonDrip(val x: Float, val y: Float, val dur: Float, val delay: Float)

private val DRIPS = listOf(158.0, 181.0, 203.0).map { a ->
    val o = polar(TUBE_R + 1.5, a)
    NeonDrip(o.x, o.y, r2(2.2 + rand.next() * 1.2).toFloat(), r2(-rand.next() * 3).toFloat())
}

private class NeonSplash(
    val x: Float,
    val y: Float,
    val dx: Float,
    val dy: Float,
    val dur: Float,
    val delay: Float
)

private val SPLASHES = List(5) {
    val a = (rand.next() - 0.5) * 70
    val o = polar(TUBE_R + 1.0, a)
    val d = polar(4 + rand.next() * 4, a + (rand.next() - 0.5) * 60)
    NeonSplash(
        o.x, o.y, d.x, d.y,
        r2(0.7 + rand.next() * 0.5).toFloat(), r2(-rand.next() * 1.2).toFloat(),
    )
}

private class NeonBead(val x: Float, val y: Float, val delay: Float)

private val BEADS = List(7) { i ->
    val o = polar(TUBE_R + (rand.next() - 0.5) * 1.6, -100.0 + i * 30 + rand.next() * 18)
    NeonBead(o.x, o.y, r2(-rand.next() * 2.4).toFloat())
}

private val CABLE_ROOT = polar(TUBE_R.toDouble(), 135.0)

private val BOLT = Path().apply {
    moveTo(-62f, -64f); lineTo(-54f, -58f); lineTo(-57.5f, -56f); lineTo(-49f, -50.5f)
    lineTo(-52.5f, -49f); lineTo(-45f, -45f)
}

private val DROP = Path().apply {
    moveTo(0f, -1.6f)
    cubicTo(1.4f, 0f, 1.5f, 1.2f, 0f, 2f)
    cubicTo(-1.5f, 1.2f, -1.4f, 0f, 0f, -1.6f)
    close()
}

private val CABLE = Path().apply {
    moveTo(0f, 0f)
    cubicTo(3f, 7f, 12f, 8f, 14f, 19f)
}

private val OUTSIDE = Path().apply {
    fillType = androidx.compose.ui.graphics.PathFillType.EvenOdd
    addRect(androidx.compose.ui.geometry.Rect(-80f, -80f, 80f, 80f))
    addOval(androidx.compose.ui.geometry.Rect(-GLOW_OUT, -GLOW_OUT, GLOW_OUT, GLOW_OUT))
}
private val BAND = fxRingPath(GLOW_OUT, GLOW_IN)

private val NIGHT = Brush.radialGradient(
    0.58f to rgb(0x1E1B4B, 0f), 0.66f to rgb(0x1E1B4B, 0.38f),
    0.88f to rgb(0x1E1B4B, 0.14f), 1f to rgb(0x1E1B4B, 0f),
    center = Offset.Zero, radius = 80f,
)
private val FLASH = Brush.radialGradient(
    0.58f to rgb(0xE0E7FF, 0f), 0.68f to rgb(0xE0E7FF, 0.5f), 1f to rgb(0xE0E7FF, 0f),
    center = Offset.Zero, radius = 80f,
)
private val PUDDLE = Brush.radialGradient(
    0f to rgb(0xFF4FD8, 0.55f), 1f to rgb(0xFF4FD8, 0f), center = Offset.Zero, radius = 36f,
)

private val EaseRainDrop = CubicBezierEasing(0.55f, 0f, 1f, 0.45f)

private val GLOW_STROKES = listOf(9f to 0.12f, 4.5f to 0.3f)

private fun DrawScope.rain(t: Float, color: Color, alpha: Float) {
    for (r in RAIN) {
        withTransform({ translate(0f, 160f * cycle(t, r.dur)) }) {
            drawPath(r.path, color, alpha, Stroke(r.width, cap = StrokeCap.Round))
        }
    }
}

internal fun DrawScope.drawNeon(t: Float, still: Boolean = false) {
    drawCircle(NIGHT, 80f, Offset.Zero)

    if (!still) {
        val flash = kf(
            cycle(t, 9f),
            LinearEasing,
            0f to 0f,
            0.79f to 0f,
            0.80f to 1f,
            0.81f to 0.15f,
            0.825f to 0.85f,
            0.85f to 0f,
            1f to 0f,
        )
        if (flash > 0.01f) {
            drawCircle(FLASH, 80f, Offset.Zero, flash)
            drawPath(BOLT, rgb(0xEEF2FF), flash, Stroke(1f, join = StrokeJoin.Round))
        }
    }

    if (!still) {
        clipPath(OUTSIDE) { rain(t, rgb(0x94A3B8), 0.7f) }
        clipPath(BAND) { rain(t, rgb(0xFFB3EC), 0.95f) }
    }

    val pk = if (still) 0f else CssEaseInOut.transform(fxAlternate(t, 2.2f))
    val puddleX = if (still) 1f else lerp(0.94f, 1.05f, pk)
    val puddleOp = if (still) 1f else lerp(0.75f, 1f, pk)
    withTransform({
        scale(puddleX, 1f, Offset.Zero)
        translate(0f, FLOOR)
        scale(1f, 4.5f / 36f, Offset.Zero)
    }) { drawCircle(PUDDLE, 36f, Offset.Zero, puddleOp) }

    if (!still) {
        for (d in DRIPS) {
            val p = cycle(t, d.dur, d.delay)

            val wsc = kf(p, CssEaseOut, 0f to 0.2f, 0.45f to 1.6f, 1f to 1.6f)
            val wop = kf(p, CssEaseOut, 0f to 0.9f, 0.45f to 0f, 1f to 0f)
            if (wop > 0.01f) {
                withTransform({
                    translate(d.x, FLOOR)
                    scale(wsc, wsc, Offset.Zero)
                }) {
                    drawOval(
                        rgb(0xFFB3EC),
                        Offset(-5f, -1.2f),
                        Size(10f, 2.4f),
                        wop,
                        Stroke(0.5f)
                    )
                }
            }

            val fall = FLOOR - d.y
            val ty: Float
            val sx: Float
            val sy: Float
            when {
                p < 0.5f -> {
                    val f = CssEaseOut.transform(p / 0.5f)
                    ty = lerp(0f, 1f, f); sx = lerp(0.3f, 1f, f); sy = sx
                }

                p < 0.58f -> {
                    val f = CssEaseInOut.transform((p - 0.5f) / 0.08f)
                    ty = lerp(1f, 2f, f); sx = lerp(1f, 0.9f, f); sy = lerp(1f, 1.2f, f)
                }

                else -> {
                    val f = EaseRainDrop.transform((p - 0.58f) / 0.42f)
                    ty = lerp(2f, fall, f); sx = lerp(0.9f, 0.8f, f); sy = lerp(1.2f, 1.3f, f)
                }
            }
            val dop = if (p < 0.12f) lerp(0f, 1f, CssEaseOut.transform(p / 0.12f))
            else lerp(1f, 0.9f, FxEase.transform((p - 0.12f) / 0.88f))
            withTransform({
                translate(d.x, d.y)
                translate(0f, ty)
                scale(sx, sy, Offset.Zero)
            }) { drawPath(DROP, rgb(0xFFD6F5), dop) }
        }
    }

    val tube = if (still) 1f else kf(
        cycle(t, 6f),
        LinearEasing,
        0f to 1f,
        0.70f to 1f,
        0.71f to 0.35f,
        0.715f to 1f,
        0.725f to 0.55f,
        0.735f to 1f,
        1f to 1f,
    )
    for ((w, a) in GLOW_STROKES) ring(rgb(0xFF4FD8), TUBE_R, w, a * tube)
    ring(rgb(0xFFE4FA), TUBE_R, 1.4f, tube)

    for (angle in listOf(70f, 290f)) {
        withTransform({
            rotate(angle, Offset.Zero)
            translate(0f, -TUBE_R)
        }) {
            drawLine(rgb(0x64748B), Offset.Zero, Offset(0f, -7f), 1f)
            drawCircle(rgb(0x94A3B8), 1.1f, Offset(0f, -7.4f))
            drawRoundRect(rgb(0x475569), Offset(-1.7f, -2.8f), Size(3.4f, 5.6f), CornerRadius(0.6f))
        }
    }

    val swing = if (still) 0f else lerp(-3f, 3f, CssEaseInOut.transform(fxAlternate(t, 3.4f)))
    withTransform({
        translate(CABLE_ROOT.x, CABLE_ROOT.y)
        rotate(swing, Offset.Zero)
    }) {
        drawPath(CABLE, rgb(0x475569), style = Stroke(1.1f))
        drawRoundRect(rgb(0x334155), Offset(9.5f, 19f), Size(9f, 6.5f), CornerRadius(0.8f))
        drawRoundRect(
            rgb(0x64748B),
            Offset(9.5f, 19f),
            Size(9f, 6.5f),
            CornerRadius(0.8f),
            Stroke(0.5f)
        )
        val led = if (still || cycle(t, 1.4f) < 0.5f) 1f else 0.15f
        drawCircle(rgb(0xFB7185), 0.75f, Offset(16f, 22.2f), led)
    }

    for (b in BEADS) {
        val op = if (still) 1f else kf(
            cycle(t, 2.4f, b.delay),
            CssEaseInOut,
            0f to 0.35f,
            0.5f to 1f,
            1f to 0.35f
        )
        drawCircle(Color.White, 0.75f, Offset(b.x, b.y), op)
    }

    if (!still) {
        for (s in SPLASHES) {
            val p = cycle(t, s.dur, s.delay)
            val m = CssEaseOut.transform(p)
            val op = kf(p, CssEaseOut, 0f to 0f, 0.1f to 1f, 1f to 0f)
            if (op <= 0.01f) continue
            drawCircle(rgb(0xFFE4FA), 0.7f, Offset(s.x + s.dx * m, s.y + s.dy * m), op)
        }
    }
}