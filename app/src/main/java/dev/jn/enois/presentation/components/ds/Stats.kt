package dev.jn.enois.presentation.components.ds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.jn.enois.ui.theme.EnoisDesignSystem

/** Stat / Stacked */
@Composable
fun EnoisStatStacked(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(
            text = value,
            style = typography.meta.copy(
                fontWeight = FontWeight.ExtraBold,
                color = colors.textStrong,
            ),
        )
        Text(
            text = label,
            style = typography.micro.copy(
                fontWeight = FontWeight.SemiBold,
                color = colors.textSubtle,
            ),
        )
    }
}

/** Stat / Inline */
@Composable
fun EnoisStatInline(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = value,
            style = typography.actionLabel.copy(
                fontWeight = FontWeight.Bold,
                color = colors.textStrong,
            ),
        )
        Text(
            text = label,
            style = typography.labelSmall.copy(
                fontWeight = FontWeight.Normal,
                color = colors.textSubtle,
            ),
        )
    }
}
