@file:OptIn(ExperimentalTime::class)

package com.techlads.auth

import com.techlads.auth.data.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


interface UserSession {
    /** Current auth state, hot and replaying last known state. */
    val authState: StateFlow<AuthState>

    /** Derived convenience flow. */
    val isLoggedIn: Flow<Boolean>

    suspend fun setLoggedIn(
        user: User,
        accessToken: String,
        refreshToken: String? = null,
        expiresAt: Instant? = null
    )

    suspend fun logout()

    /** Replace token after refresh without touching user. */
    suspend fun updateTokens(
        accessToken: String,
        refreshToken: String? = null,
        expiresAt: Instant? = null
    )
}
