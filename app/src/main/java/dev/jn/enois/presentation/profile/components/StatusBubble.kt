package dev.jn.enois.presentation.profile.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.jn.enois.presentation.components.avatar.fx.seasonal.SeasonalIcons

@Composable
fun StatusBubble(
    text: String,
    icon: String?,
    modifier: Modifier = Modifier,
    compact: Boolean = false,
) {
    val scheme = MaterialTheme.colorScheme
    val bottom = scheme.surfaceContainerHighest
    val fg = scheme.onSurface

    val fill = lerp(bottom, fg, 0.16f / 0.88f).copy(alpha = 0.88f)
    val line = fg.copy(alpha = 0.16f)
    val shape = MaterialTheme.shapes.extraLarge

    Row(
        modifier = modifier
            .drawBehind {
                val tails = listOf(
                    Offset(
                        -8.dp.toPx() + 5.5.dp.toPx(),
                        size.height + 6.dp.toPx() - 5.5.dp.toPx()
                    ) to 5.5.dp.toPx(),
                    Offset(
                        -16.dp.toPx() + 3.dp.toPx(),
                        size.height + 13.dp.toPx() - 3.dp.toPx()
                    ) to 3.dp.toPx(),
                )
                tails.forEach { (center, r) ->
                    drawCircle(fill, r, center)
                    drawCircle(line, r, center, style = Stroke(1.dp.toPx()))
                }
            }
            .background(fill, shape)
            .border(1.dp, line, shape)
            .padding(horizontal = 12.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (!icon.isNullOrBlank()) CosmeticIcon(icon, Modifier.size(16.dp))
        Text(
            text = text,
            color = fg,
            fontSize = if (compact) 12.sp else 13.sp,
            fontWeight = FontWeight.SemiBold,

            )
    }
}

@Composable
fun CosmeticIcon(key: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    LaunchedEffect(key) { SeasonalIcons.load(context, setOf(key)) }

    Canvas(modifier) {
        val img = SeasonalIcons.get(key) ?: return@Canvas
        drawImage(
            img,
            dstSize = IntSize(size.width.toInt(), size.height.toInt()),
            dstOffset = IntOffset.Zero,
            filterQuality = FilterQuality.High,
        )
    }
}