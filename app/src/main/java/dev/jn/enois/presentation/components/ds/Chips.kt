package dev.jn.enois.presentation.components.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.jn.enois.ui.theme.EnoisDesignSystem
import dev.jn.enois.ui.theme.EnoisRadius

/** Chip Tab / On & Chip Tab / Off */
@Composable
fun EnoisChipTab(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography
    val bgColor = if (selected) colors.primary else colors.surface2Neutral
    val textColor = if (selected) colors.onPrimary else colors.textSubtle

    Box(
        modifier = modifier
            .height(32.dp)
            .clip(CircleShape)
            .background(bgColor)
            .clickable(role = Role.Tab, onClick = onClick)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = typography.meta.copy(
                fontWeight = FontWeight.SemiBold,
                color = textColor,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/** Slot Chip */
@Composable
fun EnoisSlotChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography
    val bgColor = if (selected) colors.primary else colors.surface2Neutral
    val textColor = if (selected) colors.onPrimary else colors.textStrong

    Box(
        modifier = modifier
            .height(30.dp)
            .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = typography.labelSmall.copy(color = textColor),
            maxLines = 1,
        )
    }
}
