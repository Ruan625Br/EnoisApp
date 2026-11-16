package dev.jn.enois.ui.icons.lucide

import androidx.compose.ui.graphics.vector.ImageVector

internal val lucidePlus: ImageVector by lazy {
    lucideIcon("plus") {
        lucidePath {
            moveTo(5f, 12f)
            horizontalLineToRelative(14f)
        }
        lucidePath {
            moveTo(12f, 5f)
            verticalLineToRelative(14f)
        }
    }
}

internal val lucideCheck: ImageVector by lazy {
    lucideIcon("check") {
        lucidePath {
            moveTo(20f, 6f)
            lineTo(9f, 17f)
            lineToRelative(-5f, -5f)
        }
    }
}

internal val lucideX: ImageVector by lazy {
    lucideIcon("x") {
        lucidePath {
            moveTo(18f, 6f)
            lineTo(6f, 18f)
        }
        lucidePath {
            moveTo(6f, 6f)
            lineToRelative(12f, 12f)
        }
    }
}

internal val lucideSettings: ImageVector by lazy {
    lucideIcon("settings") {
        lucidePath {
            moveTo(9.671f, 4.136f)
            arcToRelative(2.34f, 2.34f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4.659f, 0f)
            arcToRelative(2.34f, 2.34f, 0f, isMoreThanHalf = false, isPositiveArc = false, 3.319f, 1.915f)
            arcToRelative(2.34f, 2.34f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2.33f, 4.033f)
            arcToRelative(2.34f, 2.34f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 3.831f)
            arcToRelative(2.34f, 2.34f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2.33f, 4.033f)
            arcToRelative(2.34f, 2.34f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.319f, 1.915f)
            arcToRelative(2.34f, 2.34f, 0f, isMoreThanHalf = false, isPositiveArc = true, -4.659f, 0f)
            arcToRelative(2.34f, 2.34f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.32f, -1.915f)
            arcToRelative(2.34f, 2.34f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2.33f, -4.033f)
            arcToRelative(2.34f, 2.34f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, -3.831f)
            arcTo(2.34f, 2.34f, 0f, isMoreThanHalf = false, isPositiveArc = true, 6.35f, 6.051f)
            arcToRelative(2.34f, 2.34f, 0f, isMoreThanHalf = false, isPositiveArc = false, 3.319f, -1.915f)
        }
        lucideCircle(12f, 12f, 3f)
    }
}

internal val lucideBookmark: ImageVector by lazy {
    lucideIcon("bookmark") {
        lucidePath {
            moveTo(17f, 3f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 2f)
            verticalLineToRelative(15f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.496f, 0.868f)
            lineToRelative(-4.512f, -2.578f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.984f, 0f)
            lineToRelative(-4.512f, 2.578f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5f, 20f)
            verticalLineTo(5f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -2f)
            close()
        }
    }
}

internal val lucideShare2: ImageVector by lazy {
    lucideIcon("share-2") {
        lucideCircle(18f, 5f, 3f)
        lucideCircle(6f, 12f, 3f)
        lucideCircle(18f, 19f, 3f)
        lucidePath {
            moveTo(8.59f, 13.51f)
            lineToRelative(6.83f, 3.98f)
        }
        lucidePath {
            moveTo(15.41f, 6.51f)
            lineToRelative(-6.82f, 3.98f)
        }
    }
}

internal val lucideFlag: ImageVector by lazy {
    lucideIcon("flag") {
        lucidePath {
            moveTo(4f, 22f)
            verticalLineTo(4f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.4f, -0.8f)
            arcTo(6f, 6f, 0f, isMoreThanHalf = false, isPositiveArc = true, 8f, 2f)
            curveToRelative(3f, 0f, 5f, 2f, 7.333f, 2f)
            quadToRelative(2f, 0f, 3.067f, -0.8f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 20f, 4f)
            verticalLineToRelative(10f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.4f, 0.8f)
            arcTo(6f, 6f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16f, 16f)
            curveToRelative(-3f, 0f, -5f, -2f, -7.333f, -2f)
            arcToRelative(3.9f, 3.9f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4.667f, 0.8f)
        }
    }
}

internal val lucideCircle: ImageVector by lazy {
    lucideIcon("circle") {
        lucideCircle(12f, 12f, 10f)
    }
}

internal val lucidePenLine: ImageVector by lazy {
    lucideIcon("pen-line") {
        lucidePath {
            moveTo(13f, 21f)
            horizontalLineToRelative(8f)
        }
        lucidePath {
            moveTo(21.174f, 6.812f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.986f, -3.987f)
            lineTo(3.842f, 16.174f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.5f, 0.83f)
            lineToRelative(-1.321f, 4.352f)
            arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.623f, 0.622f)
            lineToRelative(4.353f, -1.32f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.83f, -0.497f)
            close()
        }
    }
}

internal val lucideTrash2: ImageVector by lazy {
    lucideIcon("trash-2") {
        lucidePath {
            moveTo(10f, 11f)
            verticalLineToRelative(6f)
        }
        lucidePath {
            moveTo(14f, 11f)
            verticalLineToRelative(6f)
        }
        lucidePath {
            moveTo(19f, 6f)
            verticalLineToRelative(14f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, 2f)
            horizontalLineTo(7f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, -2f)
            verticalLineTo(6f)
        }
        lucidePath {
            moveTo(3f, 6f)
            horizontalLineToRelative(18f)
        }
        lucidePath {
            moveTo(8f, 6f)
            verticalLineTo(4f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -2f)
            horizontalLineToRelative(4f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 2f)
            verticalLineToRelative(2f)
        }
    }
}

internal val lucideUpload: ImageVector by lazy {
    lucideIcon("upload") {
        lucidePath {
            moveTo(12f, 3f)
            verticalLineToRelative(12f)
        }
        lucidePath {
            moveTo(17f, 8f)
            lineToRelative(-5f, -5f)
            lineToRelative(-5f, 5f)
        }
        lucidePath {
            moveTo(21f, 15f)
            verticalLineToRelative(4f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, 2f)
            horizontalLineTo(5f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, -2f)
            verticalLineToRelative(-4f)
        }
    }
}

internal val lucidePaperclip: ImageVector by lazy {
    lucideIcon("paperclip") {
        lucidePath {
            moveTo(13.234f, 20.252f)
            lineTo(21f, 12.3f)
            arcToRelative(5.66f, 5.66f, 0f, isMoreThanHalf = false, isPositiveArc = false, -8f, -8f)
            lineTo(3.6f, 13.7f)
            arcToRelative(3.5f, 3.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 4.95f, 4.95f)
            lineToRelative(7.9f, -7.9f)
            arcToRelative(1.414f, 1.414f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2f, -2f)
            lineTo(6.6f, 16.6f)
        }
    }
}

internal val lucideSendHorizontal: ImageVector by lazy {
    lucideIcon("send-horizontal") {
        lucidePath {
            moveTo(3.714f, 3.048f)
            arcToRelative(0.498f, 0.498f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.683f, 0.627f)
            lineToRelative(2.843f, 7.627f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 1.396f)
            lineToRelative(-2.842f, 7.627f)
            arcToRelative(0.498f, 0.498f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.682f, 0.627f)
            lineToRelative(18f, -8.5f)
            arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, -0.904f)
            close()
        }
        lucidePath {
            moveTo(6f, 12f)
            horizontalLineToRelative(16f)
        }
    }
}

internal val lucideEye: ImageVector by lazy {
    lucideIcon("eye") {
        lucidePath {
            moveTo(2.062f, 12.348f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, -0.696f)
            arcToRelative(10.75f, 10.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, 19.876f, 0f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 0.696f)
            arcToRelative(10.75f, 10.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, -19.876f, 0f)
        }
        lucideCircle(12f, 12f, 3f)
    }
}

internal val lucideEyeOff: ImageVector by lazy {
    lucideIcon("eye-off") {
        lucidePath {
            moveTo(10.733f, 5.076f)
            arcToRelative(10.744f, 10.744f, 0f, isMoreThanHalf = false, isPositiveArc = true, 11.205f, 6.575f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 0.696f)
            arcToRelative(10.747f, 10.747f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.444f, 2.49f)
        }
        lucidePath {
            moveTo(14.084f, 14.158f)
            arcToRelative(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = true, -4.242f, -4.242f)
        }
        lucidePath {
            moveTo(17.479f, 17.499f)
            arcToRelative(10.75f, 10.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, -15.417f, -5.151f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, -0.696f)
            arcToRelative(10.75f, 10.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4.446f, -5.143f)
        }
        lucidePath {
            moveTo(2f, 2f)
            lineToRelative(20f, 20f)
        }
    }
}

internal val lucideWrench: ImageVector by lazy {
    lucideIcon("wrench") {
        lucidePath {
            moveTo(14.7f, 6.3f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 1.4f)
            lineToRelative(1.6f, 1.6f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 1.4f, 0f)
            lineToRelative(3.106f, -3.105f)
            curveToRelative(0.32f, -0.322f, 0.863f, -0.22f, 0.983f, 0.218f)
            arcToRelative(6f, 6f, 0f, isMoreThanHalf = false, isPositiveArc = true, -8.259f, 7.057f)
            lineToRelative(-7.91f, 7.91f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2.999f, -3f)
            lineToRelative(7.91f, -7.91f)
            arcToRelative(6f, 6f, 0f, isMoreThanHalf = false, isPositiveArc = true, 7.057f, -8.259f)
            curveToRelative(0.438f, 0.12f, 0.54f, 0.662f, 0.219f, 0.984f)
            close()
        }
    }
}
