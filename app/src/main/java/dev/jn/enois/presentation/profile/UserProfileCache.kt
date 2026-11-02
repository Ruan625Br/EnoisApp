package dev.jn.enois.presentation.profile

import android.os.SystemClock
import dev.jn.enois.domain.model.UserProfile
import dev.jn.enois.domain.repository.UserProfileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import java.util.concurrent.ConcurrentHashMap

sealed interface ProfileState {
    data class Loaded(val profile: UserProfile, val fetchedAt: Long) : ProfileState
    data class Failed(val at: Long) : ProfileState
}

class UserProfileCache(
    private val repository: UserProfileRepository,
    private val scope: CoroutineScope,
) {
    private val _states = MutableStateFlow<Map<String, ProfileState>>(emptyMap())

    private val usernameToId = ConcurrentHashMap<String, String>()

    private val inFlight = ConcurrentHashMap.newKeySet<String>()
    private val gate = Semaphore(4)

    fun observe(id: String): Flow<ProfileState?> =
        _states.map { it[id] }.distinctUntilChanged()

    fun observeByUsername(username: String): Flow<ProfileState?> =
        _states
            .map { states ->
                usernameToId[username.lowercase()]?.let(states::get)
            }
            .distinctUntilChanged()

    fun put(profile: UserProfile) {
        usernameToId[profile.username.lowercase()] = profile.id

        _states.update {
            it + (profile.id to ProfileState.Loaded(profile, now()))
        }
    }

    fun ensure(id: String) {
        if (!isStale(_states.value[id])) return
        fetch("id:$id") {
            repository.getProfileById(id)
        }
    }

    fun ensureByUsername(username: String) {
        val normalized = username.removePrefix("@").lowercase()

        val cachedId = usernameToId[normalized]

        if (cachedId != null && !isStale(_states.value[cachedId])) return

        fetch("username:$normalized") {
            repository.getProfileByUsername(normalized)
        }
    }

    private fun fetch(
        key: String,
        request: suspend () -> Result<UserProfile>,
    ) {
        if (!inFlight.add(key)) return

        scope.launch {
            try {
                val result = gate.withPermit { request() }

                result.onSuccess(::put)
            } finally {
                inFlight.remove(key)
            }
        }
    }

    fun clear() {
        _states.value = emptyMap()
        usernameToId.clear()
    }

    private fun isStale(s: ProfileState?) = when (s) {
        null -> true
        is ProfileState.Loaded -> now() - s.fetchedAt > TTL_MS
        is ProfileState.Failed -> now() - s.at > RETRY_MS
    }

    private fun now() = SystemClock.elapsedRealtime()

    private companion object {
        const val TTL_MS = 5 * 60_000L
        const val RETRY_MS = 30_000L
    }
}