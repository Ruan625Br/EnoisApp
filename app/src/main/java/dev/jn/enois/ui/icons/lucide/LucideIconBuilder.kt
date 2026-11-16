package dev.jn.enois.ui.icons.lucide

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathBuilder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

internal inline fun lucideIcon(
    name: String,
    block: ImageVector.Builder.() -> Unit,
): ImageVector = ImageVector.Builder(
    name = name,
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f,
).apply(block).build()

internal inline fun ImageVector.Builder.lucidePath(
    block: PathBuilder.() -> Unit,
) {
    path(
        fill = null,
        stroke = SolidColor(Color.Black),
        strokeLineWidth = 2f,
        strokeLineCap = StrokeCap.Round,
        strokeLineJoin = StrokeJoin.Round,
        pathBuilder = block,
    )
}

internal fun ImageVector.Builder.lucideCircle(
    cx: Float,
    cy: Float,
    r: Float,
) {
    lucidePath {
        moveTo(cx + r, cy)
        arcToRelative(r, r, 0f, isMoreThanHalf = true, isPositiveArc = false, -2f * r, 0f)
        arcToRelative(r, r, 0f, isMoreThanHalf = true, isPositiveArc = false, 2f * r, 0f)
        close()
    }
}
