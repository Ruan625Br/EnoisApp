
package dev.jn.enois.presentation.components.htmlview

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun SandboxedHtmlView(
    html: String,
    modifier: Modifier = Modifier,
    clip: Boolean = false,
    allowSoundCloud: Boolean = false,
    innerPadding: Boolean = true,
    autoHeight: Boolean = true,
    maxHeight: Dp? = null,
    lite: Boolean = false,
) {
    val context = LocalContext.current
    val dark = isSystemInDarkTheme()

    var measuredHeight by remember(html, dark) {
        mutableIntStateOf(0)
    }

    val document = remember(
        html,
        dark,
        clip,
        innerPadding,
        allowSoundCloud
    ) {
        buildDocument(
            html = html,
            dark = dark,
            clip = clip,
            innerPadding = innerPadding
        )
    }

    val heightModifier = if (autoHeight && !clip && measuredHeight > 0) {
        val contentHeight = measuredHeight.dp
        val height = maxHeight?.let { minOf(contentHeight, it) }
            ?: contentHeight

        Modifier.height(height)
    } else {
        Modifier
    }

    AndroidView(
        modifier = modifier.then(heightModifier),
        factory = { ctx ->
            WebView(ctx).apply {
                settings.apply {
                    javaScriptEnabled = allowSoundCloud

                    allowFileAccess = false
                    allowContentAccess = false

                    domStorageEnabled = false

                    setGeolocationEnabled(false)

                    javaScriptCanOpenWindowsAutomatically = false
                    setSupportMultipleWindows(false)

                    mediaPlaybackRequiresUserGesture = lite

                    mixedContentMode =
                        WebSettings.MIXED_CONTENT_NEVER_ALLOW

                    cacheMode = WebSettings.LOAD_NO_CACHE

                    textZoom = 100
                    layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
                }

                isVerticalScrollBarEnabled = !clip
                isHorizontalScrollBarEnabled = false

                overScrollMode = if (clip) {
                    WebView.OVER_SCROLL_NEVER
                } else {
                    WebView.OVER_SCROLL_IF_CONTENT_SCROLLS
                }

                setBackgroundColor(android.graphics.Color.TRANSPARENT)

                webViewClient = object : WebViewClient() {

                    override fun shouldOverrideUrlLoading(
                        view: WebView,
                        request: WebResourceRequest
                    ): Boolean {
                        if (!request.isForMainFrame) return false

                        return openExternalUrl(
                            request.url,
                            context
                        )
                    }

                    @Deprecated("Deprecated in Android")
                    override fun shouldOverrideUrlLoading(
                        view: WebView,
                        url: String
                    ): Boolean {
                        return openExternalUrl(
                            url.toUri(),
                            context
                        )
                    }

                    override fun onPageFinished(
                        view: WebView,
                        url: String?
                    ) {
                        super.onPageFinished(view, url)
                        updateMeasuredHeight(view) {
                            measuredHeight = it
                        }
                    }
                }

                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(
                        view: WebView,
                        newProgress: Int
                    ) {
                        if (newProgress == 100) {
                            updateMeasuredHeight(view) {
                                measuredHeight = it
                            }
                        }
                    }
                }
            }
        },
        update = { webView ->
            if (webView.tag != document) {
                webView.tag = document
                measuredHeight = 0

                webView.loadDataWithBaseURL(
                    null,
                    document,
                    "text/html",
                    "UTF-8",
                    null
                )
            }
        },
        onRelease = { webView ->
            webView.stopLoading()
            webView.webChromeClient = null
            webView.webViewClient = WebViewClient()
            webView.destroy()
        }
    )
}

private fun updateMeasuredHeight(
    webView: WebView,
    onHeightChanged: (Int) -> Unit
) {
    val contentHeight = webView.contentHeight
    if (contentHeight <= 0) return

    val density = webView.resources.displayMetrics.density

    val height = (contentHeight * density).toInt()

    if (height > 0) {
        onHeightChanged(height)
    }
}

private fun openExternalUrl(
    uri: Uri,
    context: android.content.Context
): Boolean {
    if (uri.scheme != "https") return true

    return try {
        context.startActivity(
            Intent(Intent.ACTION_VIEW, uri)
        )
        true
    } catch (_: Exception) {
        true
    }
}

private const val FRAME_CSP =
    "default-src 'none'; " +
            "img-src https: data:; " +
            "media-src https: data:; " +
            "font-src data:; " +
            "style-src 'unsafe-inline'; " +
            "frame-src https://w.soundcloud.com; " +
            "object-src 'none'; " +
            "connect-src 'none'; " +
            "worker-src 'none'; " +
            "form-action 'none'"

private fun buildDocument(
    html: String,
    dark: Boolean,
    clip: Boolean,
    innerPadding: Boolean
): String {
    return """
        <!doctype html>
        <html
            lang="pt-BR"
            style="color-scheme:${if (dark) "dark" else "light"}"
        >
        <head>
            <meta
                http-equiv="Content-Security-Policy"
                content="$FRAME_CSP"
            >

            <meta
                name="viewport"
                content="width=device-width, initial-scale=1"
            >

            <base target="_blank">

            <style>
                ${buildBaseCss(innerPadding, clip)}
            </style>
        </head>
        <body>
            $html
        </body>
        </html>
    """.trimIndent()
}

private fun buildBaseCss(
    innerPadding: Boolean,
    clip: Boolean
): String {
    val padding = if (innerPadding) "14px 16px" else "0"
    val overflow = if (clip) "overflow:hidden" else ""

    return """
        * {
            box-sizing: border-box;
        }

        html, body {
            margin: 0;
            $overflow
        }

        body {
            background: transparent;
            color: light-dark(#1a1a1a, #e8e6ff);
            color-scheme: light dark;
            padding: $padding;
            overflow-wrap: break-word;
            font: 400 14px/1.55 -apple-system,
                BlinkMacSystemFont, "Segoe UI",
                Roboto, Helvetica, Arial, sans-serif;
        }

        h1,h2,h3,h4,h5,h6 {
            line-height: 1.25;
            margin: .6em 0 .4em;
        }

        p {
            margin: .5em 0;
        }

        img,video,svg,canvas {
            max-width: 100%;
            height: auto;
        }

        figure {
            margin: .8em 0;
        }

        audio {
            width: 100%;
        }

        figcaption {
            font-size: 12px;
            color: light-dark(#666,#b3b0c9);
            margin-top: 4px;
        }

        a {
            color: light-dark(#7c3aed,#c4b5fd);
        }

        ul,ol {
            padding-left: 1.4em;
        }

        table {
            border-collapse: collapse;
            max-width: 100%;
            font-size: 13px;
        }

        th,td {
            border: 1px solid light-dark(#e3e3e3,#3f3d55);
            padding: 5px 8px;
            text-align: left;
        }

        pre {
            overflow-x: auto;
            background: light-dark(#f5f5f5,#221f33);
            color: light-dark(#1a1a1a,#e8e6ff);
            padding: 10px;
            border-radius: 8px;
        }

        code {
            font-family: ui-monospace, SFMono-Regular,
                Menlo, Consolas, monospace;
            font-size: 13px;
        }

        blockquote {
            margin: .6em 0;
            padding-left: 12px;
            border-left: 3px solid light-dark(#ddd,#4a4860);
            color: light-dark(#555,#c8c6dc);
        }

        [data-enois-embed] {
            display: block;
            aspect-ratio: 16/9;
            background: light-dark(#f2f2f5,#221f33);
            border-radius: 10px;
            border: 1px dashed light-dark(#cfcfd6,#4a4860);
            margin: .8em 0;
        }
    """.trimIndent()
}
