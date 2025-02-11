package com.techlads.content

import com.techlads.content.data.MovieResponse
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
    suspend fun getTopRatedMovies(): ApiResult<MovieResponse>
    suspend fun getNowPlaying(): ApiResult<MovieResponse>
    suspend fun getUpcoming(): ApiResult<MovieResponse>
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

    override suspend fun getTopRatedMovies(): ApiResult<MovieResponse> = client.safeGet {
        url("$baseUrl/movie/top_rated")
        header("Content-Type", "application/json")
        parameter("api_key", apiKey)
        parameter("page", 1)
        parameter("language", "en")
    }

    override suspend fun getNowPlaying(): ApiResult<MovieResponse> = client.safeGet {
        url("$baseUrl/movie/now_playing")
        header("Content-Type", "application/json")
        parameter("api_key", apiKey)
        parameter("page", 1)
        parameter("language", "en")
    }

    override suspend fun getUpcoming(): ApiResult<MovieResponse> = client.safeGet {
        url("$baseUrl/movie/upcoming")
        header("Content-Type", "application/json")
        parameter("api_key", apiKey)
        parameter("page", 1)
        parameter("language", "en")
    }
}