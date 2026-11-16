package dev.jn.enois.presentation.components.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.jn.enois.ui.icons.AppIcons
import dev.jn.enois.ui.icons.Lucide
import dev.jn.enois.ui.theme.EnoisDesignSystem
import dev.jn.enois.ui.theme.EnoisRadius

/** Sheet Header */
@Composable
fun EnoisSheetHeader(
    title: String,
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier
                .size(width = 40.dp, height = 4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(colors.border),
        )
        Text(
            text = title,
            style = typography.sheetTitle.copy(color = colors.textStrong),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

/** Bottom Sheet */
@Composable
fun EnoisBottomSheetSurface(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = EnoisDesignSystem.colors
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = EnoisRadius.sheetTop, topEnd = EnoisRadius.sheetTop))
            .background(colors.card)
            .padding(start = 20.dp, top = 12.dp, end = 20.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        EnoisSheetHeader(title = title)
        content()
    }
}

/** Sheet / Opt-in */
@Composable
fun EnoisOptInSheet(
    title: String,
    body: String,
    primaryLabel: String,
    secondaryLabel: String,
    footnote: String,
    onPrimaryClick: () -> Unit,
    onSecondaryClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = AppIcons.Lucide.Bell,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = EnoisRadius.sheetTop, topEnd = EnoisRadius.sheetTop))
            .background(colors.card)
            .padding(start = 20.dp, top = 12.dp, end = 20.dp, bottom = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier
                .size(width = 40.dp, height = 4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(colors.border),
        )
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(colors.primarySoft),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colors.primary,
                modifier = Modifier.size(24.dp),
            )
        }
        Text(
            text = title,
            style = typography.headerTitle.copy(
                fontWeight = FontWeight.ExtraBold,
                color = colors.textStrong,
            ),
            textAlign = TextAlign.Center,
        )
        Text(
            text = body,
            style = typography.meta.copy(
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp,
                color = colors.textSubtle,
            ),
            textAlign = TextAlign.Center,
        )
        EnoisPrimaryButton(
            text = primaryLabel,
            onClick = onPrimaryClick,
            modifier = Modifier.fillMaxWidth(),
        )
        EnoisGhostButton(
            text = secondaryLabel,
            onClick = onSecondaryClick,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = footnote,
            style = typography.caption.copy(
                fontWeight = FontWeight.Normal,
                lineHeight = 15.sp,
                color = colors.textDate,
            ),
            textAlign = TextAlign.Center,
        )
    }
}
