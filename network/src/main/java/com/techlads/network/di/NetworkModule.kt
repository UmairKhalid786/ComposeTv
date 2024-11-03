package com.techlads.network.di

import com.techlads.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }

            HttpResponseValidator {
                validateResponse { response ->
                    val statusCode = response.status.value
                    if (statusCode >= 400) {
                        throw ResponseException(response, "HTTP error with status code $statusCode")
                    }
                }

                handleResponseExceptionWithRequest { cause, _ ->
                    when (cause) {
                        is ClientRequestException -> {
                            // Handle 4xx errors
                            println("Client Error: ${cause.response.status}")
                        }
                        is ServerResponseException -> {
                            // Handle 5xx errors
                            println("Server Error: ${cause.response.status}")
                        }
                        is HttpRequestTimeoutException -> {
                            println("Request timed out")
                        }
                        else -> println("Unknown error: ${cause.localizedMessage}")
                    }
                }
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 15000
                socketTimeoutMillis = 15000
            }
        }
    }

    @Provides
    @Named("TMDBBaseUrl")
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Named("TMDBApiKey")
    fun provideApiKey(): String = BuildConfig.TMDB_API_KEY
}


suspend inline fun <reified T> HttpClient.safeGet(block: HttpRequestBuilder.() -> Unit): ApiResult<T> =
    try {
        ApiResult.Success(get(HttpRequestBuilder().apply(block)).body())
    } catch (e: Throwable) {
        ApiResult.Error(e.message ?: "Something went wrong")
    }
