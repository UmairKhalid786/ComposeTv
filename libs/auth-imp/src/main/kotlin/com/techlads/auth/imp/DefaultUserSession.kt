@file:OptIn(ExperimentalTime::class)
package com.techlads.auth.imp

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.techlads.auth.AuthState
import com.techlads.auth.AuthTokenProvider
import com.techlads.auth.UserSession
import com.techlads.auth.data.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

private val Context.sessionDataStore by preferencesDataStore(name = "user_session")

private object Keys {
    val USER_ID = stringPreferencesKey("user_id")
    val USER_NAME = stringPreferencesKey("user_name")
    val USER_EMAIL = stringPreferencesKey("user_email")
    val ACCESS = stringPreferencesKey("access_token")
    val REFRESH = stringPreferencesKey("refresh_token")
    val EXPIRES_AT = longPreferencesKey("expires_at_epoch_ms") // -1 if unknown
}

class DefaultUserSession @Inject constructor(
    @ApplicationContext private val appContext: Context
) : UserSession, AuthTokenProvider {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _authState = MutableStateFlow<AuthState>(AuthState.LoggedOut)
    override val authState: StateFlow<AuthState> = _authState.asStateFlow()

    override val isLoggedIn: Flow<Boolean> =
        authState.map { it is AuthState.LoggedIn }.distinctUntilChanged()

    init {
        // Load persisted state
        scope.launch {
            appContext.sessionDataStore.data
                .map { prefs -> prefs.toAuthState() }
                .collect { _authState.value = it }
        }
    }

    override suspend fun setLoggedIn(
        user: User,
        accessToken: String,
        refreshToken: String?,
        expiresAt: Instant?
    ) {
        persist(
            id = user.id,
            name = user.name,
            email = user.email,
            access = accessToken,
            refresh = refreshToken,
            expiresAt = expiresAt?.toEpochMilliseconds()
        )
    }

    override suspend fun updateTokens(
        accessToken: String,
        refreshToken: String?,
        expiresAt: Instant?
    ) {
        appContext.sessionDataStore.edit { p ->
            p[Keys.ACCESS] = accessToken
            refreshToken?.let { p[Keys.REFRESH] = it }
            expiresAt?.let { p[Keys.EXPIRES_AT] = it.toEpochMilliseconds() }
        }
    }

    override suspend fun logout() {
        appContext.sessionDataStore.edit { it.clear() }
    }

    override suspend fun accessTokenOrNull(): String? {
        return appContext.sessionDataStore.data.first()[Keys.ACCESS]
            ?.takeIf { _authState.value is AuthState.LoggedIn && !isExpiredNow() }
    }

    // --- helpers

    private suspend fun persist(
        id: String,
        name: String?,
        email: String?,
        access: String,
        refresh: String?,
        expiresAt: Long?
    ) {
        appContext.sessionDataStore.edit { p ->
            p[Keys.USER_ID] = id
            name?.let { p[Keys.USER_NAME] = it }
            email?.let { p[Keys.USER_EMAIL] = it }
            p[Keys.ACCESS] = access
            refresh?.let { p[Keys.REFRESH] = it }
            if (expiresAt != null) p[Keys.EXPIRES_AT] = expiresAt
        }
    }

    private fun Preferences.toAuthState(): AuthState {
        val access = this[Keys.ACCESS] ?: return AuthState.LoggedOut
        val userId = this[Keys.USER_ID] ?: return AuthState.LoggedOut
        val expiresMs = this[Keys.EXPIRES_AT]
        val state = AuthState.LoggedIn(
            user = User(
                id = userId,
                name = this[Keys.USER_NAME],
                email = this[Keys.USER_EMAIL]
            ),
            accessToken = access,
            refreshToken = this[Keys.REFRESH],
            expiresAt = expiresMs?.let { Instant.fromEpochMilliseconds(it) }
        )
        return if (state.expiresAt?.let { it < Instant.fromEpochMilliseconds(System.currentTimeMillis()) } == true)
            AuthState.LoggedOut else state
    }

    private fun isExpiredNow(): Boolean {
        val s = _authState.value
        return s is AuthState.LoggedIn &&
                s.expiresAt?.toEpochMilliseconds()?.let { it <= System.currentTimeMillis() } == true
    }
}
