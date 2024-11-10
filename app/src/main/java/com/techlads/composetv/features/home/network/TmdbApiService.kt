package com.techlads.composetv.features.home.network

import com.techlads.composetv.features.home.network.data.MovieResponse
import com.techlads.network.ApiResult
import com.techlads.network.di.safeGet
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject
import javax.inject.Named

interface TmdbApiService {
    suspend fun getPopularMovies(): ApiResult<MovieResponse>
}

class TmdbApiServiceImpl @Inject constructor(
    private val client: HttpClient,
    @Named("TMDBBaseUrl") private val baseUrl: String,
    @Named("TMDBApiKey") private val apiKey: String
) : TmdbApiService {

    override suspend fun getPopularMovies(): ApiResult<MovieResponse> = client.safeGet {
        url("$baseUrl/movie/popular")
        header("Content-Type", "application/json")
        parameter("api_key", apiKey)
        parameter("page", 1)
        parameter("language", "en")
    }
}