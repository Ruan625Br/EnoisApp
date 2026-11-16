package dev.jn.enois.presentation.components.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.jn.enois.ui.icons.AppIcons
import dev.jn.enois.ui.icons.Lucide
import dev.jn.enois.ui.theme.EnoisDesignSystem
import dev.jn.enois.ui.theme.EnoisRadius
import dev.jn.enois.ui.theme.ManropeFontFamily

/** Status Card / Plain & Status Card / Badge */
@Composable
fun EnoisStatusCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    badgeText: String? = null,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(EnoisRadius.card))
            .background(colors.card)
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(colors.primarySoft),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colors.primary,
                modifier = Modifier.size(20.dp),
            )
        }

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
                text = subtitle,
                style = typography.caption.copy(
                    fontWeight = FontWeight.Normal,
                    color = colors.textSubtle,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }

        if (!badgeText.isNullOrBlank()) {
            EnoisStatusBadge(text = badgeText)
        }
    }
}

/** Notification Preview */
@Composable
fun EnoisNotificationPreview(
    label: String,
    title: String,
    body: String,
    modifier: Modifier = Modifier,
    appMeta: String = "Énóis · agora",
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Column(
        modifier = modifier
            .width(165.dp)
            .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
            .background(colors.surface1)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = label,
            style = typography.micro.copy(color = colors.primaryText),
            maxLines = 1,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = AppIcons.Lucide.Bell,
                contentDescription = null,
                tint = colors.textSubtle,
                modifier = Modifier.size(12.dp),
            )
            Text(
                text = appMeta,
                fontFamily = ManropeFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 9.sp,
                lineHeight = 12.sp,
                color = colors.textSubtle,
                maxLines = 1,
            )
        }
        Text(
            text = title,
            style = typography.caption.copy(
                fontWeight = FontWeight.Bold,
                color = colors.textStrong,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = body,
            style = typography.micro.copy(
                fontWeight = FontWeight.Normal,
                lineHeight = 14.sp,
                color = colors.textSubtle,
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/** Post Card */
@Composable
fun EnoisPostCard(
    initials: String,
    author: String,
    handleMeta: String,
    body: String,
    modifier: Modifier = Modifier,
    hasMedia: Boolean = false,
    liked: Boolean = false,
    saved: Boolean = false,
    onLikeClick: () -> Unit = {},
    onCommentClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onMoreClick: () -> Unit = {},
    imageUrl: String? = null,
    mediaContent: (@Composable BoxScope.() -> Unit)? = null,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.card)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            EnoisAvatar(initials = initials, imageUrl = imageUrl)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = author,
                    style = typography.body.copy(
                        fontWeight = FontWeight.Bold,
                        color = colors.textStrong,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = handleMeta,
                    style = typography.labelSmall.copy(
                        fontWeight = FontWeight.Normal,
                        color = colors.textDate,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .clickable(role = Role.Button, onClick = onMoreClick),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = AppIcons.Lucide.Ellipsis,
                    contentDescription = "Mais opções",
                    tint = colors.textSubtle,
                    modifier = Modifier.size(20.dp),
                )
            }
        }

        Text(
            text = body,
            style = typography.body.copy(
                fontWeight = FontWeight.Normal,
                lineHeight = 22.sp,
                color = colors.textStrong,
            ),
        )

        if (hasMedia) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
                    .background(colors.surface1),
                contentAlignment = Alignment.Center,
            ) {
                if (mediaContent != null) {
                    mediaContent()
                } else {
                    Icon(
                        imageVector = AppIcons.Lucide.Image,
                        contentDescription = null,
                        tint = colors.textDate,
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PostActionItem(
                icon = AppIcons.Lucide.Heart,
                label = "Curtir",
                active = liked,
                onClick = onLikeClick,
            )
            PostActionItem(
                icon = AppIcons.Lucide.MessageSquare,
                label = "Comentar",
                active = false,
                onClick = onCommentClick,
            )
            PostActionItem(
                icon = AppIcons.Lucide.Bookmark,
                label = "Salvar",
                active = saved,
                onClick = onSaveClick,
            )
            PostActionItem(
                icon = AppIcons.Lucide.Share2,
                label = "Compartilhar",
                active = false,
                onClick = onShareClick,
            )
        }
    }
}

@Composable
private fun PostActionItem(
    icon: ImageVector,
    label: String,
    active: Boolean,
    onClick: () -> Unit,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography
    val tint = if (active) colors.primary else colors.textSubtle

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 4.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = tint,
            modifier = Modifier.size(18.dp),
        )
        Text(
            text = label,
            style = typography.meta.copy(
                fontWeight = if (active) FontWeight.SemiBold else FontWeight.Normal,
                color = tint,
            ),
            maxLines = 1,
        )
    }
}

/** Event Card */
@Composable
fun EnoisEventCard(
    month: String,
    day: String,
    title: String,
    host: String,
    dateMeta: String,
    attendeesMeta: String,
    modifier: Modifier = Modifier,
    statusBadge: String? = null,
    onRsvpClick: () -> Unit = {},
    showLiveAction: Boolean = false,
    onLiveClick: () -> Unit = {},
    isEnded: Boolean = false,
    rsvpLabel: String = "Confirmar",
    liveLabel: String = "Ao vivo",
    endedLabel: String = "Encerrado",
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
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .width(48.dp)
                    .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
                    .background(colors.surface1)
                    .padding(vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = month.uppercase(),
                    style = typography.micro.copy(color = colors.primary),
                    maxLines = 1,
                )
                Text(
                    text = day,
                    style = typography.sheetTitle.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = colors.textStrong,
                    ),
                    maxLines = 1,
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = title,
                    style = typography.actionLabel.copy(
                        fontWeight = FontWeight.Bold,
                        lineHeight = 18.sp,
                        color = colors.textStrong,
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = host,
                    style = typography.caption.copy(
                        fontWeight = FontWeight.Normal,
                        color = colors.textSubtle,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            if (!statusBadge.isNullOrBlank()) {
                EnoisStatusBadge(text = statusBadge)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = AppIcons.Lucide.CalendarClock,
                    contentDescription = null,
                    tint = colors.textSubtle,
                    modifier = Modifier.size(14.dp),
                )
                Text(
                    text = dateMeta,
                    style = typography.caption.copy(
                        fontWeight = FontWeight.Normal,
                        color = colors.textSubtle,
                    ),
                    maxLines = 1,
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = AppIcons.Lucide.Users,
                    contentDescription = null,
                    tint = colors.textSubtle,
                    modifier = Modifier.size(14.dp),
                )
                Text(
                    text = attendeesMeta,
                    style = typography.caption.copy(
                        fontWeight = FontWeight.Normal,
                        color = colors.textSubtle,
                    ),
                    maxLines = 1,
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (showLiveAction) {
                EventActionPill(
                    text = liveLabel,
                    containerColor = colors.primary,
                    contentColor = colors.onPrimary,
                    onClick = onLiveClick,
                )
            }
            if (isEnded) {
                EventActionPill(
                    text = endedLabel,
                    containerColor = colors.surface1,
                    contentColor = colors.textDate,
                    onClick = null,
                )
            } else {
                EventActionPill(
                    text = rsvpLabel,
                    containerColor = colors.surface2Neutral,
                    contentColor = colors.textStrong,
                    onClick = onRsvpClick,
                )
            }
        }
    }
}

@Composable
private fun EventActionPill(
    text: String,
    containerColor: Color,
    contentColor: Color,
    onClick: (() -> Unit)?,
) {
    val typography = EnoisDesignSystem.typography
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(containerColor)
            .then(
                if (onClick != null) {
                    Modifier.clickable(role = Role.Button, onClick = onClick)
                } else {
                    Modifier
                }
            )
            .padding(horizontal = 10.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = typography.caption.copy(
                fontWeight = FontWeight.Bold,
                color = contentColor,
            ),
            maxLines = 1,
        )
    }
}

/** Mentor Card */
@Composable
fun EnoisMentorCard(
    initials: String,
    name: String,
    roleMeta: String,
    slots: List<String>,
    selectedSlot: String?,
    onSlotClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(EnoisRadius.card))
            .background(colors.card)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            EnoisAvatar(initials = initials, imageUrl = imageUrl)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = name,
                    style = typography.actionLabel.copy(
                        fontWeight = FontWeight.Bold,
                        color = colors.textStrong,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = roleMeta,
                    style = typography.caption.copy(
                        fontWeight = FontWeight.Normal,
                        color = colors.textSubtle,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            slots.forEach { slot ->
                EnoisSlotChip(
                    text = slot,
                    selected = slot == selectedSlot,
                    onClick = { onSlotClick(slot) },
                )
            }
        }
    }
}

/** Project Card */
@Composable
fun EnoisProjectCard(
    title: String,
    author: String,
    tag: String,
    modifier: Modifier = Modifier,
    coverIcon: ImageVector = AppIcons.Lucide.Code,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(EnoisRadius.card))
            .background(colors.card)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clip(RoundedCornerShape(EnoisRadius.iconTileSmall))
                .background(colors.surface1),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = coverIcon,
                contentDescription = null,
                tint = colors.primary,
                modifier = Modifier.size(24.dp),
            )
        }
        Text(
            text = title,
            style = typography.actionLabel.copy(
                fontWeight = FontWeight.Bold,
                color = colors.textStrong,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = author,
                style = typography.caption.copy(
                    fontWeight = FontWeight.Normal,
                    color = colors.textSubtle,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
            EnoisTag(text = tag)
        }
    }
}

/** Blog Card */
@Composable
fun EnoisBlogCard(
    initials: String,
    title: String,
    meta: String,
    description: String,
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
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
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            EnoisAvatar(initials = initials, imageUrl = imageUrl)
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
                        color = colors.textDate,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        Text(
            text = description,
            style = typography.caption.copy(
                fontWeight = FontWeight.Normal,
                color = colors.textSubtle,
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/** Job Card */
@Composable
fun EnoisJobCard(
    initials: String,
    title: String,
    company: String,
    tags: List<String>,
    salary: String,
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(EnoisRadius.card))
            .background(colors.card)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            EnoisAvatar(initials = initials, imageUrl = imageUrl)
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
                    text = company,
                    style = typography.caption.copy(
                        fontWeight = FontWeight.Normal,
                        color = colors.textSubtle,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        if (tags.isNotEmpty()) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                tags.forEach { tag ->
                    EnoisTag(text = tag)
                }
            }
        }

        Text(
            text = salary,
            style = typography.actionLabel.copy(
                fontWeight = FontWeight.Bold,
                color = colors.textStrong,
            ),
        )
    }
}

/** Service Card */
@Composable
fun EnoisServiceCard(
    title: String,
    price: String,
    meta: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(EnoisRadius.card))
            .background(colors.card)
            .clickable(onClick = onClick)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = typography.actionLabel.copy(
                    fontWeight = FontWeight.Bold,
                    color = colors.textStrong,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
            EnoisTag(text = price, highlighted = true)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = meta,
                style = typography.caption.copy(
                    fontWeight = FontWeight.Normal,
                    color = colors.textSubtle,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
            Icon(
                imageVector = AppIcons.Lucide.ChevronRight,
                contentDescription = null,
                tint = colors.textDate,
                modifier = Modifier.size(16.dp),
            )
        }
    }
}

/** Freelance Card */
@Composable
fun EnoisFreelanceCard(
    title: String,
    scope: String,
    payRange: String,
    deadline: String,
    username: String,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    closeLabel: String = "Encerrar",
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = typography.actionLabel.copy(
                    fontWeight = FontWeight.Bold,
                    color = colors.textStrong,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = closeLabel,
                style = typography.caption.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = colors.primaryText,
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(role = Role.Button, onClick = onCloseClick)
                    .padding(horizontal = 6.dp, vertical = 2.dp),
            )
        }

        Text(
            text = scope,
            style = typography.caption.copy(
                fontWeight = FontWeight.Normal,
                color = colors.textSubtle,
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = AppIcons.Lucide.Banknote,
                    contentDescription = null,
                    tint = colors.textSubtle,
                    modifier = Modifier.size(14.dp),
                )
                Text(
                    text = payRange,
                    style = typography.caption.copy(
                        fontWeight = FontWeight.Normal,
                        color = colors.textSubtle,
                    ),
                    maxLines = 1,
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = AppIcons.Lucide.CalendarClock,
                    contentDescription = null,
                    tint = colors.textSubtle,
                    modifier = Modifier.size(14.dp),
                )
                Text(
                    text = deadline,
                    style = typography.caption.copy(
                        fontWeight = FontWeight.Normal,
                        color = colors.textSubtle,
                    ),
                    maxLines = 1,
                )
            }
        }

        Text(
            text = username,
            style = typography.micro.copy(
                fontWeight = FontWeight.SemiBold,
                color = colors.primaryText,
            ),
            maxLines = 1,
        )
    }
}
