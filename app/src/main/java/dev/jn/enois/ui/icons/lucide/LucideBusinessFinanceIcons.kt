package dev.jn.enois.ui.icons.lucide

import androidx.compose.ui.graphics.vector.ImageVector

internal val lucideBanknote: ImageVector by lazy {
    lucideIcon("banknote") {
        lucidePath {
            moveTo(4f, 6f)
            horizontalLineToRelative(16f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 2f)
            verticalLineToRelative(8f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, 2f)
            horizontalLineTo(4f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, -2f)
            verticalLineTo(8f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -2f)
            close()
        }
        lucideCircle(12f, 12f, 2f)
        lucidePath {
            moveTo(6f, 12f)
            horizontalLineToRelative(0.01f)
        }
        lucidePath {
            moveTo(18f, 12f)
            horizontalLineToRelative(0.01f)
        }
    }
}

internal val lucideCalendarClock: ImageVector by lazy {
    lucideIcon("calendar-clock") {
        lucidePath {
            moveTo(16f, 14f)
            verticalLineToRelative(2.2f)
            lineToRelative(1.6f, 1f)
        }
        lucidePath {
            moveTo(16f, 2f)
            verticalLineToRelative(4f)
        }
        lucidePath {
            moveTo(21f, 7.5f)
            verticalLineTo(6f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2f, -2f)
            horizontalLineTo(5f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2f, 2f)
            verticalLineToRelative(14f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2f, 2f)
            horizontalLineToRelative(3.5f)
        }
        lucidePath {
            moveTo(3f, 10f)
            horizontalLineToRelative(5f)
        }
        lucidePath {
            moveTo(8f, 2f)
            verticalLineToRelative(4f)
        }
        lucideCircle(16f, 16f, 6f)
    }
}

internal val lucideBriefcase: ImageVector by lazy {
    lucideIcon("briefcase") {
        lucidePath {
            moveTo(16f, 20f)
            verticalLineTo(4f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2f, -2f)
            horizontalLineToRelative(-4f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2f, 2f)
            verticalLineToRelative(16f)
        }
        lucidePath {
            moveTo(4f, 6f)
            horizontalLineToRelative(16f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 2f)
            verticalLineToRelative(10f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, 2f)
            horizontalLineTo(4f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, -2f)
            verticalLineTo(8f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -2f)
            close()
        }
    }
}

internal val lucideCalendar: ImageVector by lazy {
    lucideIcon("calendar") {
        lucidePath {
            moveTo(8f, 2f)
            verticalLineToRelative(4f)
        }
        lucidePath {
            moveTo(16f, 2f)
            verticalLineToRelative(4f)
        }
        lucidePath {
            moveTo(5f, 4f)
            horizontalLineToRelative(14f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 2f)
            verticalLineToRelative(14f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, 2f)
            horizontalLineTo(5f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, -2f)
            verticalLineTo(6f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -2f)
            close()
        }
        lucidePath {
            moveTo(3f, 10f)
            horizontalLineToRelative(18f)
        }
    }
}

internal val lucideChartColumn: ImageVector by lazy {
    lucideIcon("chart-column") {
        lucidePath {
            moveTo(3f, 3f)
            verticalLineToRelative(16f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2f, 2f)
            horizontalLineToRelative(16f)
        }
        lucidePath {
            moveTo(18f, 17f)
            verticalLineTo(9f)
        }
        lucidePath {
            moveTo(13f, 17f)
            verticalLineTo(5f)
        }
        lucidePath {
            moveTo(8f, 17f)
            verticalLineToRelative(-3f)
        }
    }
}

internal val lucideCoins: ImageVector by lazy {
    lucideIcon("coins") {
        lucideCircle(8f, 8f, 6f)
        lucidePath {
            moveTo(18.09f, 10.37f)
            arcTo(6f, 6f, 0f, isMoreThanHalf = true, isPositiveArc = true, 10.34f, 18f)
        }
        lucidePath {
            moveTo(7f, 6f)
            horizontalLineToRelative(1f)
            verticalLineToRelative(4f)
        }
        lucidePath {
            moveTo(16.71f, 13.88f)
            lineToRelative(0.7f, 0.71f)
            lineToRelative(-2.82f, 2.82f)
        }
    }
}

internal val lucideHandCoins: ImageVector by lazy {
    lucideIcon("hand-coins") {
        lucidePath {
            moveTo(11f, 15f)
            horizontalLineToRelative(2f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, -4f)
            horizontalLineToRelative(-3f)
            curveToRelative(-0.6f, 0f, -1.1f, 0.2f, -1.4f, 0.6f)
            lineTo(3f, 17f)
        }
        lucidePath {
            moveTo(7f, 21f)
            lineToRelative(1.6f, -1.4f)
            curveToRelative(0.3f, -0.4f, 0.8f, -0.6f, 1.4f, -0.6f)
            horizontalLineToRelative(4f)
            curveToRelative(1.1f, 0f, 2.1f, -0.4f, 2.8f, -1.2f)
            lineToRelative(4.6f, -4.4f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.75f, -2.91f)
            lineToRelative(-4.2f, 3.9f)
        }
        lucidePath {
            moveTo(2f, 16f)
            lineToRelative(6f, 6f)
        }
        lucideCircle(16f, 9f, 2.9f)
        lucideCircle(6f, 5f, 3f)
    }
}

internal val lucideGraduationCap: ImageVector by lazy {
    lucideIcon("graduation-cap") {
        lucidePath {
            moveTo(21.42f, 10.922f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.019f, -1.838f)
            lineTo(12.83f, 5.18f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.66f, 0f)
            lineTo(2.6f, 9.08f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 1.832f)
            lineToRelative(8.57f, 3.908f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 1.66f, 0f)
            close()
        }
        lucidePath {
            moveTo(22f, 10f)
            verticalLineToRelative(6f)
        }
        lucidePath {
            moveTo(6f, 12.5f)
            verticalLineTo(16f)
            arcToRelative(6f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = false, 12f, 0f)
            verticalLineToRelative(-3.5f)
        }
    }
}

internal val lucideTicket: ImageVector by lazy {
    lucideIcon("ticket") {
        lucidePath {
            moveTo(2f, 9f)
            arcToRelative(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 6f)
            verticalLineToRelative(2f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2f, 2f)
            horizontalLineToRelative(16f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2f, -2f)
            verticalLineToRelative(-2f)
            arcToRelative(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, -6f)
            verticalLineTo(7f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2f, -2f)
            horizontalLineTo(4f)
            arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2f, 2f)
            close()
        }
        lucidePath {
            moveTo(13f, 5f)
            verticalLineToRelative(2f)
        }
        lucidePath {
            moveTo(13f, 17f)
            verticalLineToRelative(2f)
        }
        lucidePath {
            moveTo(13f, 11f)
            verticalLineToRelative(2f)
        }
    }
}
