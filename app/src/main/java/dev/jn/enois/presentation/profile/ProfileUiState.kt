package dev.jn.enois.presentation.profile

import dev.jn.enois.domain.model.UserProfile

data class ProfileUiState(
    val profile: UserProfile? = null,
    val isOwn: Boolean = false,
    val isContributor: Boolean = false,
    val hasDevPage: Boolean = false,
    val loading: Boolean = true,
)

sealed interface ProfileEvent {
    data class OpenConversation(val id: String) : ProfileEvent
    data class Toast(val message: String) : ProfileEvent
}
