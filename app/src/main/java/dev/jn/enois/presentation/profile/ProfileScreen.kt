package dev.jn.enois.presentation.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.jn.enois.presentation.profile.components.ProfileHeaderCard
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun ProfileRoute(
    username: String,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(
        parameters = { parametersOf(username) }
    )
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileScreen(
        uiState = uiState,
        onOpenConversation = { },
        onOpenPost = { },
        onOpenProfile = { },
        onBack = { },
        modifier = modifier
    )
}

@Composable
private fun ProfileScreen(
    uiState: ProfileUiState,
    onOpenConversation: (String) -> Unit,
    onOpenPost: (String) -> Unit,
    onOpenProfile: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            item {
                if (uiState.profile != null) {
                    ProfileHeaderCard(
                        profile = uiState.profile,
                        state = uiState,
                        onMessage = { onOpenConversation(uiState.profile.username) },
                        onConnect = { onOpenProfile(uiState.profile.username) },
                        onDisconnect = { onOpenProfile(uiState.profile.username) },
                        onToggleMute = { onOpenProfile(uiState.profile.username) },
                    )

                }
            }
        }
    }
}