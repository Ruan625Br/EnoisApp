package dev.jn.enois.presentation.components.avatar.fx

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import kotlin.math.PI
import kotlin.math.sin

private val rand = SeededRandom(59)

private val EaseGust = CubicBezierEasing(0.4f, 0f, 0.6f, 1f)


private fun streak(radius: Double, drop: Double, arc: Double, thick: Double): Path {
    val steps = 24
    val outer = ArrayList<Offset>()
    val inner = ArrayList<Offset>()
    for (i in 0..steps) {
        val k = i.toDouble() / steps
        val r = radius - drop * k
        val half = thick * sin(PI * k) / 2 + 0.1
        outer.add(polar(r + half, arc * k))
        inner.add(0, polar(r - half, arc * k))
    }
    return Path().apply {
        moveTo(outer[0].x, outer[0].y)
        for (i in 1 until outer.size) lineTo(outer[i].x, outer[i].y)
        for (p in inner) lineTo(p.x, p.y)
        close()
    }
}

private fun gust(length: Double, thick: Double, wave: Double): Path {
    val steps = 24
    val top = ArrayList<Offset>()
    val bottom = ArrayList<Offset>()
    for (i in 0..steps) {
        val k = i.toDouble() / steps
        val x = r2(-length / 2 + length * k).toFloat()
        val y = wave * sin(k * 2 * PI)
        val half = thick * sin(PI * k) / 2 + 0.1
        top.add(Offset(x, r2(y - half).toFloat()))
        bottom.add(0, Offset(x, r2(y + half).toFloat()))
    }
    return Path().apply {
        moveTo(top[0].x, top[0].y)
        for (i in 1 until top.size) lineTo(top[i].x, top[i].y)
        for (p in bottom) lineTo(p.x, p.y)
        close()
    }
}

private class VendStreak(
    val start: Float, val path: Path, val color: Color,
    val spin: Float, val breath: Float, val delay: Float,
)

private val STREAKS = List(9) { i ->
    val start = r2(i * 40 + rand.next() * 20).toFloat()
    val path = streak(
        57 + rand.next() * 11,
        5 + rand.next() * 5,
        50 + rand.next() * 60,
        1.4 + rand.next() * 1.6
    )
    VendStreak(
        start, path,
        if (i % 2 == 0) rgb(0xF0F9FF) else rgb(0xBAE6FD),
        r2(1.8 + rand.next() * 1.4).toFloat(),
        r2(1.4 + rand.next() * 0.8).toFloat(),
        r2(-rand.next() * 2.2).toFloat(),
    )
}

private class VendLeaf(val color: Color, val radius: Float, val delay: Float)

private val LEAVES = listOf(0xF59E0B, 0x84CC16, 0xEA580C).mapIndexed { i, c ->
    VendLeaf(rgb(c), r2(60 + rand.next() * 6).toFloat(), r2(-i * 0.9 - rand.next() * 0.4).toFloat())
}

private class VendGust(val y: Float, val path: Path, val delay: Float)

private val GUSTS = listOf(
    VendGust(-20f, gust(58.0, 2.2, 2.0), 0f),
    VendGust(-6f, gust(72.0, 2.8, -2.5), 0.08f),
    VendGust(8f, gust(64.0, 2.4, 2.2), 0.16f),
    VendGust(21f, gust(50.0, 1.8, -1.8), 0.26f),
)

private val LEAF_SHAPE = Path().apply {
    moveTo(0f, -3.6f)
    cubicTo(2.4f, -2.4f, 2.4f, 2f, 0f, 3.6f)
    cubicTo(-2.4f, 2f, -2.4f, -2.4f, 0f, -3.6f)
    close()
}

private val DUST = List(8) {
    val o = polar(58 + rand.next() * 12, rand.next() * 360)
    Triple(o.x, o.y, r2(0.5 + rand.next() * 0.5).toFloat())
}

private val AIR = Brush.radialGradient(
    0.6f to rgb(0x7DD3FC, 0f), 0.68f to rgb(0x7DD3FC, 0.3f), 0.9f to rgb(0x7DD3FC, 0f),
    center = Offset.Zero, radius = 80f,
)

internal fun DrawScope.drawVendaval(t: Float, still: Boolean = false) {
    drawCircle(AIR, 80f, Offset.Zero)
    ring(rgb(0xE0F2FE), 50f, 1.8f, 0.8f)

    for (s in STREAKS) {
        val op = if (still) 1f else kf(
            cycle(t, s.breath, s.delay),
            CssEaseInOut,
            0f to 0f,
            0.3f to 0.9f,
            0.6f to 0.6f,
            1f to 0f,
        )
        if (op <= 0.01f) continue
        val spin = if (still) 0f else 360f * cycle(t, s.spin, s.delay)
        withTransform({
            rotate(spin, Offset.Zero)
            rotate(s.start, Offset.Zero)
        }) { drawPath(s.path, s.color, op) }
    }

    if (!still) {
        withTransform({ rotate(360f * cycle(t, 1.4f), Offset.Zero) }) {
            for ((x, y, r) in DUST) drawCircle(rgb(0xE0F2FE), r, Offset(x, y))
        }

        for (f in LEAVES) {
            val fly = 360f * cycle(t, 2.7f, f.delay)
            val spin = 360f * cycle(t, 0.9f, f.delay)
            val op = kf(
                cycle(t, 2.7f, f.delay),
                CssEaseInOut,
                0f to 0f,
                0.15f to 1f,
                0.65f to 1f,
                0.8f to 0f,
                1f to 0f
            )
            if (op <= 0.01f) continue
            withTransform({
                rotate(fly, Offset.Zero)
                translate(0f, -f.radius)
                rotate(spin, Offset.Zero)
            }) { drawPath(LEAF_SHAPE, f.color, op) }
        }
    }

    if (!still) {
        for (g in GUSTS) {
            val p = cycle(t, 6f, g.delay)
            val op = kf(p, EaseGust, 0f to 0f, 0.04f to 0.8f, 0.14f to 0.7f, 0.19f to 0f, 1f to 0f)
            if (op <= 0.01f) continue
            val x = kf(p, EaseGust, 0f to -75f, 0.19f to 75f, 1f to 75f)
            withTransform({
                translate(0f, g.y)
                translate(x, 0f)
            }) { drawPath(g.path, rgb(0xF0F9FF), op) }
        }

        val p = cycle(t, 6f, 0.12f)
        val op = kf(p, CssEaseInOut, 0f to 0f, 0.05f to 1f, 0.16f to 1f, 0.2f to 0f, 1f to 0f)
        if (op > 0.01f) {
            val x = kf(p, CssEaseInOut, 0f to -70f, 0.1f to 0f, 0.2f to 72f, 1f to 72f)
            val y = kf(p, CssEaseInOut, 0f to 6f, 0.1f to -6f, 0.2f to 4f, 1f to 4f)
            withTransform({
                translate(0f, -12f)
                translate(x, y)
                rotate(360f * cycle(t, 0.6f), Offset.Zero)
            }) { drawPath(LEAF_SHAPE, rgb(0x84CC16), op) }
        }
    }
}


internal fun vendavalSwayX(t: Float): Float = 0.01f * kf(
    cycle(t, 6f), CssEaseOut,
    0f to 0f, 0.07f to 0f, 0.12f to 1.2f, 0.17f to -0.4f, 0.22f to 0f, 1f to 0f,
)

internal fun vendavalSwayRot(t: Float): Float = kf(
    cycle(t, 6f), CssEaseOut,
    0f to 0f, 0.07f to 0f, 0.12f to 7f, 0.17f to -3f, 0.22f to 1.2f, 0.27f to 0f, 1f to 0f,
)