package dev.jn.enois.presentation.components.ds

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.jn.enois.R
import dev.jn.enois.ui.icons.AppIcons
import dev.jn.enois.ui.icons.Lucide
import dev.jn.enois.ui.theme.EnoisDesignSystem
import dev.jn.enois.ui.theme.EnoisRadius

@Composable
private fun EnoisBaseButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    height: Dp = 48.dp,
    shape: Shape = RoundedCornerShape(EnoisRadius.button),
    border: BorderStroke? = null,
) {
    val typography = EnoisDesignSystem.typography
    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        shape = shape,
        border = border,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor.copy(alpha = 0.5f),
            disabledContentColor = contentColor.copy(alpha = 0.6f),
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
        ),
        contentPadding = PaddingValues(horizontal = 20.dp),
        modifier = modifier
            .defaultMinSize(minWidth = 180.dp)
            .height(height),
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = contentColor,
                strokeWidth = 2.dp,
                modifier = Modifier.size(18.dp),
            )
        } else {
            Text(
                text = text,
                style = typography.body.copy(fontWeight = FontWeight.SemiBold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

/** Button / Primary */
@Composable
fun EnoisPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
) {
    val colors = EnoisDesignSystem.colors
    EnoisBaseButton(
        text = text,
        onClick = onClick,
        containerColor = colors.primary,
        contentColor = colors.onPrimary,
        modifier = modifier,
        enabled = enabled,
        isLoading = isLoading,
    )
}

/** Button / Secondary */
@Composable
fun EnoisSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = EnoisDesignSystem.colors
    EnoisBaseButton(
        text = text,
        onClick = onClick,
        containerColor = colors.surface2Neutral,
        contentColor = colors.textStrong,
        border = BorderStroke(1.dp, colors.border),
        modifier = modifier,
        enabled = enabled,
    )
}

/** Button / Outline */
@Composable
fun EnoisOutlineButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = EnoisDesignSystem.colors
    EnoisBaseButton(
        text = text,
        onClick = onClick,
        containerColor = colors.card,
        contentColor = colors.textStrong,
        border = BorderStroke(1.dp, colors.border),
        modifier = modifier,
        enabled = enabled,
    )
}

/** Button / Danger */
@Composable
fun EnoisDangerButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = EnoisDesignSystem.colors
    EnoisBaseButton(
        text = text,
        onClick = onClick,
        containerColor = colors.dangerSoft,
        contentColor = colors.danger,
        modifier = modifier,
        enabled = enabled,
    )
}

/** Button / Ghost (secundário) */
@Composable
fun EnoisGhostButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = EnoisDesignSystem.colors
    EnoisBaseButton(
        text = text,
        onClick = onClick,
        containerColor = colors.surface1,
        contentColor = colors.textStrong,
        height = 44.dp,
        shape = RoundedCornerShape(EnoisRadius.inputAndRow),
        modifier = modifier,
        enabled = enabled,
    )
}

/** Button / Social */
@Composable
fun EnoisSocialButton(
    text: String,
    iconPainter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    iconContentDescription: String? = null,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography
    Surface(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(EnoisRadius.button),
        color = colors.card,
        border = BorderStroke(1.dp, colors.border),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .alpha(if (enabled) 1f else 0.5f),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = iconPainter,
                contentDescription = iconContentDescription,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(20.dp),
            )
            Text(
                text = text,
                style = typography.body.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = colors.textStrong,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun EnoisGoogleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "Continuar com Google",
    enabled: Boolean = true,
) {
    EnoisSocialButton(
        text = text,
        iconPainter = painterResource(R.drawable.ic_social_google),
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        iconContentDescription = "Google",
    )
}

@Composable
fun EnoisLinkedInButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "Continuar com LinkedIn",
    enabled: Boolean = true,
) {
    EnoisSocialButton(
        text = text,
        iconPainter = painterResource(R.drawable.ic_social_linkedin),
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        iconContentDescription = "LinkedIn",
    )
}

/** FAB */
@Composable
fun EnoisFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = AppIcons.Lucide.Plus,
    contentDescription: String? = null,
) {
    val colors = EnoisDesignSystem.colors
    Box(
        modifier = modifier
            .size(56.dp)
            .shadow(
                elevation = 12.dp,
                shape = CircleShape,
                ambientColor = colors.fabShadow,
                spotColor = colors.fabShadow,
            )
            .clip(CircleShape)
            .background(colors.primary)
            .clickable(role = Role.Button, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = colors.onPrimary,
            modifier = Modifier.size(24.dp),
        )
    }
}
