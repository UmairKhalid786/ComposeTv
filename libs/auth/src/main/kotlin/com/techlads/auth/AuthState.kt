@file:OptIn(ExperimentalTime::class)

package com.techlads.auth

import com.techlads.auth.data.User
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

sealed interface AuthState {
    data object LoggedOut : AuthState
    data class LoggedIn(
        val user: User,
        val accessToken: String,
        val refreshToken: String?,
        val expiresAt: Instant? // optional
    ) : AuthState
}
