package dev.jn.enois.presentation.components.ds

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import dev.jn.enois.ui.theme.EnoisDesignSystem

/** Section Label */
@Composable
fun EnoisSectionLabel(
    text: String,
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography
    Text(
        text = text.uppercase(),
        style = typography.micro.copy(
            fontWeight = FontWeight.ExtraBold,
            color = colors.textSubtle,
        ),
        modifier = modifier,
    )
}
