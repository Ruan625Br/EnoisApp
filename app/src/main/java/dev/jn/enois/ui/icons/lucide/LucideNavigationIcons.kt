package dev.jn.enois.ui.icons.lucide

import androidx.compose.ui.graphics.vector.ImageVector

internal val lucideChevronLeft: ImageVector by lazy {
    lucideIcon("chevron-left") {
        lucidePath {
            moveTo(15f, 18f)
            lineToRelative(-6f, -6f)
            lineToRelative(6f, -6f)
        }
    }
}

internal val lucideChevronRight: ImageVector by lazy {
    lucideIcon("chevron-right") {
        lucidePath {
            moveTo(9f, 18f)
            lineToRelative(6f, -6f)
            lineToRelative(-6f, -6f)
        }
    }
}

internal val lucideChevronDown: ImageVector by lazy {
    lucideIcon("chevron-down") {
        lucidePath {
            moveTo(6f, 9f)
            lineToRelative(6f, 6f)
            lineToRelative(6f, -6f)
        }
    }
}

internal val lucideArrowUpRight: ImageVector by lazy {
    lucideIcon("arrow-up-right") {
        lucidePath {
            moveTo(7f, 7f)
            horizontalLineToRelative(10f)
            verticalLineToRelative(10f)
        }
        lucidePath {
            moveTo(7f, 17f)
            lineTo(17f, 7f)
        }
    }
}

internal val lucideArrowLeft: ImageVector by lazy {
    lucideIcon("arrow-left") {
        lucidePath {
            moveTo(12f, 19f)
            lineToRelative(-7f, -7f)
            lineToRelative(7f, -7f)
        }
        lucidePath {
            moveTo(19f, 12f)
            horizontalLineTo(5f)
        }
    }
}

internal val lucideHouse: ImageVector by lazy {
    lucideIcon("house") {
        lucidePath {
            moveTo(15f, 21f)
            verticalLineToRelative(-8f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1f, -1f)
            horizontalLineToRelative(-4f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1f, 1f)
            verticalLineToRelative(8f)
        }
        lucidePath {
            moveTo(3f, 10f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.709f, -1.528f)
            lineToRelative(7f, -5.999f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2.582f, 0f)
            lineToRelative(7f, 5.999f)
            arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 21f, 10f)
            verticalLineToRelative(9f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, 2f)
            horizontalLineTo(5f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, -2f)
            close()
        }
    }
}

internal val lucideCompass: ImageVector by lazy {
    lucideIcon("compass") {
        lucideCircle(12f, 12f, 10f)
        lucidePath {
            moveTo(16.24f, 7.76f)
            lineToRelative(-1.804f, 5.411f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.265f, 1.265f)
            lineTo(7.76f, 16.24f)
            lineToRelative(1.804f, -5.411f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.265f, -1.265f)
            close()
        }
    }
}

internal val lucideEllipsis: ImageVector by lazy {
    lucideIcon("ellipsis") {
        lucideCircle(12f, 12f, 1f)
        lucideCircle(19f, 12f, 1f)
        lucideCircle(5f, 12f, 1f)
    }
}

internal val lucideSearch: ImageVector by lazy {
    lucideIcon("search") {
        lucideCircle(11f, 11f, 8f)
        lucidePath {
            moveTo(21f, 21f)
            lineToRelative(-4.34f, -4.34f)
        }
    }
}

internal val lucideListFilter: ImageVector by lazy {
    lucideIcon("list-filter") {
        lucidePath {
            moveTo(3f, 6f)
            horizontalLineToRelative(18f)
        }
        lucidePath {
            moveTo(7f, 12f)
            horizontalLineToRelative(10f)
        }
        lucidePath {
            moveTo(10f, 18f)
            horizontalLineToRelative(4f)
        }
    }
}

internal val lucideFunnel: ImageVector by lazy {
    lucideIcon("funnel") {
        lucidePath {
            moveTo(10f, 20f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.553f, 0.895f)
            lineToRelative(2f, 1f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 14f, 21f)
            verticalLineToRelative(-7f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.517f, -1.341f)
            lineTo(21.74f, 4.67f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 21f, 3f)
            horizontalLineTo(3f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.742f, 1.67f)
            lineToRelative(7.225f, 7.989f)
            arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 10f, 14f)
            close()
        }
    }
}

internal val lucideExternalLink: ImageVector by lazy {
    lucideIcon("external-link") {
        lucidePath {
            moveTo(15f, 3f)
            horizontalLineToRelative(6f)
            verticalLineToRelative(6f)
        }
        lucidePath {
            moveTo(10f, 14f)
            lineTo(21f, 3f)
        }
        lucidePath {
            moveTo(18f, 13f)
            verticalLineToRelative(6f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, 2f)
            horizontalLineTo(5f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, -2f)
            verticalLineTo(8f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -2f)
            horizontalLineToRelative(6f)
        }
    }
}

internal val lucideLink: ImageVector by lazy {
    lucideIcon("link") {
        lucidePath {
            moveTo(10f, 13f)
            arcToRelative(5f, 5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 7.54f, 0.54f)
            lineToRelative(3f, -3f)
            arcToRelative(5f, 5f, 0f, isMoreThanHalf = false, isPositiveArc = false, -7.07f, -7.07f)
            lineToRelative(-1.72f, 1.71f)
        }
        lucidePath {
            moveTo(14f, 11f)
            arcToRelative(5f, 5f, 0f, isMoreThanHalf = false, isPositiveArc = false, -7.54f, -0.54f)
            lineToRelative(-3f, 3f)
            arcToRelative(5f, 5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 7.07f, 7.07f)
            lineToRelative(1.71f, -1.71f)
        }
    }
}

internal val lucideGlobe: ImageVector by lazy {
    lucideIcon("globe") {
        lucideCircle(12f, 12f, 10f)
        lucidePath {
            moveTo(12f, 2f)
            arcToRelative(14.5f, 14.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 20f)
            arcToRelative(14.5f, 14.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, -20f)
        }
        lucidePath {
            moveTo(2f, 12f)
            horizontalLineToRelative(20f)
        }
    }
}
