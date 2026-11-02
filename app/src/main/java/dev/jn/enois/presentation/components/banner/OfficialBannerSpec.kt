package dev.jn.enois.presentation.components.banner

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

internal data class OfficialBannerSpec(
    val sky: Pair<Color, Color>,
    val ground: Color?,
    val icons: List<String>,
    val count: Int = 9,
    val seed: Int = 3,
    val y: ClosedFloatingPointRange<Float> = 18f..64f,
    val scale: ClosedFloatingPointRange<Float> = 0.9f..1.6f,
    val extra: (DrawScope.() -> Unit)? = null,
)

private fun hex(v: Long) = Color(0xFF000000L or v)

private val NatalPraiaSea = Path().apply {
    moveTo(0f, 74f)
    cubicTo(80f, 68f, 160f, 78f, 300f, 70f)
    lineTo(300f, 84f); lineTo(0f, 84f); close()
}

internal val OFFICIAL_BANNERS: Map<String, OfficialBannerSpec> = mapOf(
    "banner-saci" to OfficialBannerSpec(
        sky = hex(0xFDE68A) to hex(0xFB923C), ground = hex(0x166534),
        icons = listOf("redemoinho", "folha", "folhaSeca", "carapuca"), count = 10, seed = 5,
    ),
    "banner-halloween" to OfficialBannerSpec(
        sky = hex(0x312E81) to hex(0x7C2D12),
        ground = hex(0x1C1917),
        icons = listOf("luaCheia", "morcego", "abobora", "fantasma", "morcego"),
        count = 9,
        seed = 8,
    ),
    "banner-floresta" to OfficialBannerSpec(
        sky = hex(0xBBF7D0) to hex(0x14532D), ground = hex(0x0E7490),
        icons = listOf("folhaEscura", "boto", "vitoria", "folha", "chama"), count = 11, seed = 12,
    ),
    "banner-natal-praia" to OfficialBannerSpec(
        sky = hex(0xBAE6FD) to hex(0xFEF3C7), ground = hex(0xFDE68A),
        icons = listOf("coqueiro", "estrela", "presente", "sol", "coqueiro"), count = 8, seed = 21,
        extra = { drawPath(NatalPraiaSea, hex(0x38BDF8)) },
    ),
    "banner-copacabana" to OfficialBannerSpec(
        sky = hex(0x0F172A) to hex(0x312E81), ground = hex(0x0C4A6E),
        icons = listOf("fogos", "fogosVerde", "brilhoDourado"), count = 10, seed = 31,
        y = 12f..56f, scale = 1.2f..2.2f,
    ),
    "banner-bloco" to OfficialBannerSpec(
        sky = hex(0xF0ABFC) to hex(0xFDE68A),
        ground = null,
        icons = listOf("sombrinha", "confete", "mascara", "pandeiro", "brilho"),
        count = 12,
        seed = 61,
    ),
    "banner-arraia" to OfficialBannerSpec(
        sky = hex(0x1E1B4B) to hex(0x7C2D12), ground = hex(0x78350F),
        icons = listOf("bandeirinha", "estrelinha", "fogueira", "milho"), count = 10, seed = 71,
    ),
    "banner-cerrado" to OfficialBannerSpec(
        sky = hex(0xBAE6FD) to hex(0xFEF9C3), ground = hex(0xA16207),
        icons = listOf("ipe", "arara", "ipe", "tucano", "beijaflor"), count = 11, seed = 91,
    ),
    "banner-terminal" to OfficialBannerSpec(
        sky = hex(0x020617) to hex(0x1E1B4B), ground = null,
        icons = listOf("um", "zero", "cafe", "bug", "um", "zero", "merge"), count = 14, seed = 101,
        y = 14f..80f, scale = 0.8f..1.4f,
    ),
    "banner-pipas" to OfficialBannerSpec(
        sky = hex(0x7DD3FC) to hex(0xFDE68A), ground = hex(0x65A30D),
        icons = listOf("pipa", "pipaAzul", "pipaAmarela", "bolha"), count = 9, seed = 121,
    ),
)

internal fun isOfficialBanner(id: String?) = id != null && id in OFFICIAL_BANNERS