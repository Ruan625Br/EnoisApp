package dev.jn.enois.ui.icons.lucide

import androidx.compose.ui.graphics.vector.ImageVector

internal val lucideMoon: ImageVector by lazy {
    lucideIcon("moon") {
        lucidePath {
            moveTo(20.985f, 12.486f)
            arcToRelative(9f, 9f, 0f, isMoreThanHalf = true, isPositiveArc = true, -9.473f, -9.472f)
            curveToRelative(0.405f, -0.022f, 0.617f, 0.46f, 0.402f, 0.803f)
            arcToRelative(6f, 6f, 0f, isMoreThanHalf = false, isPositiveArc = false, 8.268f, 8.268f)
            curveToRelative(0.344f, -0.215f, 0.825f, -0.004f, 0.803f, 0.401f)
        }
    }
}

internal val lucideWind: ImageVector by lazy {
    lucideIcon("wind") {
        lucidePath {
            moveTo(12.8f, 19.6f)
            arcTo(2f, 2f, 0f, isMoreThanHalf = true, isPositiveArc = false, 14f, 16f)
            horizontalLineTo(2f)
        }
        lucidePath {
            moveTo(17.5f, 8f)
            arcToRelative(2.5f, 2.5f, 0f, isMoreThanHalf = true, isPositiveArc = true, 2f, 4f)
            horizontalLineTo(2f)
        }
        lucidePath {
            moveTo(9.8f, 4.4f)
            arcTo(2f, 2f, 0f, isMoreThanHalf = true, isPositiveArc = true, 11f, 8f)
            horizontalLineTo(2f)
        }
    }
}

internal val lucideBadgeCheck: ImageVector by lazy {
    lucideIcon("badge-check") {
        lucidePath {
            moveTo(3.85f, 8.62f)
            arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4.78f, -4.77f)
            arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 6.74f, 0f)
            arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4.78f, 4.78f)
            arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 6.74f)
            arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, -4.77f, 4.78f)
            arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, -6.75f, 0f)
            arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, -4.78f, -4.77f)
            arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, -6.76f)
            close()
        }
        lucidePath {
            moveTo(9f, 12f)
            lineToRelative(2f, 2f)
            lineToRelative(4f, -4f)
        }
    }
}

internal val lucideCircleAlert: ImageVector by lazy {
    lucideIcon("circle-alert") {
        lucideCircle(12f, 12f, 10f)
        lucidePath {
            moveTo(12f, 8f)
            verticalLineToRelative(4f)
        }
        lucidePath {
            moveTo(12f, 16f)
            horizontalLineToRelative(0.01f)
        }
    }
}

internal val lucideCircleCheck: ImageVector by lazy {
    lucideIcon("circle-check") {
        lucideCircle(12f, 12f, 10f)
        lucidePath {
            moveTo(9f, 12f)
            lineToRelative(2f, 2f)
            lineToRelative(4f, -4f)
        }
    }
}

internal val lucideLoaderCircle: ImageVector by lazy {
    lucideIcon("loader-circle") {
        lucidePath {
            moveTo(21f, 12f)
            arcToRelative(9f, 9f, 0f, isMoreThanHalf = true, isPositiveArc = true, -6.219f, -8.56f)
        }
    }
}

internal val lucideScanFace: ImageVector by lazy {
    lucideIcon("scan-face") {
        lucidePath {
            moveTo(3f, 7f)
            verticalLineTo(5f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -2f)
            horizontalLineToRelative(2f)
        }
        lucidePath {
            moveTo(17f, 3f)
            horizontalLineToRelative(2f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 2f)
            verticalLineToRelative(2f)
        }
        lucidePath {
            moveTo(21f, 17f)
            verticalLineToRelative(2f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, 2f)
            horizontalLineToRelative(-2f)
        }
        lucidePath {
            moveTo(7f, 21f)
            horizontalLineTo(5f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, -2f)
            verticalLineToRelative(-2f)
        }
        lucidePath {
            moveTo(8f, 14f)
            reflectiveCurveToRelative(1.5f, 2f, 4f, 2f)
            reflectiveCurveToRelative(4f, -2f, 4f, -2f)
        }
        lucidePath {
            moveTo(9f, 9f)
            horizontalLineToRelative(0.01f)
        }
        lucidePath {
            moveTo(15f, 9f)
            horizontalLineToRelative(0.01f)
        }
    }
}

internal val lucideTriangleAlert: ImageVector by lazy {
    lucideIcon("triangle-alert") {
        lucidePath {
            moveTo(21.73f, 18f)
            lineToRelative(-8f, -14f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.48f, 0f)
            lineToRelative(-8f, 14f)
            arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 4f, 21f)
            horizontalLineToRelative(16f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 1.73f, -3f)
        }
        lucidePath {
            moveTo(12f, 9f)
            verticalLineToRelative(4f)
        }
        lucidePath {
            moveTo(12f, 17f)
            horizontalLineToRelative(0.01f)
        }
    }
}
