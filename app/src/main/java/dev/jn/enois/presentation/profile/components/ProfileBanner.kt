package dev.jn.enois.presentation.profile.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import dev.jn.enois.presentation.components.avatar.resolveCdnUrl
import dev.jn.enois.presentation.components.banner.OfficialBanner
import dev.jn.enois.presentation.components.banner.isOfficialBanner
import dev.jn.enois.presentation.components.htmlview.SandboxedHtmlView

@Composable
fun ProfileBanner(
    officialBanner: String?,
    bannerHtml: String?,
    photoUrl: String?,
    modifier: Modifier = Modifier,
) {
    Log.d(
        "ProfileBanner",
        "officialBanner: $officialBanner, bannerHtml: $bannerHtml, photoUrl: $photoUrl"
    )
    when {
        officialBanner != null && isOfficialBanner(officialBanner) -> {
            OfficialBanner(
                banner = officialBanner, modifier = modifier
            )
        }

        bannerHtml != null ->
            SandboxedHtmlView(
                html = bannerHtml,
                modifier = modifier,
                clip = true,
                innerPadding = false
            )

        photoUrl != null ->
            AsyncImage(
                resolveCdnUrl(photoUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
            )

        else -> Box(modifier.background(Color.Red))
    }
}