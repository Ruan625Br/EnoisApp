package dev.jn.enois.presentation.profile

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn


@Stable
class ProfileViewModel(
    private val username: String,
    private val currentUser: CurrentUserStore,
    private val profiles: UserProfileCache,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = combine(
        _uiState,
        currentUser.profile,
        profiles.observeByUsername(username)
    ) { state, me, profileState ->
        val profile = if (profileState is ProfileState.Loaded) {
            profileState.profile
        } else {
            state.profile
        }

        val isOwn = profile?.id == me?.id

        state.copy(
            profile = profile,
            isOwn = isOwn,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _uiState.value.copy(profile = currentUser.profile.value),
    )

    private val _events = Channel<ProfileEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()


    init {
        load()
    }


    private fun load() {
        currentUser.ensureLoaded()
        profiles.ensureByUsername(username)
    }

}