package dev.jn.enois.presentation.components.avatar.fx

import androidx.compose.animation.core.LinearEasing
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import dev.jn.enois.presentation.components.avatar.fx.seasonal.rf
import kotlin.math.floor
import kotlin.math.roundToInt

private const val BLOCK = 6.0


private val CELLS: List<Offset> = run {
    val seen = LinkedHashMap<String, Offset>()
    for (a in 0 until 360) {
        val p = polar(57.0, a.toDouble())
        val x = floor(p.x / BLOCK + 0.5) * BLOCK
        val y = floor(p.y / BLOCK + 0.5) * BLOCK
        seen["$x,$y"] = Offset(x.toFloat(), y.toFloat())
    }
    seen.values.toList()
}

private const val LAP = 5.875f
private const val PAUSE = 1.5f
private const val CYCLE = LAP * 4 + PAUSE
private val STEP = LAP / CELLS.size

private val APPLES = listOf(0.304f, 0.709f, 0.52f, 0.128f).map { CELLS[floor(it * CELLS.size).toInt()] }

private fun squares(points: List<Offset>, side: Float): Path = Path().apply {
    val half = side / 2
    for (p in points) {
        moveTo(rf(p.x - half), rf(p.y - half))
        relativeLineTo(side, 0f)
        relativeLineTo(0f, side)
        relativeLineTo(-side, 0f)
        close()
    }
}

private class FlipPix(val color: Color, val path: Path)

private fun pixels(art: List<String>, colors: List<Pair<Char, Color>>, unit: Float): List<FlipPix> {
    val halfW = art[0].length * unit / 2
    val halfH = art.size * unit / 2
    return colors.map { (ch, color) ->
        val pts = ArrayList<Offset>()
        art.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if (c == ch) pts.add(Offset(x * unit - halfW + unit / 2, y * unit - halfH + unit / 2))
            }
        }
        FlipPix(color, squares(pts, unit))
    }
}

private val RING = squares(CELLS, 5.4f)
private val HOLE = CELLS[0]

private val APPLE = pixels(
    listOf("..G..", ".RRR.", "RWRRR", "RRRRR", ".RRR."),
    listOf('R' to rgb(0xEF4444), 'W' to rgb(0xFECACA), 'G' to rgb(0x22C55E)), 1.3f,
)
private val COIN = pixels(
    listOf("..YYY..", ".YWYYY.", "YWYYYOY", "YWYYYOY", "YWYYYOY", ".YYYOY.", "..OOO.."),
    listOf('Y' to rgb(0xFACC15), 'W' to rgb(0xFEF9C3), 'O' to rgb(0xCA8A04)), 1.7f,
)
private val PLUS_ONE = pixels(
    listOf(".W...W.", "WWW.WW.", ".W...W.", ".....W.", "....WWW"),
    listOf('W' to rgb(0xFEF08A)), 1.1f,
)
private val HEART = pixels(
    listOf(".RR.RR.", "RWRRRRR", "RRRRRRR", ".RRRRR.", "..RRR..", "...R..."),
    listOf('R' to rgb(0xF43F5E), 'W' to rgb(0xFFE4E6)), 1.6f,
)
private val STAR = pixels(listOf(".W.", "WWW", ".W."), listOf('W' to rgb(0xE0E7FF)), 1.5f)

private val STARS = listOf(62.0, 118.0, 242.0, 298.0).mapIndexed { i, a ->
    Pair(polar(70.0, a), r2(-i * 0.55).toFloat())
}

private val CELL_DELAY = FloatArray(CELLS.size) { ((it * STEP - CYCLE) * 1000f).roundToInt() / 1000f }

private val SNAKE: Array<Pair<Float, Float>> = arrayOf(
    0f to 0f, 0.06f to 1f, 0.06318f to 0.65f, 0.07645f to 0f,
    0.295f to 1f, 0.29818f to 0.65f, 0.3185f to 0f,
    0.53f to 1f, 0.53318f to 0.65f, 0.56055f to 0f,
    0.765f to 1f, 0.76818f to 0.65f, 0.8026f to 0f, 1f to 0f,
)

private val COIN_SCALE: Array<Pair<Float, Float>> = arrayOf(
    0f to 1f,
    0.13144f to 0.55f, 0.13644f to 0.15f, 0.14144f to 0.55f, 0.14644f to 1f,
    0.46162f to 0.55f, 0.46662f to 0.15f, 0.47162f to 0.55f, 0.47662f to 1f,
    0.6522f to 0.55f, 0.6572f to 0.15f, 0.6622f to 0.55f, 0.6672f to 1f,
    0.79508f to 0.55f, 0.80008f to 0.15f, 0.80508f to 0.55f, 0.81008f to 1f,
)

private val STAR_BLINK: Array<Pair<Float, Float>> = arrayOf(0f to 1f, 0.5f to 0.2f, 0.75f to 0.6f)

private fun DrawScope.drawPix(layers: List<FlipPix>, alpha: Float = 1f) {
    for (l in layers) drawPath(l.path, l.color, alpha)
}

internal fun DrawScope.drawFliperama(t: Float, still: Boolean = false) {
    drawPath(RING, rgb(0x312E81))
    drawRect(rgb(0x0B0A1F), Offset(HOLE.x - 2.7f, HOLE.y - 2.7f), Size(5.4f, 5.4f))
    drawRect(rgb(0x818CF8), Offset(HOLE.x - 2.7f, HOLE.y - 2.7f), Size(5.4f, 5.4f), style = Stroke(0.6f))

    if (!still) {
        for (i in CELLS.indices) {
            val op = fxStepOf(cycle(t, CYCLE, CELL_DELAY[i]), SNAKE)
            if (op <= 0.01f) continue
            drawRect(rgb(0x4ADE80), Offset(CELLS[i].x - 2.7f, CELLS[i].y - 2.7f), Size(5.4f, 5.4f), op)
        }
    }

    val p = cycle(t, CYCLE)
    var idx = 0
    var appleOp = 1f
    if (!still) {
        when {
            p >= 0.879f -> { idx = 0; appleOp = 1f }
            p >= 0.79508f -> { idx = 0; appleOp = 0f }
            p >= 0.6522f -> { idx = 3; appleOp = 1f }
            p >= 0.46162f -> { idx = 2; appleOp = 1f }
            p >= 0.2481f -> { idx = 1; appleOp = 1f }
            p >= 0.13144f -> { idx = 1; appleOp = 0f }
        }
    }
    if (appleOp > 0f) {
        withTransform({ translate(APPLES[idx].x, APPLES[idx].y) }) { drawPix(APPLE) }
    }

    val coin = polar(68.0, 0.0)
    val coinX = if (still) 1f else fxStepOf(p, COIN_SCALE)
    withTransform({
        translate(coin.x, coin.y)
        scale(coinX, 1f, Offset.Zero)
    }) { drawPix(COIN) }

    if (!still) {
        val op = kf(
            p, LinearEasing,
            0f to 0f, 0.13134f to 0f, 0.13144f to 1f, 0.15644f to 0f, 0.15654f to 0f,
            0.46152f to 0f, 0.46162f to 1f, 0.48662f to 0f, 0.48672f to 0f,
            0.6521f to 0f, 0.6522f to 1f, 0.6772f to 0f, 0.6773f to 0f,
            0.79498f to 0f, 0.79508f to 1f, 0.82008f to 0f, 0.82018f to 0f, 1f to 0f,
        )
        if (op > 0.01f) {
            val rise = kf(
                p, LinearEasing,
                0f to 0f, 0.13144f to 0f, 0.15644f to -7f, 0.15654f to 0f,
                0.46162f to 0f, 0.48662f to -7f, 0.48672f to 0f,
                0.6522f to 0f, 0.6772f to -7f, 0.6773f to 0f,
                0.79508f to 0f, 0.82008f to -7f, 0.82018f to 0f, 1f to 0f,
            )
            withTransform({
                translate(coin.x, coin.y)
                translate(9f, -2f + rise)
            }) { drawPix(PLUS_ONE, op) }
        }
    }

    val heart = polar(68.0, 180.0)
    val heartScale = if (!still && cycle(t, 1f) >= 0.5f) 1.25f else 1f
    withTransform({
        translate(heart.x, heart.y)
        scale(heartScale, heartScale, Offset.Zero)
    }) { drawPix(HEART) }

    for ((pos, delay) in STARS) {
        val op = if (still) 1f else fxStepOf(cycle(t, 2.2f, delay), STAR_BLINK)
        withTransform({ translate(pos.x, pos.y) }) { drawPix(STAR, op) }
    }
}