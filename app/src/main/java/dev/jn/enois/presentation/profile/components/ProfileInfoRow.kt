package dev.jn.enois.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dev.jn.enois.ui.icons.AppIcons


@Composable
fun ProfileInfoRow(
    company: String?,
    location: String?,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        InfoItem(
            title = company,
            icon = AppIcons.Enterprise
        )
        InfoItem(
            title = location,
            icon = AppIcons.LocationOn
        )
    }

}


@Composable
private fun InfoItem(
    title: String?,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {

    if (title.isNullOrBlank()) return

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(16.dp),
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}