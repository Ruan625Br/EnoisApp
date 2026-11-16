package dev.jn.enois.presentation.components.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import dev.jn.enois.ui.icons.AppIcons
import dev.jn.enois.ui.icons.Lucide
import dev.jn.enois.ui.theme.EnoisDesignSystem
import dev.jn.enois.ui.theme.EnoisRadius

enum class EnoisTabItem(
    val label: String,
    val icon: ImageVector,
) {
    Feed("Feed", AppIcons.Lucide.House),
    Comunidades("Comunidades", AppIcons.Lucide.Users),
    Descobrir("Descobrir", AppIcons.Lucide.Compass),
    Mensagens("Mensagens", AppIcons.Lucide.MessageCircle),
    Perfil("Perfil", AppIcons.Lucide.User),
}

/** Bottom navigation tab bar. */
@Composable
fun EnoisTabBar(
    selectedTab: EnoisTabItem,
    onSelectTab: (EnoisTabItem) -> Unit,
    modifier: Modifier = Modifier,
    unreadMessagesCount: Int = 0,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(colors.card)
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        EnoisTabItem.entries.forEach { tab ->
            val isSelected = tab == selectedTab
            val pillColor = if (isSelected) colors.primarySoft else Color.Transparent
            val iconTint = if (isSelected) colors.primaryText else colors.textSubtle
            val textColor = if (isSelected) colors.primary else colors.textSubtle
            val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
                    .background(pillColor)
                    .clickable(role = Role.Tab, onClick = { onSelectTab(tab) }),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Box(contentAlignment = Alignment.TopEnd) {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.label,
                        tint = iconTint,
                        modifier = Modifier.size(22.dp),
                    )
                    if (tab == EnoisTabItem.Mensagens && unreadMessagesCount > 0) {
                        EnoisBadgeDot(
                            count = unreadMessagesCount,
                            size = 16.dp,
                            modifier = Modifier.offset(x = 8.dp, y = (-4).dp),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = tab.label,
                    style = typography.micro.copy(
                        fontWeight = fontWeight,
                        color = textColor,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
