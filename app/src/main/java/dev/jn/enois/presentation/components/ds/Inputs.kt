package dev.jn.enois.presentation.components.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.jn.enois.ui.icons.AppIcons
import dev.jn.enois.ui.icons.Lucide
import dev.jn.enois.ui.theme.EnoisDesignSystem
import dev.jn.enois.ui.theme.EnoisRadius

/** Input */
@Composable
fun EnoisTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    isError: Boolean = false,
    errorText: String? = null,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography
    val borderColor = if (isError) colors.danger else colors.border

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = label,
            style = typography.meta.copy(color = colors.textSubtle),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
                .background(colors.card)
                .border(1.dp, borderColor, RoundedCornerShape(EnoisRadius.inputAndRow))
                .padding(horizontal = 14.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                enabled = enabled,
                singleLine = singleLine,
                textStyle = typography.body.copy(
                    fontWeight = FontWeight.Normal,
                    color = colors.textStrong,
                ),
                cursorBrush = SolidColor(colors.primary),
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    if (value.isEmpty() && placeholder.isNotEmpty()) {
                        Text(
                            text = placeholder,
                            style = typography.body.copy(
                                fontWeight = FontWeight.Normal,
                                color = colors.textDate,
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    innerTextField()
                },
            )
        }
        if (isError && !errorText.isNullOrBlank()) {
            Text(
                text = errorText,
                style = typography.caption.copy(color = colors.danger),
            )
        }
    }
}

/** Search Field */
@Composable
fun EnoisSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Buscar...",
    onSearch: () -> Unit = {},
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(CircleShape)
            .background(colors.card)
            .padding(horizontal = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = AppIcons.Lucide.Search,
            contentDescription = null,
            tint = colors.textDate,
            modifier = Modifier.size(18.dp),
        )
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            singleLine = true,
            textStyle = typography.actionLabel.copy(
                fontWeight = FontWeight.Normal,
                color = colors.textStrong,
            ),
            cursorBrush = SolidColor(colors.primary),
            keyboardActions = KeyboardActions(onSearch = { onSearch() }),
            modifier = Modifier.weight(1f),
            decorationBox = { innerTextField ->
                if (query.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = typography.actionLabel.copy(
                            fontWeight = FontWeight.Normal,
                            color = colors.textDate,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                innerTextField()
            },
        )
    }
}

@Composable
private fun EnoisFormFieldContainer(
    label: String,
    boxHeight: Dp,
    contentAlignment: Alignment,
    boxPadding: Dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(
            text = label,
            style = typography.labelSmall.copy(
                fontWeight = FontWeight.Medium,
                color = colors.textSubtle,
            ),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(boxHeight)
                .clip(RoundedCornerShape(EnoisRadius.inputAndRow))
                .background(colors.surface1)
                .padding(horizontal = 12.dp, vertical = boxPadding),
            contentAlignment = contentAlignment,
        ) {
            content()
        }
    }
}

/** Form Field / Text */
@Composable
fun EnoisFormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography
    val textStyle: TextStyle = typography.meta.copy(
        fontWeight = FontWeight.Normal,
        color = colors.textStrong,
    )

    EnoisFormFieldContainer(
        label = label,
        boxHeight = 44.dp,
        contentAlignment = Alignment.CenterStart,
        boxPadding = 0.dp,
        modifier = modifier,
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = true,
            textStyle = textStyle,
            cursorBrush = SolidColor(colors.primary),
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (value.isEmpty() && placeholder.isNotEmpty()) {
                    Text(
                        text = placeholder,
                        style = textStyle.copy(color = colors.textDate),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                innerTextField()
            },
        )
    }
}

/** Form Field / Area */
@Composable
fun EnoisFormTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
) {
    val colors = EnoisDesignSystem.colors
    val typography = EnoisDesignSystem.typography
    val textStyle: TextStyle = typography.meta.copy(
        fontWeight = FontWeight.Normal,
        lineHeight = 18.sp,
        color = colors.textStrong,
    )

    EnoisFormFieldContainer(
        label = label,
        boxHeight = 88.dp,
        contentAlignment = Alignment.TopStart,
        boxPadding = 10.dp,
        modifier = modifier,
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = false,
            textStyle = textStyle,
            cursorBrush = SolidColor(colors.primary),
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (value.isEmpty() && placeholder.isNotEmpty()) {
                    Text(
                        text = placeholder,
                        style = textStyle.copy(color = colors.textDate),
                    )
                }
                innerTextField()
            },
        )
    }
}
