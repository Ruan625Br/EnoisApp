package dev.jn.enois.presentation.components.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.jn.enois.ui.theme.EnoisDesignSystem
import dev.jn.enois.ui.theme.EnoisRadius

/** Message Bubble / In & Message Bubble / Out */
@Composable
fun EnoisMessageBubble(
    text: String,
    isIncoming: Boolean,
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography
    val bgColor = if (isIncoming) colors.card else colors.primary
    val textColor = if (isIncoming) colors.textStrong else colors.onPrimary

    Box(
        modifier = modifier
            .widthIn(max = 250.dp)
            .clip(RoundedCornerShape(EnoisRadius.card))
            .background(bgColor)
            .padding(horizontal = 14.dp, vertical = 10.dp),
    ) {
        Text(
            text = text,
            style = typography.actionLabel.copy(
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp,
                color = textColor,
            ),
        )
    }
}
