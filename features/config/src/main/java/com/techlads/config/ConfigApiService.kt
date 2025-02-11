package com.techlads.config

import com.techlads.config.data.ConfigResponse
import com.techlads.network.ApiResult
import com.techlads.network.di.safeGet
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject
import javax.inject.Named

interface ConfigApiService {
    suspend fun getConfig(): ApiResult<ConfigResponse>
}

class ConfigApiServiceImpl @Inject constructor(
    private val client: HttpClient,
    @Named("TMDBBaseUrl") private val baseUrl: String,
    @Named("TMDBApiKey") private val apiKey: String
) : ConfigApiService {

    override suspend fun getConfig(): ApiResult<ConfigResponse> = client.safeGet {
        url("$baseUrl/configuration")
        header("Content-Type", "application/json")
        parameter("api_key", apiKey)
        parameter("language", "en")
    }
}