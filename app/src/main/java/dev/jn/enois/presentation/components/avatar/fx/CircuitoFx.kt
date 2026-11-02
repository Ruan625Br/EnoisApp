package dev.jn.enois.presentation.components.avatar.fx

import androidx.compose.animation.core.LinearEasing
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import dev.jn.enois.presentation.components.avatar.fx.seasonal.CssEaseIn
import dev.jn.enois.presentation.components.avatar.fx.seasonal.arcPath
import dev.jn.enois.presentation.components.avatar.fx.seasonal.rf

private val rand = SeededRandom(73)

private class CircComp(val x: Float, val y: Float, val rot: Float, val body: Color)

private class CircTrail(
    val angle: Float, val path: Path, val run: Float,
    val padX: Float, val padY: Float, val dur: Float, val delay: Float, val comp: CircComp?,
)

private val TRAILS = List(10) { i ->
    val run = r2(5 + rand.next() * 6).toFloat()
    val bend = if (rand.next() > 0.5) 1 else -1
    val diag = r2(4 + rand.next() * 4).toFloat()
    val comp = if (i % 3 == 2) null else CircComp(
        rf(bend * diag / 2f), rf(-53f - run - diag / 2f),
        if (bend == 1) -45f else -135f,
        if (i % 3 == 0) rgb(0x1C1917) else rgb(0xA16207),
    )
    val angle = r2(18 + i * 36 + (rand.next() - 0.5) * 10).toFloat()
    val padY = rf(-53f - run - diag)
    val path = Path().apply {
        moveTo(0f, -53f)
        lineTo(0f, -53f - run)
        lineTo(bend * diag, padY)
    }
    val dur = r2(1.8 + rand.next() * 1.2).toFloat()
    val delay = r2(-rand.next() * 3).toFloat()
    CircTrail(angle, path, run, bend * diag, padY, dur, delay, comp)
}

private val PINS = listOf(-3.2f, 0f, 3.2f)

private val SOCKET = Path().apply {
    for (i in 0 until 60) {
        val a = polar(48.6, i * 6.0 + 3)
        val b = polar(50.4, i * 6.0 + 3)
        moveTo(a.x, a.y)
        lineTo(b.x, b.y)
    }
}

private val VIAS: List<Offset> = List(12) {
    val o = polar(60 + rand.next() * 12, rand.next() * 360)
    Offset(rf(o.x - 0.9f) + 0.9f, o.y)
}

private val PACKETS = Path().apply {
    for (a in listOf(0f, 120f, 240f)) arcTo(Rect(-57.5f, -57.5f, 57.5f, 57.5f), a - 90f, 4f, true)
}

private fun tealGlow(r: Float) = Brush.radialGradient(
    0f to rgb(0xCCFBF1), 0.35f to rgb(0x5EEAD4, 0.8f), 1f to rgb(0x14B8A6, 0f),
    center = Offset.Zero, radius = r,
)

private val GLOW_TERMINAL = tealGlow(4f)
private val GLOW_PULSE = tealGlow(2.4f)
private val GLOW_LED = tealGlow(3f)

private val DATA_DASH = PathEffect.dashPathEffect(floatArrayOf(1.5f, 4f, 6f, 4f), 0f)

private val TEAL = rgb(0x14B8A6)

private fun DrawScope.line(x1: Float, y1: Float, x2: Float, y2: Float, w: Float, color: Color) =
    drawLine(color, Offset(x1, y1), Offset(x2, y2), w)

internal fun DrawScope.drawCircuito(t: Float, still: Boolean = false) {
    for (v in VIAS) drawCircle(TEAL, 0.9f, v, 0.7f, Stroke(0.5f))
    ring(rgb(0x134E4A), 52f, 3.6f)
    ring(rgb(0x2DD4BF), 52f, 0.8f)
    drawPath(SOCKET, rgb(0x5EEAD4), 0.75f, Stroke(0.7f))

    withTransform({ rotate(if (still) 0f else 360f * cycle(t, 3.2f), Offset.Zero) }) {
        drawPath(arcPath(52f, 0f, 42f), rgb(0x5EEAD4), 0.25f, Stroke(3.4f, cap = StrokeCap.Round))
        drawPath(arcPath(52f, 12f, 42f), rgb(0xCCFBF1), style = Stroke(1.3f, cap = StrokeCap.Round))
    }

    withTransform({ rotate(if (still) 0f else 360f * cycle(t, 20f), Offset.Zero) }) {
        drawCircle(rgb(0x5EEAD4), 57.5f, Offset.Zero, 0.55f, Stroke(1f, pathEffect = DATA_DASH))
    }
    withTransform({ rotate(if (still) 0f else -360f * cycle(t, 6f), Offset.Zero) }) {
        drawPath(PACKETS, rgb(0xCCFBF1), style = Stroke(1.5f, cap = StrokeCap.Round))
    }

    for (tr in TRAILS) {
        withTransform({ rotate(tr.angle, Offset.Zero) }) {
            drawPath(tr.path, TEAL, style = Stroke(1f, join = StrokeJoin.Round))

            tr.comp?.let { c ->
                withTransform({
                    translate(c.x, c.y)
                    rotate(c.rot, Offset.Zero)
                }) {
                    drawRoundRect(c.body, Offset(-2f, -0.9f), Size(4f, 1.8f), CornerRadius(0.2f))
                    drawRect(rgb(0xCBD5E1), Offset(-2.2f, -0.9f), Size(0.9f, 1.8f))
                    drawRect(rgb(0xCBD5E1), Offset(1.3f, -0.9f), Size(0.9f, 1.8f))
                }
            }

            val p = if (still) 0f else cycle(t, tr.dur, tr.delay)
            val glowOp = if (still) 0.25f else kf(
                p,
                LinearEasing,
                0f to 0.25f,
                0.55f to 0.25f,
                0.62f to 1f,
                0.85f to 0.25f,
                1f to 0.25f,
            )
            withTransform({ translate(tr.padX, tr.padY) }) {
                drawCircle(rgb(0x042F2E), 1.7f, Offset.Zero)
                drawCircle(rgb(0x5EEAD4), 1.7f, Offset.Zero, style = Stroke(0.7f))
                drawCircle(GLOW_TERMINAL, 4f, Offset.Zero, glowOp)
            }

            if (!still) {
                val op = kf(p, CssEaseIn, 0f to 0f, 0.1f to 1f, 0.6f to 1f, 0.7f to 0f, 1f to 0f)
                if (op > 0.01f) {
                    val ty =
                        kf(p, CssEaseIn, 0f to 0f, 0.6f to -tr.run, 0.7f to -tr.run, 1f to -tr.run)
                    withTransform({
                        translate(0f, -53f)
                        translate(0f, ty)
                    }) { drawCircle(GLOW_PULSE, 2.4f, Offset.Zero, op) }
                }
            }
        }
    }

    withTransform({ translate(0f, -66f) }) {
        val pin = rgb(0x14B8A6)
        for (y in PINS) {
            line(-9.5f, y, -7f, y, 0.8f, pin)
            line(7f, y, 9.5f, y, 0.8f, pin)
        }
        line(-3f, 5f, -3f, 13f, 0.8f, pin)
        line(3f, 5f, 3f, 13f, 0.8f, pin)

        drawRoundRect(rgb(0x042F2E), Offset(-7f, -5f), Size(14f, 10f), CornerRadius(1f))
        drawRoundRect(
            rgb(0x2DD4BF),
            Offset(-7f, -5f),
            Size(14f, 10f),
            CornerRadius(1f),
            Stroke(0.7f)
        )
        drawRect(rgb(0x2DD4BF), Offset(-4.5f, -2.2f), Size(5f, 1f), 0.6f)
        drawRect(rgb(0x2DD4BF), Offset(-4.5f, 0.4f), Size(3f, 1f), 0.6f)

        val led = if (still || cycle(t, 1.4f) < 0.5f) 1f else 0.15f
        withTransform({ translate(4f, 0f) }) {
            drawCircle(GLOW_LED, 3f, Offset.Zero, led)
            drawCircle(rgb(0xCCFBF1), 0.9f, Offset.Zero)
        }
    }
}