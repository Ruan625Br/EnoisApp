package dev.jn.enois.presentation.components.ds

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.jn.enois.ui.theme.DarkEnoisColors
import dev.jn.enois.ui.theme.DsFabShadow
import dev.jn.enois.ui.theme.DsScrim
import dev.jn.enois.ui.theme.DsTabBarShadow
import dev.jn.enois.ui.theme.EnoisDsTypography
import dev.jn.enois.ui.theme.EnoisRadius
import dev.jn.enois.ui.theme.EnoisSpacing
import dev.jn.enois.ui.theme.LightEnoisColors
import dev.jn.enois.ui.theme.ManropeFontFamily
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EnoisDesignTokensTest {

    @Test
    fun `light palette matches all 18 semantic tokens and overlays`() {
        assertFalse(LightEnoisColors.isDark)
        assertEquals(Color(0xFFFAF9FF), LightEnoisColors.bg)
        assertEquals(Color(0xFFFFFFFF), LightEnoisColors.card)
        assertEquals(Color(0xFFF9F8FF), LightEnoisColors.surface1)
        assertEquals(Color(0xFFEDE8F5), LightEnoisColors.surface2)
        assertEquals(Color(0xFFEFEDF2), LightEnoisColors.surface2Neutral)
        assertEquals(Color(0xFFF5F3FF), LightEnoisColors.surface3)
        assertEquals(Color(0xFFE5E0EB), LightEnoisColors.border)
        assertEquals(Color(0xFF0F0F14), LightEnoisColors.textStrong)
        assertEquals(Color(0xFF6B7280), LightEnoisColors.textSubtle)
        assertEquals(Color(0xFFA1A1AA), LightEnoisColors.textDate)
        assertEquals(Color(0xFF6746F5), LightEnoisColors.primary)
        assertEquals(Color(0xFF5736DB), LightEnoisColors.primaryText)
        assertEquals(Color(0xFFFFFFFF), LightEnoisColors.onPrimary)
        assertEquals(Color(0xFFEEE8FF), LightEnoisColors.primarySoft)
        assertEquals(Color(0xFFE5484D), LightEnoisColors.danger)
        assertEquals(Color(0xFFFDECEC), LightEnoisColors.dangerSoft)
        assertEquals(Color(0xFF3EBD7E), LightEnoisColors.growth)
        assertEquals(Color(0xFFD97A1F), LightEnoisColors.opportunity)
        assertEquals(DsScrim, LightEnoisColors.scrim)
        assertEquals(DsFabShadow, LightEnoisColors.fabShadow)
        assertEquals(DsTabBarShadow, LightEnoisColors.tabBarShadow)
    }

    @Test
    fun `dark palette matches all 18 semantic tokens and overlays`() {
        assertTrue(DarkEnoisColors.isDark)
        assertEquals(Color(0xFF0D0D18), DarkEnoisColors.bg)
        assertEquals(Color(0xFF13131F), DarkEnoisColors.card)
        assertEquals(Color(0xFF1C1C2E), DarkEnoisColors.surface1)
        assertEquals(Color(0xFF2D1F4E), DarkEnoisColors.surface2)
        assertEquals(Color(0xFF232336), DarkEnoisColors.surface2Neutral)
        assertEquals(Color(0xFF1E1829), DarkEnoisColors.surface3)
        assertEquals(Color(0xFF2A2A3D), DarkEnoisColors.border)
        assertEquals(Color(0xFFE8E6FF), DarkEnoisColors.textStrong)
        assertEquals(Color(0xFF9CA3AF), DarkEnoisColors.textSubtle)
        assertEquals(Color(0xFF9CA3AF), DarkEnoisColors.textDate)
        assertEquals(Color(0xFFA78BFA), DarkEnoisColors.primary)
        assertEquals(Color(0xFFA78BFA), DarkEnoisColors.primaryText)
        assertEquals(Color(0xFF16121F), DarkEnoisColors.onPrimary)
        assertEquals(Color(0xFF2E2140), DarkEnoisColors.primarySoft)
        assertEquals(Color(0xFFFF6B6E), DarkEnoisColors.danger)
        assertEquals(Color(0xFF3A1F26), DarkEnoisColors.dangerSoft)
        assertEquals(Color(0xFF5FD39B), DarkEnoisColors.growth)
        assertEquals(Color(0xFFF0A04B), DarkEnoisColors.opportunity)
        assertEquals(Color(0xAA0D0D18), DarkEnoisColors.scrim)
        assertEquals(Color(0x556746F5), DarkEnoisColors.fabShadow)
        assertEquals(Color(0x266746F5), DarkEnoisColors.tabBarShadow)
    }

    @Test
    fun `spacing and radius tokens match specification`() {
        assertEquals(4.dp, EnoisSpacing.xs)
        assertEquals(8.dp, EnoisSpacing.sm)
        assertEquals(12.dp, EnoisSpacing.md)
        assertEquals(16.dp, EnoisSpacing.lg)
        assertEquals(24.dp, EnoisSpacing.xl)
        assertEquals(32.dp, EnoisSpacing.xxl)
        assertEquals(44.dp, EnoisSpacing.minTouchTarget)

        assertEquals(10.dp, EnoisRadius.iconTileSmall)
        assertEquals(12.dp, EnoisRadius.inputAndRow)
        assertEquals(14.dp, EnoisRadius.button)
        assertEquals(16.dp, EnoisRadius.card)
        assertEquals(24.dp, EnoisRadius.sheetTop)
        assertEquals(999.dp, EnoisRadius.pill)
    }

    @Test
    fun `typography scale has 12 levels using ManropeFontFamily`() {
        val styles = listOf(
            EnoisDsTypography.display to Triple(28.sp, FontWeight.ExtraBold, 34.sp),
            EnoisDsTypography.brandLogo to Triple(24.sp, FontWeight.ExtraBold, 28.sp),
            EnoisDsTypography.screenTitle to Triple(22.sp, FontWeight.Bold, 28.sp),
            EnoisDsTypography.headerTitle to Triple(20.sp, FontWeight.Bold, 26.sp),
            EnoisDsTypography.sheetTitle to Triple(18.sp, FontWeight.Bold, 24.sp),
            EnoisDsTypography.section to Triple(17.sp, FontWeight.Bold, 22.sp),
            EnoisDsTypography.body to Triple(15.sp, FontWeight.Medium, 22.sp),
            EnoisDsTypography.actionLabel to Triple(14.sp, FontWeight.SemiBold, 18.sp),
            EnoisDsTypography.meta to Triple(13.sp, FontWeight.Medium, 18.sp),
            EnoisDsTypography.labelSmall to Triple(12.sp, FontWeight.SemiBold, 16.sp),
            EnoisDsTypography.caption to Triple(11.sp, FontWeight.Medium, 15.sp),
            EnoisDsTypography.micro to Triple(10.sp, FontWeight.Bold, 14.sp),
        )

        assertEquals(12, styles.size)
        styles.forEach { (style, expected) ->
            assertEquals(ManropeFontFamily, style.fontFamily)
            assertEquals(expected.first, style.fontSize)
            assertEquals(expected.second, style.fontWeight)
            assertEquals(expected.third, style.lineHeight)
        }
    }
}
