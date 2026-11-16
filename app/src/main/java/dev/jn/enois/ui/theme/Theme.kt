package dev.jn.enois.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun EnoisTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// --- Design System Semântico (enois-mobile-client-android.pen) ---

@Immutable
data class EnoisColors(
    val bg: Color,
    val card: Color,
    val surface1: Color,
    val surface2: Color,
    val surface2Neutral: Color,
    val surface3: Color,
    val border: Color,
    val textStrong: Color,
    val textSubtle: Color,
    val textDate: Color,
    val primary: Color,
    val primaryText: Color,
    val onPrimary: Color,
    val primarySoft: Color,
    val danger: Color,
    val dangerSoft: Color,
    val growth: Color,
    val opportunity: Color,
    val scrim: Color = DsScrim,
    val fabShadow: Color = DsFabShadow,
    val tabBarShadow: Color = DsTabBarShadow,
    val isDark: Boolean,
)

val LightEnoisColors = EnoisColors(
    bg = DsLightBg,
    card = DsLightCard,
    surface1 = DsLightSurface1,
    surface2 = DsLightSurface2,
    surface2Neutral = DsLightSurface2Neutral,
    surface3 = DsLightSurface3,
    border = DsLightBorder,
    textStrong = DsLightTextStrong,
    textSubtle = DsLightTextSubtle,
    textDate = DsLightTextDate,
    primary = DsLightPrimary,
    primaryText = DsLightPrimaryText,
    onPrimary = DsLightOnPrimary,
    primarySoft = DsLightPrimarySoft,
    danger = DsLightDanger,
    dangerSoft = DsLightDangerSoft,
    growth = DsLightGrowth,
    opportunity = DsLightOpportunity,
    isDark = false,
)

val DarkEnoisColors = EnoisColors(
    bg = DsDarkBg,
    card = DsDarkCard,
    surface1 = DsDarkSurface1,
    surface2 = DsDarkSurface2,
    surface2Neutral = DsDarkSurface2Neutral,
    surface3 = DsDarkSurface3,
    border = DsDarkBorder,
    textStrong = DsDarkTextStrong,
    textSubtle = DsDarkTextSubtle,
    textDate = DsDarkTextDate,
    primary = DsDarkPrimary,
    primaryText = DsDarkPrimaryText,
    onPrimary = DsDarkOnPrimary,
    primarySoft = DsDarkPrimarySoft,
    danger = DsDarkDanger,
    dangerSoft = DsDarkDangerSoft,
    growth = DsDarkGrowth,
    opportunity = DsDarkOpportunity,
    isDark = true,
)

object EnoisSpacing {
    val xs = 4.dp
    val sm = 8.dp
    val md = 12.dp
    val lg = 16.dp
    val xl = 24.dp
    val xxl = 32.dp
    val minTouchTarget = 44.dp
}

object EnoisRadius {
    val iconTileSmall = 10.dp
    val inputAndRow = 12.dp
    val button = 14.dp
    val card = 16.dp
    val sheetTop = 24.dp
    val pill = 999.dp
}

val LocalEnoisColors = staticCompositionLocalOf { DarkEnoisColors }
val LocalEnoisTypography = staticCompositionLocalOf { EnoisDsTypography }

object EnoisDesignSystem {
    val colors: EnoisColors
        @Composable
        @ReadOnlyComposable
        get() = LocalEnoisColors.current

    val typography: EnoisTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalEnoisTypography.current
}

@Composable
fun EnoisDesignSystemTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val dsColors = if (darkTheme) DarkEnoisColors else LightEnoisColors
    val m3Scheme = if (darkTheme) {
        darkColorScheme(
            primary = dsColors.primary,
            onPrimary = dsColors.onPrimary,
            primaryContainer = dsColors.primarySoft,
            onPrimaryContainer = dsColors.primaryText,
            secondaryContainer = dsColors.surface2,
            background = dsColors.bg,
            onBackground = dsColors.textStrong,
            surface = dsColors.card,
            onSurface = dsColors.textStrong,
            surfaceVariant = dsColors.surface2Neutral,
            onSurfaceVariant = dsColors.textSubtle,
            outline = dsColors.border,
            error = dsColors.danger,
            errorContainer = dsColors.dangerSoft,
            onErrorContainer = dsColors.danger,
        )
    } else {
        lightColorScheme(
            primary = dsColors.primary,
            onPrimary = dsColors.onPrimary,
            primaryContainer = dsColors.primarySoft,
            onPrimaryContainer = dsColors.primaryText,
            secondaryContainer = dsColors.surface2,
            background = dsColors.bg,
            onBackground = dsColors.textStrong,
            surface = dsColors.card,
            onSurface = dsColors.textStrong,
            surfaceVariant = dsColors.surface2Neutral,
            onSurfaceVariant = dsColors.textSubtle,
            outline = dsColors.border,
            error = dsColors.danger,
            errorContainer = dsColors.dangerSoft,
            onErrorContainer = dsColors.danger,
        )
    }

    CompositionLocalProvider(
        LocalEnoisColors provides dsColors,
        LocalEnoisTypography provides EnoisDsTypography,
    ) {
        MaterialTheme(
            colorScheme = m3Scheme,
            typography = Typography,
            content = content,
        )
    }
}
