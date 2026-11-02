package dev.jn.enois.presentation.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt

@Composable
fun ContributorTag(
    colorHex: String?,
    modifier: Modifier = Modifier) {

    val color = colorHex?.let { Color(it.toColorInt()) } ?: Color.Transparent

    Pill(
        text = "Contribuinte",
        tint = color,
        borderStroke = BorderStroke(
            width = 1.dp,
            color = color
        ),
        modifier = modifier
    )
}