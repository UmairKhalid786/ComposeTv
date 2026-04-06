@file:OptIn(ExperimentalTime::class)

package com.techlads.login.data

import com.techlads.auth.UserSession
import com.techlads.auth.data.User
import com.techlads.network.ApiResult
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class TmdbLoginRepository @Inject constructor(
    private val service: TmdbLoginService,
    private val userSession: UserSession,
) {
    suspend fun login(username: String, password: String): ApiResult<Unit> {
        val requestToken = when (val response = service.createRequestToken()) {
            is ApiResult.Success -> response.data.requestToken
            is ApiResult.Error -> return ApiResult.Error(response.message)
        }

        val validatedToken = when (
            val response = service.validateLogin(
                username = username,
                password = password,
                requestToken = requestToken,
            )
        ) {
            is ApiResult.Success -> response.data.requestToken
            is ApiResult.Error -> return ApiResult.Error(response.message)
        }

        val sessionId = when (val response = service.createSession(validatedToken)) {
            is ApiResult.Success -> response.data.sessionId
            is ApiResult.Error -> return ApiResult.Error(response.message)
        }

        userSession.setLoggedIn(
            user = User(
                id = username,
                name = username,
                email = null,
            ),
            sessionId = sessionId,
        )

        return ApiResult.Success(Unit)
    }
}
