package dev.jn.enois.presentation.components.avatar.fx

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import dev.jn.enois.presentation.components.avatar.fx.seasonal.rf
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sin

private const val CICLO = 9f

private fun vineRadius(a: Double): Double = 52.0 + 2.2 * sin(a * 7 * PI / 180)

private fun stem(from: Double, to: Double): Path = Path().apply {
    val steps = ceil(abs(to - from) / 3).toInt()
    for (i in 0..steps) {
        val a = from + (to - from) * i / steps
        val o = polar(vineRadius(a), a)
        if (i == 0) moveTo(o.x, o.y) else lineTo(o.x, o.y)
    }
}

private fun leafPath(l: Float): Path {
    val w = rf(l * 0.42f)
    return Path().apply {
        moveTo(0f, 0f)
        cubicTo(w, rf(-l * 0.3f), w, rf(-l * 0.75f), 0f, -l)
        cubicTo(-w, rf(-l * 0.75f), -w, rf(-l * 0.3f), 0f, 0f)
        close()
    }
}

private fun delayOf(phase: Double): Float = r2(phase * CICLO - CICLO).toFloat()

private class TrepLeaf(
    val x: Float, val y: Float, val rot: Float,
    val path: Path, val vein: Float, val delay: Float, val fill: Brush,
)

private val VINES = listOf(Triple(180.0, 352.0, 1), Triple(180.0, 8.0, -1))
private val STEMS = VINES.map { stem(it.first, it.second) }

private val LEAVES: List<TrepLeaf> = VINES.flatMap { (from, _, dir) ->
    List(10) { i ->
        val a = from + dir * (12 + i * 16)
        val o = polar(vineRadius(a), a)
        val out = i % 2 == 0
        val len = if (out) 10f + (i % 3) else 6.5f
        val path = leafPath(len)
        val b = path.getBounds()
        TrepLeaf(
            o.x, o.y,
            r2(a + dir * (if (out) 40 else 140)).toFloat(),
            path, rf(-len * 0.85f), delayOf(i * 0.034),
            vGrad(b.bottom, b.top, 0f to rgb(0x15803D), 1f to rgb(0x86EFAC)),
        )
    }
}

private val TENDRILS = listOf(140.0, 220.0, 300.0, 60.0).map { a ->
    val o = polar(vineRadius(a) + 1, a)
    Triple(o, a.toFloat(), Path().apply {
        moveTo(0f, 0f)
        cubicTo(2f, -3f, 6f, -3f, 6f, 0f)
        cubicTo(6f, 2.4f, 3f, 2.6f, 2.6f, 0.8f)
    })
}

private val PETALS = listOf(0f, 72f, 144f, 216f, 288f)


private val PETAL_FILL = Brush.radialGradient(
    0f to rgb(0xBE185D), 1f to rgb(0xF9A8D4),
    center = Offset(0f, 0.4f), radius = 9.6f,
)
private const val PETAL_SX = 6.6f / 9.6f

internal fun DrawScope.drawTrepadeira(t: Float, still: Boolean = false) {
    ring(rgb(0x14532D), 49.5f, 1.4f, 0.8f)

    for (s in STEMS) {
        drawPath(s, rgb(0x166534), style = Stroke(2.6f, cap = StrokeCap.Round, join = StrokeJoin.Round))
        drawPath(s, rgb(0x4ADE80), 0.55f, Stroke(0.8f, cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
    for ((o, angle, path) in TENDRILS) {
        withTransform({
            translate(o.x, o.y)
            rotate(angle, Offset.Zero)
        }) {
            drawPath(path, rgb(0x22C55E), style = Stroke(0.8f, cap = StrokeCap.Round, join = StrokeJoin.Round))
        }
    }

    for (leaf in LEAVES) {
        var sc = 1f
        var rot = 0f
        var op = 1f
        if (!still) {
            val p = cycle(t, CICLO, leaf.delay)
            sc = kf(p, CssEaseOut, 0f to 0f, 0.08f to 1.12f, 0.12f to 1f, 0.62f to 1f, 0.72f to 0.8f, 1f to 0f)
            rot = kf(p, CssEaseOut, 0f to 0f, 0.62f to 0f, 0.72f to 20f, 1f to 0f)
            op = kf(p, CssEaseOut, 0f to 0f, 0.08f to 1f, 0.12f to 1f, 0.62f to 1f, 0.72f to 0f, 1f to 0f)
            if (op <= 0.01f) continue
        }
        withTransform({
            translate(leaf.x, leaf.y)
            rotate(leaf.rot, Offset.Zero)
            scale(sc, sc, Offset.Zero)
            rotate(rot, Offset.Zero)
        }) {
            drawPath(leaf.path, leaf.fill, op)
            drawLine(rgb(0x14532D), Offset.Zero, Offset(0f, leaf.vein), 0.5f, alpha = op)
        }
    }

    var fsc = 1f
    var frot = 0f
    var fop = 1f
    if (!still) {
        val p = cycle(t, CICLO, delayOf(0.38))
        fsc = kf(p, CssEaseOut, 0f to 0f, 0.1f to 1.15f, 0.14f to 1f, 0.52f to 1f, 0.62f to 0.6f, 1f to 0f)
        frot = kf(p, CssEaseOut, 0f to -60f, 0.1f to 0f, 0.52f to 0f, 0.62f to 30f, 1f to 0f)
        fop = kf(p, CssEaseOut, 0f to 0f, 0.1f to 1f, 0.14f to 1f, 0.52f to 1f, 0.62f to 0f, 1f to 0f)
    }
    if (fop > 0.01f) {
        val top = polar(vineRadius(0.0), 0.0)
        withTransform({
            translate(top.x, top.y)
            scale(fsc, fsc, Offset.Zero)
            rotate(frot, Offset.Zero)
        }) {
            for (deg in PETALS) {
                withTransform({
                    rotate(deg, Offset.Zero)
                    scale(PETAL_SX, 1f, Offset.Zero)
                }) { drawCircle(PETAL_FILL, 4.8f, Offset(0f, -4.4f), fop) }
            }
            drawCircle(rgb(0xFACC15), 2.4f, Offset.Zero, fop)
        }
    }
}