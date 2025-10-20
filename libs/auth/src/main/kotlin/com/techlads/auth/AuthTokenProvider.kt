package com.techlads.auth

/** Narrow surface area for networking modules. */
interface AuthTokenProvider {
    /** Null if logged out or token missing/expired. */
    suspend fun accessTokenOrNull(): String?
}