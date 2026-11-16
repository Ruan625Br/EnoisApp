package dev.jn.enois.presentation.components.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.jn.enois.ui.icons.AppIcons
import dev.jn.enois.ui.icons.Lucide
import dev.jn.enois.ui.theme.EnoisDesignSystem
import dev.jn.enois.ui.theme.EnoisRadius

enum class EnoisFeedSegment(val label: String) {
    ForYou("Para você"),
    Following("Seguindo"),
}

/** App Header */
@Composable
fun EnoisAppHeader(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    rightIcon: ImageVector? = null,
    onRightClick: (() -> Unit)? = null,
    backContentDescription: String = "Voltar",
    rightContentDescription: String? = null,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(colors.bg)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .then(
                    if (onBackClick != null) {
                        Modifier.clickable(role = Role.Button, onClick = onBackClick)
                    } else {
                        Modifier
                    }
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (onBackClick != null) {
                Icon(
                    imageVector = AppIcons.Lucide.ChevronLeft,
                    contentDescription = backContentDescription,
                    tint = colors.textStrong,
                    modifier = Modifier.size(24.dp),
                )
            }
        }

        Text(
            text = title,
            style = typography.headerTitle.copy(color = colors.textStrong),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
        )

        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .then(
                    if (rightIcon != null && onRightClick != null) {
                        Modifier.clickable(role = Role.Button, onClick = onRightClick)
                    } else {
                        Modifier
                    }
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (rightIcon != null) {
                Icon(
                    imageVector = rightIcon,
                    contentDescription = rightContentDescription,
                    tint = colors.textStrong,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}

/** Feed Header */
@Composable
fun EnoisFeedHeader(
    unreadNotifications: Int,
    selectedSegment: EnoisFeedSegment,
    onSelectSegment: (EnoisFeedSegment) -> Unit,
    feedTypeLabel: String,
    onFeedTypeClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    modifier: Modifier = Modifier,
    showFilterButton: Boolean = false,
    onFilterClick: () -> Unit = {},
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.bg),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        // Top brand + notifications
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 8.dp, end = 20.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Énóis",
                style = typography.brandLogo.copy(color = colors.primary),
            )
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .clickable(role = Role.Button, onClick = onNotificationsClick),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = AppIcons.Lucide.Bell,
                    contentDescription = "Notificações",
                    tint = colors.textStrong,
                    modifier = Modifier.size(24.dp),
                )
                if (unreadNotifications > 0) {
                    EnoisBadgeDot(
                        count = unreadNotifications,
                        size = 14.dp,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = (-4).dp, y = 4.dp),
                    )
                }
            }
        }

        // Segmented Control + optional Filter button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 0.dp, end = 20.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
                    .background(colors.surface1)
                    .padding(4.dp),
            ) {
                EnoisFeedSegment.entries.forEach { segment ->
                    val isSelected = segment == selectedSegment
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(32.dp)
                            .clip(RoundedCornerShape(9.dp))
                            .background(if (isSelected) colors.card else Color.Transparent)
                            .clickable(role = Role.Tab, onClick = { onSelectSegment(segment) }),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = segment.label,
                            style = typography.meta.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) colors.textStrong else colors.textSubtle,
                            ),
                            maxLines = 1,
                        )
                    }
                }
            }

            if (showFilterButton) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
                        .background(colors.surface1)
                        .clickable(role = Role.Button, onClick = onFilterClick),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = AppIcons.Lucide.ListFilter,
                        contentDescription = "Filtrar",
                        tint = colors.textStrong,
                        modifier = Modifier.size(18.dp),
                    )
                }
            }
        }

        // Feed Type Pill
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            Row(
                modifier = Modifier
                    .height(32.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(colors.surface2Neutral)
                    .clickable(onClick = onFeedTypeClick)
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = AppIcons.Lucide.Funnel,
                    contentDescription = null,
                    tint = colors.textStrong,
                    modifier = Modifier.size(14.dp),
                )
                Text(
                    text = feedTypeLabel,
                    style = typography.labelSmall.copy(color = colors.textStrong),
                    maxLines = 1,
                )
                Icon(
                    imageVector = AppIcons.Lucide.ChevronDown,
                    contentDescription = null,
                    tint = colors.textSubtle,
                    modifier = Modifier.size(14.dp),
                )
            }
        }
    }
}
