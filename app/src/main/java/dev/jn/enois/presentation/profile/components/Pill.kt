package dev.jn.enois.presentation.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun Pill(
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.large,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
    borderStroke: BorderStroke? = null
) {

    Box(
        modifier = modifier
            .background(backgroundColor, shape = shape)
            .then(
                if (borderStroke != null) {
                    Modifier.border(borderStroke, shape = shape)
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 6.dp, vertical = 2.dp),
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = tint.copy(alpha = 0.8f)
        )
    }
}