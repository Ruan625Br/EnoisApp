package dev.jn.enois.presentation.components.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import dev.jn.enois.ui.theme.EnoisDesignSystem

/**
 * Semi-transparent full-screen backdrop overlay placed behind modal sheets or dialogs.
 *
 * Dims the underlying screen content and invokes [onDismiss] when the user taps outside the modal.
 */
@Composable
fun EnoisScrim(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = EnoisDesignSystem.colors
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.scrim)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onDismiss,
            ),
    )
}
