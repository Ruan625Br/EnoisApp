package dev.jn.enois.ui.icons.lucide

import androidx.compose.ui.graphics.vector.ImageVector

object LucideIcons {
    // Navigation (14)
    val ChevronLeft: ImageVector get() = lucideChevronLeft
    val ChevronRight: ImageVector get() = lucideChevronRight
    val ChevronDown: ImageVector get() = lucideChevronDown
    val ArrowUpRight: ImageVector get() = lucideArrowUpRight
    val ArrowLeft: ImageVector get() = lucideArrowLeft
    val House: ImageVector get() = lucideHouse
    val Compass: ImageVector get() = lucideCompass
    val Ellipsis: ImageVector get() = lucideEllipsis
    val Search: ImageVector get() = lucideSearch
    val ListFilter: ImageVector get() = lucideListFilter
    val Funnel: ImageVector get() = lucideFunnel
    val ExternalLink: ImageVector get() = lucideExternalLink
    val Link: ImageVector get() = lucideLink
    val Globe: ImageVector get() = lucideGlobe

    // Action (16)
    val Plus: ImageVector get() = lucidePlus
    val Check: ImageVector get() = lucideCheck
    val X: ImageVector get() = lucideX
    val Settings: ImageVector get() = lucideSettings
    val Bookmark: ImageVector get() = lucideBookmark
    val Share2: ImageVector get() = lucideShare2
    val Flag: ImageVector get() = lucideFlag
    val Circle: ImageVector get() = lucideCircle
    val PenLine: ImageVector get() = lucidePenLine
    val Trash2: ImageVector get() = lucideTrash2
    val Upload: ImageVector get() = lucideUpload
    val Paperclip: ImageVector get() = lucidePaperclip
    val SendHorizontal: ImageVector get() = lucideSendHorizontal
    val Eye: ImageVector get() = lucideEye
    val EyeOff: ImageVector get() = lucideEyeOff
    val Wrench: ImageVector get() = lucideWrench

    // Media & Social (15)
    val Bell: ImageVector get() = lucideBell
    val Heart: ImageVector get() = lucideHeart
    val MessageCircle: ImageVector get() = lucideMessageCircle
    val MessageSquare: ImageVector get() = lucideMessageSquare
    val User: ImageVector get() = lucideUser
    val Users: ImageVector get() = lucideUsers
    val Newspaper: ImageVector get() = lucideNewspaper
    val Code: ImageVector get() = lucideCode
    val AtSign: ImageVector get() = lucideAtSign
    val Mail: ImageVector get() = lucideMail
    val MailCheck: ImageVector get() = lucideMailCheck
    val Image: ImageVector get() = lucideImage
    val Video: ImageVector get() = lucideVideo
    val Smile: ImageVector get() = lucideSmile
    val FolderGit2: ImageVector get() = lucideFolderGit2

    // Business & Finance (9)
    val Banknote: ImageVector get() = lucideBanknote
    val CalendarClock: ImageVector get() = lucideCalendarClock
    val Briefcase: ImageVector get() = lucideBriefcase
    val Calendar: ImageVector get() = lucideCalendar
    val ChartColumn: ImageVector get() = lucideChartColumn
    val Coins: ImageVector get() = lucideCoins
    val HandCoins: ImageVector get() = lucideHandCoins
    val GraduationCap: ImageVector get() = lucideGraduationCap
    val Ticket: ImageVector get() = lucideTicket

    // System & Status (8)
    val Moon: ImageVector get() = lucideMoon
    val Wind: ImageVector get() = lucideWind
    val BadgeCheck: ImageVector get() = lucideBadgeCheck
    val CircleAlert: ImageVector get() = lucideCircleAlert
    val CircleCheck: ImageVector get() = lucideCircleCheck
    val LoaderCircle: ImageVector get() = lucideLoaderCircle
    val ScanFace: ImageVector get() = lucideScanFace
    val TriangleAlert: ImageVector get() = lucideTriangleAlert

    val all: List<Pair<String, ImageVector>> by lazy {
        listOf(
            // Navigation (14)
            "chevron-left" to ChevronLeft,
            "chevron-right" to ChevronRight,
            "chevron-down" to ChevronDown,
            "arrow-up-right" to ArrowUpRight,
            "arrow-left" to ArrowLeft,
            "house" to House,
            "compass" to Compass,
            "ellipsis" to Ellipsis,
            "search" to Search,
            "list-filter" to ListFilter,
            "funnel" to Funnel,
            "external-link" to ExternalLink,
            "link" to Link,
            "globe" to Globe,
            // Action (16)
            "plus" to Plus,
            "check" to Check,
            "x" to X,
            "settings" to Settings,
            "bookmark" to Bookmark,
            "share-2" to Share2,
            "flag" to Flag,
            "circle" to Circle,
            "pen-line" to PenLine,
            "trash-2" to Trash2,
            "upload" to Upload,
            "paperclip" to Paperclip,
            "send-horizontal" to SendHorizontal,
            "eye" to Eye,
            "eye-off" to EyeOff,
            "wrench" to Wrench,
            // Media & Social (15)
            "bell" to Bell,
            "heart" to Heart,
            "message-circle" to MessageCircle,
            "message-square" to MessageSquare,
            "user" to User,
            "users" to Users,
            "newspaper" to Newspaper,
            "code" to Code,
            "at-sign" to AtSign,
            "mail" to Mail,
            "mail-check" to MailCheck,
            "image" to Image,
            "video" to Video,
            "smile" to Smile,
            "folder-git-2" to FolderGit2,
            // Business & Finance (9)
            "banknote" to Banknote,
            "calendar-clock" to CalendarClock,
            "briefcase" to Briefcase,
            "calendar" to Calendar,
            "chart-column" to ChartColumn,
            "coins" to Coins,
            "hand-coins" to HandCoins,
            "graduation-cap" to GraduationCap,
            "ticket" to Ticket,
            // System & Status (8)
            "moon" to Moon,
            "wind" to Wind,
            "badge-check" to BadgeCheck,
            "circle-alert" to CircleAlert,
            "circle-check" to CircleCheck,
            "loader-circle" to LoaderCircle,
            "scan-face" to ScanFace,
            "triangle-alert" to TriangleAlert,
        )
    }
}
