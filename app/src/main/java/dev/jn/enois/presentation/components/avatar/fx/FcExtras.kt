package dev.jn.enois.presentation.components.avatar.fx

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.max
import kotlin.math.sqrt

internal val FxEase = CubicBezierEasing(0.25f, 0.1f, 0.25f, 1f)

internal fun fxAlternate(t: Float, dur: Float, delay: Float = 0f): Float {
    val x = cycle(t, dur * 2f, delay)
    return if (x < 0.5f) x * 2f else 2f - 2f * x
}

internal fun fxStepOf(p: Float, s: Array<out Pair<Float, Float>>): Float {
    var v = s[0].second
    for ((o, x) in s) {
        if (p >= o) v = x else break
    }
    return v
}

internal fun fxStep(p: Float, vararg s: Pair<Float, Float>): Float = fxStepOf(p, s)

internal fun fxCirclePath(r: Float): Path = Path().apply { addOval(Rect(-r, -r, r, r)) }

internal fun fxRingPath(outer: Float, inner: Float): Path = Path().apply {
    fillType = PathFillType.EvenOdd
    addOval(Rect(-outer, -outer, outer, outer))
    addOval(Rect(-inner, -inner, inner, inner))
}


internal fun Path.circleArcTo(
    x1: Float, y1: Float, r0: Float, large: Boolean, sweep: Boolean, x2: Float, y2: Float,
) {
    val dx = (x1 - x2) / 2.0
    val dy = (y1 - y2) / 2.0
    val d2 = dx * dx + dy * dy
    if (d2 == 0.0) return
    var r = r0.toDouble()
    if (d2 > r * r) r = sqrt(d2)
    val coef = (if (large == sweep) -1.0 else 1.0) * sqrt(max(0.0, (r * r - d2) / d2))
    val cxp = coef * dy
    val cyp = -coef * dx
    val cx = cxp + (x1 + x2) / 2.0
    val cy = cyp + (y1 + y2) / 2.0
    val a1 = atan2(dy - cyp, dx - cxp)
    val a2 = atan2(-dy - cyp, -dx - cxp)
    var delta = a2 - a1
    if (!sweep && delta > 0) delta -= 2 * PI
    if (sweep && delta < 0) delta += 2 * PI
    arcTo(
        Rect((cx - r).toFloat(), (cy - r).toFloat(), (cx + r).toFloat(), (cy + r).toFloat()),
        (a1 * 180 / PI).toFloat(),
        (delta * 180 / PI).toFloat(),
        false,
    )
}