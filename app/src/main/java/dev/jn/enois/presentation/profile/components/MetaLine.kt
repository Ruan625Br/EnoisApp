package dev.jn.enois.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.dp
import dev.jn.enois.presentation.components.badge.UserBadges

@Composable
fun MetaLine(
    username: String,
    pronouns: String?,
    badges: List<String>,
    modifier: Modifier = Modifier) {

    val separatorWithPronouns = if (pronouns.isNullOrBlank()) "" else "· $pronouns"
    val metaLine = "@$username $separatorWithPronouns"
    val badgesBgColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
    val scheme = MaterialTheme.colorScheme
    val bottom = scheme.surfaceContainerHighest
    val fg = scheme.onSurface

    val fill = lerp(bottom, fg, 0.16f / 0.88f).copy(alpha = 0.88f)

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = metaLine,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Box(
            modifier = Modifier
                .background(badgesBgColor, shape = MaterialTheme.shapes.large),
            contentAlignment = Alignment.Center
        ) {
            UserBadges(
                badges = badges,
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }
    }
}