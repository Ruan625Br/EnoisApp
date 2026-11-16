package dev.jn.enois.presentation.components.ds

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.jn.enois.ui.icons.AppIcons
import dev.jn.enois.ui.icons.Lucide
import dev.jn.enois.ui.theme.EnoisDesignSystem
import dev.jn.enois.ui.theme.EnoisRadius

sealed interface ListRowTrailing {
    data object Chevron : ListRowTrailing
    data class Switch(val checked: Boolean, val onCheckedChange: (Boolean) -> Unit) : ListRowTrailing
}

sealed interface UserRowTrailing {
    data object Chevron : UserRowTrailing
    data class Action(val label: String, val onAction: () -> Unit) : UserRowTrailing
}

/** List Row / Chevron, List Row / Switch On, List Row / Switch Off */
@Composable
fun EnoisListRow(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    trailing: ListRowTrailing = ListRowTrailing.Chevron,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
            .background(colors.card)
            .clickable(
                onClick = when (trailing) {
                    is ListRowTrailing.Switch -> {
                        { trailing.onCheckedChange(!trailing.checked) }
                    }
                    ListRowTrailing.Chevron -> onClick
                },
            )
            .padding(horizontal = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(EnoisRadius.iconTileSmall))
                .background(colors.surface2Neutral),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colors.primary,
                modifier = Modifier.size(18.dp),
            )
        }

        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = title,
                style = typography.actionLabel.copy(color = colors.textStrong),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (!subtitle.isNullOrBlank()) {
                Text(
                    text = subtitle,
                    style = typography.caption.copy(
                        fontWeight = FontWeight.Normal,
                        color = colors.textSubtle,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        when (trailing) {
            ListRowTrailing.Chevron -> {
                Icon(
                    imageVector = AppIcons.Lucide.ChevronRight,
                    contentDescription = null,
                    tint = colors.textDate,
                    modifier = Modifier.size(18.dp),
                )
            }
            is ListRowTrailing.Switch -> {
                EnoisSwitch(
                    checked = trailing.checked,
                    onCheckedChange = trailing.onCheckedChange,
                )
            }
        }
    }
}

@Composable
private fun EnoisNavRowBase(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit,
    trailing: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
            .background(colors.card)
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
                .background(colors.surface2Neutral),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colors.primary,
                modifier = Modifier.size(18.dp),
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = title,
                style = typography.actionLabel.copy(color = colors.textStrong),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = subtitle,
                style = typography.caption.copy(
                    fontWeight = FontWeight.Normal,
                    color = colors.textSubtle,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        trailing()
    }
}

/** Nav Row */
@Composable
fun EnoisNavRow(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    EnoisNavRowBase(
        title = title,
        subtitle = subtitle,
        icon = icon,
        onClick = onClick,
        trailing = {
            Icon(
                imageVector = AppIcons.Lucide.ChevronRight,
                contentDescription = null,
                tint = colors.textDate,
                modifier = Modifier.size(18.dp),
            )
        },
        modifier = modifier,
    )
}

/** Nav Row / Selectable */
@Composable
fun EnoisSelectableNavRow(
    title: String,
    subtitle: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = AppIcons.Lucide.Moon,
) {
    val colors = EnoisDesignSystem.colors
    EnoisNavRowBase(
        title = title,
        subtitle = subtitle,
        icon = icon,
        onClick = onClick,
        trailing = {
            Icon(
                imageVector = if (selected) AppIcons.Lucide.CircleCheck else AppIcons.Lucide.Circle,
                contentDescription = null,
                tint = if (selected) colors.primary else colors.textDate,
                modifier = Modifier.size(20.dp),
            )
        },
        modifier = modifier,
    )
}

/** Thread Row */
@Composable
fun EnoisThreadRow(
    initials: String,
    name: String,
    time: String,
    preview: String,
    unread: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
            .background(colors.card)
            .clickable(onClick = onClick)
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        EnoisAvatar(initials = initials, imageUrl = imageUrl)

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = name,
                    style = typography.body.copy(
                        fontWeight = FontWeight.Bold,
                        color = colors.textStrong,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = time,
                    style = typography.micro.copy(
                        fontWeight = FontWeight.Normal,
                        color = colors.textDate,
                    ),
                )
            }
            Text(
                text = preview,
                style = typography.meta.copy(
                    fontWeight = FontWeight.Normal,
                    color = colors.textSubtle,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        if (unread) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(colors.primary),
            )
        }
    }
}

/** Notification Row */
@Composable
fun EnoisNotificationRow(
    initials: String,
    name: String,
    time: String,
    body: String,
    onAccept: () -> Unit,
    onReject: () -> Unit,
    modifier: Modifier = Modifier,
    acceptLabel: String = "Aceitar",
    rejectLabel: String = "Recusar",
    imageUrl: String? = null,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
            .background(colors.card)
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        EnoisAvatar(initials = initials, imageUrl = imageUrl)

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = name,
                    style = typography.actionLabel.copy(
                        fontWeight = FontWeight.Bold,
                        color = colors.textStrong,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = time,
                    style = typography.micro.copy(
                        fontWeight = FontWeight.Normal,
                        color = colors.textDate,
                    ),
                )
            }
            Text(
                text = body,
                style = typography.caption.copy(
                    fontWeight = FontWeight.Normal,
                    color = colors.textSubtle,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 82.dp, height = 34.dp)
                        .clip(RoundedCornerShape(EnoisRadius.iconTileSmall))
                        .background(colors.primary)
                        .clickable(role = Role.Button, onClick = onAccept),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = acceptLabel,
                        style = typography.labelSmall.copy(color = colors.onPrimary),
                        maxLines = 1,
                    )
                }
                Box(
                    modifier = Modifier
                        .size(width = 82.dp, height = 34.dp)
                        .clip(RoundedCornerShape(EnoisRadius.iconTileSmall))
                        .background(colors.card)
                        .border(BorderStroke(1.dp, colors.border), RoundedCornerShape(EnoisRadius.iconTileSmall))
                        .clickable(role = Role.Button, onClick = onReject),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = rejectLabel,
                        style = typography.labelSmall.copy(color = colors.textStrong),
                        maxLines = 1,
                    )
                }
            }
        }
    }
}

/** User Row / Chevron & User Row / Action */
@Composable
fun EnoisUserRow(
    initials: String,
    name: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    trailing: UserRowTrailing = UserRowTrailing.Chevron,
    onClick: () -> Unit = {},
    imageUrl: String? = null,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
            .background(colors.card)
            .clickable(
                enabled = trailing is UserRowTrailing.Chevron,
                onClick = onClick,
            )
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        EnoisAvatar(initials = initials, imageUrl = imageUrl)

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = name,
                style = typography.actionLabel.copy(color = colors.textStrong),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = subtitle,
                style = typography.caption.copy(
                    fontWeight = FontWeight.Normal,
                    color = colors.textSubtle,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        when (trailing) {
            UserRowTrailing.Chevron -> {
                Icon(
                    imageVector = AppIcons.Lucide.ChevronRight,
                    contentDescription = null,
                    tint = colors.textDate,
                    modifier = Modifier.size(18.dp),
                )
            }
            is UserRowTrailing.Action -> {
                Text(
                    text = trailing.label,
                    style = typography.labelSmall.copy(color = colors.primaryText),
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(role = Role.Button, onClick = trailing.onAction)
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                )
            }
        }
    }
}

/** User Row / Request */
@Composable
fun EnoisUserRequestRow(
    initials: String,
    name: String,
    subtitle: String,
    onAccept: () -> Unit,
    onReject: () -> Unit,
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
            .background(colors.card)
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        EnoisAvatar(initials = initials, imageUrl = imageUrl)

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = name,
                style = typography.actionLabel.copy(color = colors.textStrong),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = subtitle,
                style = typography.caption.copy(
                    fontWeight = FontWeight.Normal,
                    color = colors.textSubtle,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(colors.primary)
                    .clickable(role = Role.Button, onClick = onAccept),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = AppIcons.Lucide.Check,
                    contentDescription = "Aceitar",
                    tint = colors.onPrimary,
                    modifier = Modifier.size(16.dp),
                )
            }
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(colors.surface2Neutral)
                    .clickable(role = Role.Button, onClick = onReject),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = AppIcons.Lucide.X,
                    contentDescription = "Recusar",
                    tint = colors.textSubtle,
                    modifier = Modifier.size(16.dp),
                )
            }
        }
    }
}

/** Transaction Row */
@Composable
fun EnoisTransactionRow(
    title: String,
    date: String,
    amount: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = AppIcons.Lucide.ArrowUpRight,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
                .background(colors.surface2Neutral),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colors.primary,
                modifier = Modifier.size(18.dp),
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = title,
                style = typography.meta.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = colors.textStrong,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = date,
                style = typography.caption.copy(
                    fontWeight = FontWeight.Normal,
                    color = colors.textDate,
                ),
                maxLines = 1,
            )
        }

        Text(
            text = amount,
            style = typography.actionLabel.copy(
                fontWeight = FontWeight.Bold,
                color = colors.textStrong,
            ),
            maxLines = 1,
        )
    }
}

/** Report Row */
@Composable
fun EnoisReportRow(
    reason: String,
    status: String,
    timestamp: String,
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(EnoisRadius.card))
            .background(colors.card)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = AppIcons.Lucide.Flag,
                contentDescription = null,
                tint = colors.primary,
                modifier = Modifier.size(18.dp),
            )
            Text(
                text = reason,
                style = typography.meta.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = colors.textStrong,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            EnoisTag(text = status, highlighted = true)
            Text(
                text = timestamp,
                style = typography.micro.copy(
                    fontWeight = FontWeight.Normal,
                    color = colors.textDate,
                ),
            )
        }
    }
}

/** Select Row */
@Composable
fun EnoisSelectRow(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
            .clickable(role = Role.RadioButton, onClick = onClick)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = colors.textSubtle,
            modifier = Modifier.size(20.dp),
        )
        Text(
            text = label,
            style = typography.actionLabel.copy(color = colors.textStrong),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
        )
        Icon(
            imageVector = AppIcons.Lucide.Check,
            contentDescription = null,
            tint = if (selected) colors.primary else colors.textDate,
            modifier = Modifier.size(18.dp),
        )
    }
}

/** My Service Row */
@Composable
fun EnoisMyServiceRow(
    title: String,
    meta: String,
    price: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(EnoisRadius.card))
            .background(colors.card)
            .clickable(onClick = onClick)
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = title,
                style = typography.actionLabel.copy(
                    fontWeight = FontWeight.Bold,
                    color = colors.textStrong,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = meta,
                style = typography.caption.copy(
                    fontWeight = FontWeight.Normal,
                    color = colors.textSubtle,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        EnoisTag(text = price, highlighted = true)
    }
}
