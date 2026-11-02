package dev.jn.enois.presentation.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jn.enois.domain.model.AvatarFrameType
import dev.jn.enois.domain.model.UserProfile
import dev.jn.enois.presentation.components.avatar.UserAvatar
import dev.jn.enois.presentation.components.cardtheme.LocalCardColors
import dev.jn.enois.presentation.components.cardtheme.LocalCardTheme
import dev.jn.enois.presentation.components.profileeffect.ProfileEffectAmbient
import dev.jn.enois.presentation.components.profileeffect.ProfileEffectLayer
import dev.jn.enois.presentation.components.styledname.StyledName
import dev.jn.enois.presentation.profile.ProfileUiState

@Composable
fun ProfileHeaderCard(
    profile: UserProfile,
    state: ProfileUiState,
    onMessage: () -> Unit,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit,
    onToggleMute: () -> Unit,
) {

    ProfileCard(
        themeId = profile.cardTheme,
        modifier = Modifier.padding(horizontal = 12.dp),
    ) {
        val cardColors = LocalCardColors.current
        val cardTheme = LocalCardTheme.current
        Box {
            Column {
                ProfileBanner(
                    officialBanner = profile.bannerItem,
                    bannerHtml = profile.bannerHtml,
                    photoUrl = profile.banner,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f),
                )

                Row(
                    Modifier
                        .padding(horizontal = 16.dp)
                        .offset(y = (-36).dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {

                    UserAvatar(
                        url = profile.avatar.orEmpty(),
                        userName = profile.username,
                        frame = AvatarFrameType.fromFxName(profile.avatarFrame),
                        size = 88.dp,
                        borderStroke = BorderStroke(
                            width = 6.dp,
                            color = cardColors.top
                        ),
                    )
                    profile.statusText?.takeIf { it.isNotBlank() }?.let {
                        StatusBubble(text = it, icon = profile.statusIcon)
                    }
                }

                Column(
                    Modifier
                        .padding(horizontal = 12.dp)
                        .offset(y = (-24).dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        itemVerticalAlignment = Alignment.CenterVertically,
                    ) {
                        StyledName(
                            name = profile.name,
                            style = profile.nameStyle(),
                            textStyle = MaterialTheme.typography.titleMedium,
                            color = cardColors.fg,
                            glow = cardColors.accent.copy(alpha = 0.55f),
                        )
                        profile.aka?.let {
                            Pill("vulgo $it")
                        }
                        if (profile.openToWork) Pill(
                            "Aberto a oportunidades",
                            tint = cardTheme?.primaryText ?: MaterialTheme.colorScheme.primary
                        )
                        if (profile.isMentor) Pill(
                            "Mentor disponível",
                            tint = cardTheme?.primaryText ?: MaterialTheme.colorScheme.primary
                        )
                        //  if (state.hasDevPage) DevChip()
                    }

                    MetaLine(
                        username = profile.username,
                        pronouns = profile.pronouns,
                        badges = profile.badges.orEmpty()
                    )

                    if (state.isContributor) ContributorTag(colorHex = profile.contributorTagColor)

                    profile.headline?.let {
                        Text(
                            it,
                            color = cardTheme?.primaryText ?: cardColors.accent,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }


                    ProfileInfoRow(
                        company = profile.company,
                        location = profile.location
                    )

                    /*ProfileSeals(username = profile.username)

                 ProfileActions(
                        isOwn = state.isOwn,
                        connection = state.connection,
                        onMessage = onMessage,
                        onConnect = onConnect,
                        onDisconnect = onDisconnect,
                        onToggleMute = onToggleMute,
                    )*/

                    ProfileStats(
                        posts = profile.postsCount,
                        connections = profile.connectionsCount,
                        followers = profile.followersCount,
                        reputation = profile.reputation,
                    )
                }
            }

            profile.profileEffect?.let {
                ProfileEffectAmbient(
                    effect = it,
                    modifier = Modifier.matchParentSize()
                )
                ProfileEffectLayer(
                    effect = it,
                    modifier = Modifier.matchParentSize()
                )
            }
        }
    }

}