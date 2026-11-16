package dev.jn.enois.presentation.components.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.jn.enois.ui.theme.EnoisDesignSystem

/** Skeleton */
@Composable
fun EnoisSkeletonCard(
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(105.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(colors.surface2Neutral),
            )
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Box(
                    modifier = Modifier
                        .size(width = 140.dp, height = 10.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(colors.surface2Neutral),
                )
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 10.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(colors.surface2Neutral),
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(colors.surface2Neutral),
        )
        Box(
            modifier = Modifier
                .size(width = 200.dp, height = 10.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(colors.surface2Neutral),
        )
    }
}
