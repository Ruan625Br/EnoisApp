package dev.jn.enois.presentation.components.avatar.fx.seasonal


import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


internal object IconRecorder {
    var current: MutableSet<String>? = null
}

internal object SeasonalIcons {
    private const val SIZE = 128


    private val bitmaps = mutableStateMapOf<String, ImageBitmap>()
    private val loading = HashSet<String>()

    fun get(key: String): ImageBitmap? = bitmaps[key]

    suspend fun load(context: PlatformContext, keys: Set<String>) {
        val loader = SingletonImageLoader.get(context)
        for (key in keys) {
            if (key in bitmaps || !loading.add(key)) continue
            try {
                val request = ImageRequest.Builder(context)
                    .data("file:///android_asset/icons/$key.svg")
                    .size(SIZE, SIZE)
                    .build()
                val result = loader.execute(request)
                if (result is SuccessResult) {
                    val bmp = withContext(Dispatchers.Default) {
                        result.image.toBitmap(SIZE, SIZE).asImageBitmap()
                    }
                    bitmaps[key] = bmp
                }
            } finally {
                loading.remove(key)
            }
        }
    }
}

internal fun cosmeticIcon(key: String): FxNode {
    IconRecorder.current?.add(key)
    return { c ->
        SeasonalIcons.get(key)?.let { img ->
            drawImage(
                img,
                dstOffset = IntOffset(-12, -12),
                dstSize = IntSize(24, 24),
                alpha = c.alpha,
                filterQuality = FilterQuality.High,
            )
        }
    }
}