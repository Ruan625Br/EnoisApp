package dev.jn.enois.presentation.components.avatar.fx

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.util.lerp
import dev.jn.enois.presentation.components.avatar.fx.seasonal.arcPath
import dev.jn.enois.presentation.components.avatar.fx.seasonal.rf

private val rand = SeededRandom(67)

private fun blade(l: Float, curve: Float): Path = Path().apply {
    moveTo(-1f, 0f)
    quadraticTo(rf(curve * 0.4f), rf(-l * 0.55f), curve, -l)
    quadraticTo(rf(curve * 0.4f + 0.6f), rf(-l * 0.5f), 1f, 0f)
    close()
}

private const val EARTH_R = 56.5f
private val EARTH = arcPath(EARTH_R, 116f, 244f)

private val GRASS = listOf(rgb(0x15803D), rgb(0x0F766E), rgb(0x16A34A))

private class VagaBlade(val path: Path, val rot: Float, val color: Color)
private class VagaClump(
    val x: Float, val y: Float, val rot: Float,
    val blades: List<VagaBlade>, val flower: Boolean, val delay: Float,
)

private val CLUMPS = List(9) { i ->
    val a = 124.0 + i * 14
    val o = polar(EARTH_R.toDouble(), a)
    val rot = r2(a + 180 + (rand.next() - 0.5) * 10).toFloat()
    val blades = List(6) { j ->
        val path = blade(
            (6 + rand.next() * 6).toFloat(),
            r2((rand.next() - 0.5) * 6).toFloat(),
        )
        VagaBlade(path, r2((rand.next() - 0.5) * 60).toFloat(), GRASS[j % GRASS.size])
    }
    VagaClump(o.x, o.y, rot, blades, i % 2 == 1, r2(-rand.next() * 3.2).toFloat())
}

private class VagaStar(val x: Float, val y: Float, val dur: Float, val delay: Float)

private val STARS = listOf(292.0, 312.0, 334.0, 356.0, 18.0, 66.0, 84.0).map { a ->
    val o = polar(52 + rand.next() * 4, a)
    VagaStar(o.x, o.y, r2(1.8 + rand.next() * 1.6).toFloat(), r2(-rand.next() * 3).toFloat())
}

private class VagaFly(
    val radius: Float, val lap: Float, val dir: Int, val start: Float,
    var dx: Float = 0f, var dy: Float = 0f, var flight: Float = 0f, var blink: Float = 0f,
)

private val FLIES = listOf(
    VagaFly(62f, 15f, 1, 0.1f),
    VagaFly(57f, 19f, -1, 0.45f),
    VagaFly(66f, 23f, 1, 0.75f),
).onEach {
    it.dx = r2((rand.next() - 0.5) * 10).toFloat()
    it.dy = r2((rand.next() - 0.5) * 10).toFloat()
    it.flight = r2(2.6 + rand.next() * 1.4).toFloat()
    it.blink = r2(1.6 + rand.next() * 0.8).toFloat()
}

private val TRAIL = listOf(0.22f, 0.44f, 0.66f)

private class VagaFar(val x: Float, val y: Float, val dur: Float, val delay: Float)

private val FAR = List(7) { i ->
    val o = polar(54 + rand.next() * 16, i * 51 + rand.next() * 30)
    VagaFar(o.x, o.y, r2(2.6 + rand.next() * 2).toFloat(), r2(-rand.next() * 4.6).toFloat())
}

private fun fireGlow(r: Float) = Brush.radialGradient(
    0f to rgb(0xFEFCE8), 0.2f to rgb(0xD9F99D, 0.95f), 1f to rgb(0xA3E635, 0f),
    center = Offset.Zero, radius = r,
)

private val GLOW_FAR = fireGlow(4.5f)
private val GLOW_TRAIL = listOf(fireGlow(2.6f), fireGlow(2.0f), fireGlow(1.4f))
private val GLOW_MAIN = fireGlow(8f)

private val SKY = Brush.linearGradient(
    0f to rgb(0x1E1B4B), 0.6f to rgb(0x1E3A5F), 1f to rgb(0x134E4A),
    start = Offset(0f, -54f), end = Offset(0f, 54f),
)
private val MOON_HALO = Brush.radialGradient(
    0.3f to rgb(0xFEF9C3, 0.5f), 1f to rgb(0xFEF9C3, 0f), center = Offset.Zero, radius = 11f,
)

private val MOON = Path().apply {
    moveTo(-1f, -4.6f)
    circleArcTo(-1f, -4.6f, 4.6f, true, false, 3.6f, 3f)
    circleArcTo(3.6f, 3f, 3.6f, true, true, -1f, -4.6f)
    close()
}

private val STAR_SHAPE = Path().apply {
    moveTo(0f, -2.2f); lineTo(0.5f, -0.5f); lineTo(2.2f, 0f); lineTo(0.5f, 0.5f)
    lineTo(0f, 2.2f); lineTo(-0.5f, 0.5f); lineTo(-2.2f, 0f); lineTo(-0.5f, -0.5f); close()
}

private val BELL_STEM = Path().apply { moveTo(0f, 0f); quadraticTo(2f, -7f, 0f, -12f) }
private val BELL = Path().apply {
    moveTo(0f, -12f)
    cubicTo(-2.6f, -11.6f, -3f, -8.6f, -2.4f, -7.4f)
    lineTo(-1f, -8.2f); lineTo(0f, -7.2f); lineTo(1f, -8.2f); lineTo(2.4f, -7.4f)
    cubicTo(3f, -8.6f, 2.6f, -11.6f, 0f, -12f)
    close()
}

internal fun DrawScope.drawVagaLumes(t: Float, still: Boolean = false) {
    drawCircle(SKY, 54f, Offset.Zero, style = Stroke(9f))
    ring(rgb(0xA5B4FC), 58.5f, 0.6f, 0.35f)
    ring(rgb(0xA5B4FC), 49.5f, 0.6f, 0.45f)

    val moon = polar(54.0, 40.0)
    withTransform({ translate(moon.x, moon.y) }) {
        drawCircle(MOON_HALO, 11f, Offset.Zero)
        drawPath(MOON, rgb(0xFEF9C3))
    }

    for (s in STARS) {
        var sc = 1f
        var op = 1f
        if (!still) {
            val p = cycle(t, s.dur, s.delay)
            sc = kf(p, CssEaseInOut, 0f to 0.4f, 0.5f to 1f, 1f to 0.4f)
            op = kf(p, CssEaseInOut, 0f to 0.3f, 0.5f to 1f, 1f to 0.3f)
        }
        withTransform({
            translate(s.x, s.y)
            scale(sc, sc, Offset.Zero)
        }) { drawPath(STAR_SHAPE, rgb(0xE0E7FF), op) }
    }

    if (!still) {
        for (f in FAR) {
            val op = kf(
                cycle(t, f.dur, f.delay), CssEaseInOut,
                0f to 0.1f, 0.45f to 0.15f, 0.6f to 0.85f, 0.78f to 0.15f, 1f to 0.1f,
            )
            withTransform({ translate(f.x, f.y) }) { drawCircle(GLOW_FAR, 4.5f, Offset.Zero, op) }
        }
    }

    drawPath(EARTH, rgb(0x14532D), style = Stroke(4f, cap = StrokeCap.Round))
    for (m in CLUMPS) {
        val sway =
            if (still) 0f else lerp(-5f, 5f, CssEaseInOut.transform(fxAlternate(t, 3.2f, m.delay)))
        withTransform({
            translate(m.x, m.y)
            rotate(m.rot, Offset.Zero)
            rotate(sway, Offset.Zero)
        }) {
            for (b in m.blades) {
                withTransform({ rotate(b.rot, Offset.Zero) }) { drawPath(b.path, b.color) }
            }
            if (m.flower) {
                withTransform({ scale(1.05f, 1.05f, Offset.Zero) }) {
                    drawPath(BELL_STEM, rgb(0x166534), style = Stroke(0.8f))
                    drawPath(BELL, rgb(0xC4B5FD))
                }
            }
        }
    }

    for (v in FLIES) {
        val dirRot = v.dir * 360f
        withTransform({ rotate(r2(v.start * 360.0).toFloat(), Offset.Zero) }) {
            if (!still) {
                for ((j, lag) in TRAIL.withIndex()) {
                    val orbit = dirRot * cycle(t, v.lap, r2((lag - v.lap).toDouble()).toFloat())
                    val fly = flightOffset(t, v, lag)
                    withTransform({
                        rotate(orbit, Offset.Zero)
                        translate(0f, -v.radius)
                        translate(fly.x, fly.y)
                    }) {
                        drawCircle(
                            GLOW_TRAIL[j], r2(2.6 - j * 0.6).toFloat(), Offset.Zero,
                            r2(0.6 - j * 0.17).toFloat(),
                        )
                    }
                }
            }

            val orbit =
                if (still) 0f else dirRot * cycle(t, v.lap, r2((-v.lap).toDouble()).toFloat())
            val fly = if (still) Offset.Zero else flightOffset(t, v, 0f)
            val blink = if (still) 1f else kf(
                cycle(t, v.blink), CssEaseInOut, 0f to 0.5f, 0.5f to 1f, 1f to 0.5f,
            )
            val wing = if (still) 1f else lerp(1f, 0.25f, fxAlternate(t, 0.12f))
            withTransform({
                rotate(orbit, Offset.Zero)
                translate(0f, -v.radius)
                scale(1.4f * v.dir, 1.4f, Offset.Zero)
                translate(fly.x, fly.y)
            }) {
                withTransform({ translate(-2f, 0f) }) {
                    drawCircle(
                        GLOW_MAIN,
                        8f,
                        Offset.Zero,
                        blink
                    )
                }
                withTransform({ scale(1f, wing, Offset.Zero) }) {
                    drawOval(
                        rgb(0xE0F2FE),
                        Offset(0.2f - 2.4f, -1.9f - 1.1f),
                        Size(4.8f, 2.2f),
                        0.6f
                    )
                }
                drawOval(rgb(0x1C1917), Offset(1f - 2.2f, -1.1f), Size(4.4f, 2.2f))
                drawCircle(rgb(0x292524), 0.85f, Offset(3.1f, 0f))
                drawCircle(rgb(0xFEFCE8), 1.3f, Offset(-1.6f, 0f))
            }
        }
    }
}

private fun flightOffset(t: Float, v: VagaFly, lag: Float): Offset {
    val p = cycle(t, v.flight, r2((lag - v.flight).toDouble()).toFloat())
    val x = kf(p, CssEaseInOut, 0f to 0f, 0.33f to v.dx, 0.66f to -v.dy, 1f to 0f)
    val y = kf(p, CssEaseInOut, 0f to 0f, 0.33f to v.dy, 0.66f to v.dx, 1f to 0f)
    return Offset(x, y)
}