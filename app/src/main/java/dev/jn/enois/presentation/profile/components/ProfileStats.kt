package dev.jn.enois.presentation.profile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileStats(posts: Int?, connections: Int?, followers: Int?, reputation: Int?) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {

        Row(
            Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Stat("PUBLICAÇÕES", posts, Modifier.weight(1f))
            VerticalDivider()
            Stat("CONEXÕES", connections, Modifier.weight(1f))
            VerticalDivider()
            Stat("SEGUIDORES", followers, Modifier.weight(1f))
            VerticalDivider()
            Stat("REPUTAÇÃO", reputation, Modifier.weight(1f), highlight = true)
        }
    }
}

@Composable
private fun Stat(
    label: String,
    value: Int?,
    modifier: Modifier = Modifier,
    highlight: Boolean = false
) {

    val valueColor =
        if (highlight) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface

    Row(
        modifier = modifier,

        ) {
        Column {
            Text(
                label,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                style = MaterialTheme.typography.labelSmall,
                fontSize = 9.sp
            )
            Text(value?.or(0).toString(), color = valueColor)
        }

    }
}