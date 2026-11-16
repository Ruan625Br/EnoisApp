package dev.jn.enois.ui.icons.lucide

import androidx.compose.ui.graphics.vector.ImageVector

internal val lucideBell: ImageVector by lazy {
    lucideIcon("bell") {
        lucidePath {
            moveTo(10.268f, 21f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 3.464f, 0f)
        }
        lucidePath {
            moveTo(3.262f, 15.326f)
            arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 4f, 17f)
            horizontalLineToRelative(16f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.74f, -1.673f)
            curveTo(19.41f, 13.956f, 18f, 12.499f, 18f, 8f)
            arcTo(6f, 6f, 0f, isMoreThanHalf = false, isPositiveArc = false, 6f, 8f)
            curveToRelative(0f, 4.499f, -1.411f, 5.956f, -2.738f, 7.326f)
        }
    }
}

internal val lucideHeart: ImageVector by lazy {
    lucideIcon("heart") {
        lucidePath {
            moveTo(2f, 9.5f)
            arcToRelative(5.5f, 5.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 9.591f, -3.676f)
            arcToRelative(0.56f, 0.56f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.818f, 0f)
            arcTo(5.49f, 5.49f, 0f, isMoreThanHalf = false, isPositiveArc = true, 22f, 9.5f)
            curveToRelative(0f, 2.29f, -1.5f, 4f, -3f, 5.5f)
            lineToRelative(-5.492f, 5.313f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -3f, -0.019f)
            lineTo(5f, 15f)
            curveToRelative(-1.5f, -1.5f, -3f, -3.2f, -3f, -5.5f)
        }
    }
}

internal val lucideMessageCircle: ImageVector by lazy {
    lucideIcon("message-circle") {
        lucidePath {
            moveTo(2.992f, 16.342f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.094f, 1.167f)
            lineToRelative(-1.065f, 3.29f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 1.236f, 1.168f)
            lineToRelative(3.413f, -0.998f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.099f, 0.092f)
            arcToRelative(10f, 10f, 0f, isMoreThanHalf = true, isPositiveArc = false, -4.777f, -4.719f)
        }
    }
}

internal val lucideMessageSquare: ImageVector by lazy {
    lucideIcon("message-square") {
        lucidePath {
            moveTo(22f, 17f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, 2f)
            horizontalLineTo(6.828f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.414f, 0.586f)
            lineToRelative(-2.202f, 2.202f)
            arcTo(0.71f, 0.71f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 21.286f)
            verticalLineTo(5f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -2f)
            horizontalLineToRelative(16f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 2f)
            close()
        }
    }
}

internal val lucideUser: ImageVector by lazy {
    lucideIcon("user") {
        lucidePath {
            moveTo(19f, 21f)
            verticalLineToRelative(-2f)
            arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4f, -4f)
            horizontalLineTo(9f)
            arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4f, 4f)
            verticalLineToRelative(2f)
        }
        lucideCircle(12f, 7f, 4f)
    }
}

internal val lucideUsers: ImageVector by lazy {
    lucideIcon("users") {
        lucidePath {
            moveTo(16f, 21f)
            verticalLineToRelative(-2f)
            arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4f, -4f)
            horizontalLineTo(6f)
            arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4f, 4f)
            verticalLineToRelative(2f)
        }
        lucidePath {
            moveTo(16f, 3.128f)
            arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 7.744f)
        }
        lucidePath {
            moveTo(22f, 21f)
            verticalLineToRelative(-2f)
            arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3f, -3.87f)
        }
        lucideCircle(9f, 7f, 4f)
    }
}

internal val lucideNewspaper: ImageVector by lazy {
    lucideIcon("newspaper") {
        lucidePath {
            moveTo(15f, 18f)
            horizontalLineToRelative(-5f)
        }
        lucidePath {
            moveTo(18f, 14f)
            horizontalLineToRelative(-8f)
        }
        lucidePath {
            moveTo(4f, 22f)
            horizontalLineToRelative(16f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2f, -2f)
            verticalLineTo(4f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2f, -2f)
            horizontalLineTo(8f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2f, 2f)
            verticalLineToRelative(16f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -4f, 0f)
            verticalLineToRelative(-9f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -2f)
            horizontalLineToRelative(2f)
        }
        lucidePath {
            moveTo(10f, 6f)
            horizontalLineToRelative(8f)
            verticalLineToRelative(4f)
            horizontalLineToRelative(-8f)
            close()
        }
    }
}

internal val lucideCode: ImageVector by lazy {
    lucideIcon("code") {
        lucidePath {
            moveTo(16f, 18f)
            lineToRelative(6f, -6f)
            lineToRelative(-6f, -6f)
        }
        lucidePath {
            moveTo(8f, 6f)
            lineToRelative(-6f, 6f)
            lineToRelative(6f, 6f)
        }
    }
}

internal val lucideAtSign: ImageVector by lazy {
    lucideIcon("at-sign") {
        lucideCircle(12f, 12f, 4f)
        lucidePath {
            moveTo(16f, 8f)
            verticalLineToRelative(5f)
            arcToRelative(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = false, 6f, 0f)
            verticalLineToRelative(-1f)
            arcToRelative(10f, 10f, 0f, isMoreThanHalf = true, isPositiveArc = false, -4f, 8f)
        }
    }
}

internal val lucideMail: ImageVector by lazy {
    lucideIcon("mail") {
        lucidePath {
            moveTo(22f, 7f)
            lineToRelative(-8.991f, 5.727f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2.009f, 0f)
            lineTo(2f, 7f)
        }
        lucidePath {
            moveTo(4f, 4f)
            horizontalLineToRelative(16f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 2f)
            verticalLineToRelative(12f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, 2f)
            horizontalLineTo(4f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, -2f)
            verticalLineTo(6f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -2f)
            close()
        }
    }
}

internal val lucideMailCheck: ImageVector by lazy {
    lucideIcon("mail-check") {
        lucidePath {
            moveTo(22f, 13f)
            verticalLineTo(6f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2f, -2f)
            horizontalLineTo(4f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2f, 2f)
            verticalLineToRelative(12f)
            curveToRelative(0f, 1.1f, 0.9f, 2f, 2f, 2f)
            horizontalLineToRelative(8f)
        }
        lucidePath {
            moveTo(22f, 7f)
            lineToRelative(-8.97f, 5.7f)
            arcToRelative(1.94f, 1.94f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2.06f, 0f)
            lineTo(2f, 7f)
        }
        lucidePath {
            moveTo(16f, 19f)
            lineToRelative(2f, 2f)
            lineToRelative(4f, -4f)
        }
    }
}

internal val lucideImage: ImageVector by lazy {
    lucideIcon("image") {
        lucidePath {
            moveTo(5f, 3f)
            horizontalLineToRelative(14f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 2f)
            verticalLineToRelative(14f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, 2f)
            horizontalLineTo(5f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, -2f)
            verticalLineTo(5f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -2f)
            close()
        }
        lucideCircle(9f, 9f, 2f)
        lucidePath {
            moveTo(21f, 15f)
            lineToRelative(-3.086f, -3.086f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.828f, 0f)
            lineTo(6f, 21f)
        }
    }
}

internal val lucideVideo: ImageVector by lazy {
    lucideIcon("video") {
        lucidePath {
            moveTo(16f, 13f)
            lineToRelative(5.223f, 3.482f)
            arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.777f, -0.416f)
            verticalLineTo(7.87f)
            arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.752f, -0.432f)
            lineTo(16f, 10.5f)
        }
        lucidePath {
            moveTo(4f, 6f)
            horizontalLineToRelative(10f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 2f)
            verticalLineToRelative(8f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, 2f)
            horizontalLineTo(4f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, -2f)
            verticalLineTo(8f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -2f)
            close()
        }
    }
}

internal val lucideSmile: ImageVector by lazy {
    lucideIcon("smile") {
        lucideCircle(12f, 12f, 10f)
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

internal val lucideFolderGit2: ImageVector by lazy {
    lucideIcon("folder-git-2") {
        lucidePath {
            moveTo(18f, 19f)
            arcToRelative(5f, 5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -5f, -5f)
            verticalLineToRelative(8f)
        }
        lucidePath {
            moveTo(9f, 20f)
            horizontalLineTo(4f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, -2f)
            verticalLineTo(5f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -2f)
            horizontalLineToRelative(3.9f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.69f, 0.9f)
            lineToRelative(0.81f, 1.2f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 1.67f, 0.9f)
            horizontalLineTo(20f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 2f)
            verticalLineToRelative(5f)
        }
        lucideCircle(13f, 12f, 2f)
        lucideCircle(20f, 19f, 2f)
    }
}
