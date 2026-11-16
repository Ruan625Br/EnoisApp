package dev.jn.enois.presentation.components.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.jn.enois.ui.theme.EnoisDesignSystem
import dev.jn.enois.ui.theme.ManropeFontFamily

/** Badge / Dot */
@Composable
fun EnoisBadgeDot(
    count: Int,
    modifier: Modifier = Modifier,
    size: Dp = 18.dp,
) {
    val colors = EnoisDesignSystem.colors
    val displayText = if (count > 99) "99+" else count.toString()
    Box(
        modifier = modifier
            .defaultMinSize(minWidth = size, minHeight = size)
            .clip(CircleShape)
            .background(colors.primary)
            .padding(horizontal = if (count > 9) 4.dp else 0.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = displayText,
            fontFamily = ManropeFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = if (size <= 14.dp) 8.sp else 10.sp,
            lineHeight = if (size <= 14.dp) 10.sp else 12.sp,
            color = colors.onPrimary,
            maxLines = 1,
        )
    }
}

/** Badge / Status */
@Composable
fun EnoisStatusBadge(
    text: String,
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(colors.primarySoft)
            .padding(horizontal = 9.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = typography.micro.copy(color = colors.primaryText),
            maxLines = 1,
        )
    }
}
