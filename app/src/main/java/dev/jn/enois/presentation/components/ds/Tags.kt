package dev.jn.enois.presentation.components.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.jn.enois.ui.theme.EnoisDesignSystem

/** Tag */
@Composable
fun EnoisTag(
    text: String,
    modifier: Modifier = Modifier,
    highlighted: Boolean = false,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography
    val bgColor = if (highlighted) colors.primarySoft else colors.surface2Neutral
    val textColor = if (highlighted) colors.primaryText else colors.textSubtle
    val fontWeight = if (highlighted) FontWeight.Bold else FontWeight.SemiBold

    Box(
        modifier = modifier
            .height(24.dp)
            .clip(CircleShape)
            .background(bgColor)
            .padding(horizontal = 9.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = typography.caption.copy(
                fontWeight = fontWeight,
                color = textColor,
            ),
            maxLines = 1,
        )
    }
}
