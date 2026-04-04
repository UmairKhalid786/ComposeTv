package com.techlads.login.data

import com.techlads.network.ApiResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.inject.Inject
import javax.inject.Named

class TmdbLoginService @Inject constructor(
    private val client: HttpClient,
    @Named("TMDBBaseUrl") private val baseUrl: String,
    @Named("TMDBApiKey") private val apiKey: String,
) {
    suspend fun createRequestToken(): ApiResult<RequestTokenResponse> = get("authentication/token/new")

    suspend fun validateLogin(
        username: String,
        password: String,
        requestToken: String,
    ): ApiResult<RequestTokenResponse> = post(
        path = "authentication/token/validate_with_login",
        body = ValidateLoginRequest(
            username = username,
            password = password,
            requestToken = requestToken,
        ),
    )

    suspend fun createSession(requestToken: String): ApiResult<SessionResponse> = post(
        path = "authentication/session/new",
        body = CreateSessionRequest(requestToken = requestToken),
    )

    private suspend inline fun <reified T> get(path: String): ApiResult<T> {
        if (apiKey.isBlank()) {
            return ApiResult.Error(
                "TMDB API key is missing. Set `tmdbApiKey` in Gradle properties or `TMDB_API_KEY` in your environment."
            )
        }

        return try {
            ApiResult.Success(
                client.get {
                    url("$baseUrl/$path")
                    parameter("api_key", apiKey)
                }.body()
            )
        } catch (exception: ResponseException) {
            ApiResult.Error(exception.toTmdbErrorMessage())
        } catch (exception: Exception) {
            ApiResult.Error(exception.message ?: "TMDB login failed.")
        }
    }

    private suspend inline fun <reified Request : Any, reified Response> post(
        path: String,
        body: Request,
    ): ApiResult<Response> {
        if (apiKey.isBlank()) {
            return ApiResult.Error(
                "TMDB API key is missing. Set `tmdbApiKey` in Gradle properties or `TMDB_API_KEY` in your environment."
            )
        }

        return try {
            ApiResult.Success(
                client.post {
                    url("$baseUrl/$path")
                    parameter("api_key", apiKey)
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                    setBody(body)
                }.body()
            )
        } catch (exception: ResponseException) {
            ApiResult.Error(exception.toTmdbErrorMessage())
        } catch (exception: Exception) {
            ApiResult.Error(exception.message ?: "TMDB login failed.")
        }
    }

    private suspend fun ResponseException.toTmdbErrorMessage(): String {
        val error = runCatching { response.body<TmdbErrorResponse>() }.getOrNull()
        return error?.statusMessage ?: (message ?: "TMDB login failed.")
    }
}

@Serializable
data class RequestTokenResponse(
    val success: Boolean,
    @SerialName("expires_at") val expiresAt: String,
    @SerialName("request_token") val requestToken: String,
)

@Serializable
data class SessionResponse(
    val success: Boolean,
    @SerialName("session_id") val sessionId: String,
)

@Serializable
private data class ValidateLoginRequest(
    val username: String,
    val password: String,
    @SerialName("request_token") val requestToken: String,
)

@Serializable
private data class CreateSessionRequest(
    @SerialName("request_token") val requestToken: String,
)

@Serializable
private data class TmdbErrorResponse(
    @SerialName("status_code") val statusCode: Int? = null,
    @SerialName("status_message") val statusMessage: String? = null,
    val success: Boolean? = null,
)
