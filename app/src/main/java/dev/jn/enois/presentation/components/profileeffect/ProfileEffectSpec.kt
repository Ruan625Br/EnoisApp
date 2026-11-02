package dev.jn.enois.presentation.components.profileeffect

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.util.lerp
import dev.jn.enois.presentation.components.avatar.fx.CssEaseOut
import kotlin.math.PI
import kotlin.math.sin

enum class EffectMotion { FALL, RISE, CROSS, SNAKE, BURST }

data class ProfileEffectSpec(
    val icon: String,
    val motion: EffectMotion,
    val icons: List<String>,
    val count: Int,
    val size: Float,
    val melt: Boolean = false,
    val spin: Boolean = false,
    val gap: Float? = null,
)

private typealias M = EffectMotion

private const val FALL_SIZE = 16f
private const val CROSS_GAP_MS = 260f
private const val SNAKE_DOTS = 16

val PROFILE_EFFECTS: Map<String, ProfileEffectSpec> = mapOf(
    "efeito-redemoinho" to ProfileEffectSpec(
        "redemoinho",
        M.CROSS,
        listOf("redemoinho", "carapuca"),
        2,
        60f,
        spin = true,
        gap = 140f
    ),
    "efeito-folhas" to ProfileEffectSpec(
        "folhaSeca",
        M.FALL,
        listOf("folha", "folhaSeca", "folhaEscura"),
        24,
        FALL_SIZE
    ),
    "efeito-morcegos" to ProfileEffectSpec(
        "morcego",
        M.CROSS,
        listOf("morcego"),
        6,
        30f,
        gap = 220f
    ),
    "efeito-fantasmas" to ProfileEffectSpec("fantasma", M.RISE, listOf("fantasma"), 8, 26f),
    "efeito-boitata" to ProfileEffectSpec("chama", M.SNAKE, emptyList(), SNAKE_DOTS, 18f),
    "efeito-boto" to ProfileEffectSpec("boto", M.CROSS, listOf("boto"), 2, 40f, gap = 600f),
    "efeito-neve" to ProfileEffectSpec("floco", M.FALL, listOf("floco"), 26, 14f, melt = true),
    "efeito-presentes" to ProfileEffectSpec(
        "presente",
        M.FALL,
        listOf("presente", "presenteAzul"),
        14,
        22f
    ),
    "efeito-fogos" to ProfileEffectSpec("fogos", M.BURST, listOf("fogos", "fogosVerde"), 6, 70f),
    "efeito-confete" to ProfileEffectSpec("confete", M.FALL, listOf("confete"), 26, FALL_SIZE),
    "efeito-purpurina" to ProfileEffectSpec(
        "brilho",
        M.BURST,
        listOf("brilho", "brilhoDourado"),
        10,
        30f
    ),
    "efeito-estrelinhas" to ProfileEffectSpec("estrelinha", M.BURST, listOf("estrelinha"), 10, 36f),
    "efeito-correio" to ProfileEffectSpec("carta", M.CROSS, listOf("carta"), 1, 36f),
    "efeito-coracoes" to ProfileEffectSpec("coracao", M.RISE, listOf("coracao"), 12, 20f),
    "efeito-petalas" to ProfileEffectSpec("ipe", M.FALL, listOf("ipe"), 18, 14f),
    "efeito-beija-flor" to ProfileEffectSpec("beijaflor", M.CROSS, listOf("beijaflor"), 1, 34f),
    "efeito-binario" to ProfileEffectSpec("um", M.FALL, listOf("zero", "um"), 30, FALL_SIZE),
    "efeito-bugs" to ProfileEffectSpec("bug", M.CROSS, listOf("bug"), 3, 24f, gap = 300f),
    "efeito-bolhas" to ProfileEffectSpec("bolha", M.RISE, listOf("bolha"), 12, 26f),
    "efeito-pipas" to ProfileEffectSpec(
        "pipa",
        M.CROSS,
        listOf("pipa", "pipaAzul", "pipaAmarela"),
        3,
        32f,
        gap = 400f
    ),
)

fun profileEffect(id: String?): ProfileEffectSpec? = id?.let { PROFILE_EFFECTS[it] }


class Pose(val x: Float, val y: Float, val rot: Float = 0f, val scale: Float = 1f)
internal class PoseStop(val at: Float, val pose: Pose)
internal class OpacityStop(val at: Float, val value: Float)

internal class Motion(
    val poses: List<PoseStop>,
    val opacity: List<OpacityStop>,
    val duration: Float,
    val delay: Float,
    val easing: Easing,
)

class Particle internal constructor(
    val icon: String?,
    val color: Color?,
    val size: Float,
    private val m: Motion,
) {
    val endMs: Float get() = m.delay + m.duration

    fun progressAt(ms: Float): Float =
        m.easing.transform(((ms - m.delay) / m.duration).coerceIn(0f, 1f))

    fun poseAt(p: Float): Pose {
        val s = m.poses
        if (p <= s.first().at) return s.first().pose
        for (i in 1 until s.size) {
            val b = s[i]
            if (p <= b.at) {
                val a = s[i - 1]
                val f = (p - a.at) / (b.at - a.at)
                return Pose(
                    lerp(a.pose.x, b.pose.x, f), lerp(a.pose.y, b.pose.y, f),
                    lerp(a.pose.rot, b.pose.rot, f), lerp(a.pose.scale, b.pose.scale, f),
                )
            }
        }
        return s.last().pose
    }

    fun opacityAt(p: Float): Float {
        val s = m.opacity
        if (s.isEmpty()) return 1f
        if (p <= s.first().at) return s.first().value
        for (i in 1 until s.size) {
            val b = s[i]
            if (p <= b.at) {
                val a = s[i - 1]
                return lerp(a.value, b.value, (p - a.at) / (b.at - a.at))
            }
        }
        return s.last().value
    }
}


private val FallEasing = CubicBezierEasing(0.3f, 0.1f, 0.6f, 1f)

private fun stops(vararg v: Pair<Float, Float>) = v.map { OpacityStop(it.first, it.second) }

private fun fall(spec: ProfileEffectSpec, w: Float, h: Float, rnd: () -> Float): Motion {
    val x = rnd() * w
    return Motion(
        poses = listOf(
            PoseStop(0f, Pose(x, -24f)),
            PoseStop(1f, Pose(x + (rnd() - 0.5f) * 60f, h + 10f, rot = rnd() * 540f)),
        ),
        opacity = stops(0f to 1f, 1f to if (spec.melt) 0f else 1f),
        duration = 1800f + rnd() * 1400f,
        delay = rnd() * 900f,
        easing = FallEasing,
    )
}

private fun rise(w: Float, h: Float, rnd: () -> Float): Motion {
    val x = rnd() * w
    return Motion(
        poses = listOf(
            PoseStop(0f, Pose(x, h, scale = 0.6f)),
            PoseStop(1f, Pose(x + (rnd() - 0.5f) * 40f, -30f, scale = 1.1f)),
        ),
        opacity = stops(0f to 0f, 0.2f to 1f, 1f to 0f),
        duration = 2200f + rnd() * 1200f,
        delay = rnd() * 900f,
        easing = CssEaseOut,
    )
}

private fun cross(spec: ProfileEffectSpec, i: Int, w: Float, h: Float, rnd: () -> Float): Motion {
    val base = h * (0.2f + rnd() * 0.45f)
    val poses = List(13) { s ->
        val p = s / 12f
        PoseStop(
            p,
            Pose(
                x = -60f + p * (w + 120f),
                y = base - spec.size / 2f + sin(p * PI.toFloat() * 3f) * 12f,
                rot = if (spec.spin) p * 720f else 0f,
            ),
        )
    }
    return Motion(poses, emptyList(), 2600f, i * (spec.gap ?: CROSS_GAP_MS), LinearEasing)
}

private fun snake(k: Int, w: Float, h: Float): Motion {
    val poses = List(17) { s ->
        val p = s / 16f
        PoseStop(p, Pose(-40f + p * (w + 80f), h * 0.55f + sin(p * PI.toFloat() * 4f) * 16f))
    }
    return Motion(poses, emptyList(), 2800f, k * 50f, LinearEasing)
}

private fun burst(spec: ProfileEffectSpec, i: Int, w: Float, h: Float, rnd: () -> Float): Motion {
    val x = rnd() * (w - spec.size)
    val y = rnd() * (h - spec.size * 0.7f)
    return Motion(
        poses = listOf(
            PoseStop(0f, Pose(x, y, scale = 0.1f)),
            PoseStop(1f, Pose(x, y, scale = 1.1f))
        ),
        opacity = stops(0f to 0f, 0.2f to 1f, 1f to 0f),
        duration = 1300f,
        delay = i * 280f,
        easing = CssEaseOut,
    )
}

fun profileEffectParticles(
    spec: ProfileEffectSpec, w: Float, h: Float, rnd: () -> Float,
): List<Particle> = List(spec.count) { i ->
    val m = when (spec.motion) {
        M.FALL -> fall(spec, w, h, rnd)
        M.RISE -> rise(w, h, rnd)
        M.CROSS -> cross(spec, i, w, h, rnd)
        M.SNAKE -> snake(i, w, h)
        M.BURST -> burst(spec, i, w, h, rnd)
    }
    if (spec.motion == M.SNAKE) {
        Particle(
            null,
            Color.hsl((42 - i * 2).toFloat(), 0.96f, (58 - i) / 100f),
            spec.size - i * 0.8f,
            m
        )
    } else {
        Particle(spec.icons[i % spec.icons.size], null, spec.size, m)
    }
}