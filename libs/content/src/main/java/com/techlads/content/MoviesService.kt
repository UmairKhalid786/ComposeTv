package com.techlads.content

import com.techlads.content.data.CreditsResponse
import com.techlads.content.data.FakeCastProvider
import com.techlads.content.data.FakeMoviesDataProvider
import com.techlads.content.data.MovieResponse
import com.techlads.content.data.MovieVideosResponse
import com.techlads.content.data.MoviesResponse
import com.techlads.network.ApiResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.security.cert.CertPathValidatorException
import javax.inject.Inject
import javax.inject.Named
import javax.net.ssl.SSLHandshakeException

interface MoviesService {
    suspend fun getMovies(category: String): ApiResult<MoviesResponse>
    suspend fun getMovieDetail(movieId: Int): ApiResult<MovieResponse>
    suspend fun getMovieCredits(movieId: Int): ApiResult<CreditsResponse>
    suspend fun getMovieVideos(movieId: Int): ApiResult<MovieVideosResponse>
    suspend fun getTrending(category: String): ApiResult<MoviesResponse>
}

class TmdbApiServiceImpl @Inject constructor(
    private val client: HttpClient,
    @Named("TMDBBaseUrl") private val baseUrl: String,
    @Named("TMDBApiKey") private val apiKey: String
) : MoviesService {

    override suspend fun getMovies(category: String): ApiResult<MoviesResponse> = get("movie/$category") {
        parameter("page", 1)
        parameter("language", "en")
    }

    override suspend fun getMovieDetail(movieId: Int): ApiResult<MovieResponse> = get("movie/$movieId")

    override suspend fun getMovieCredits(movieId: Int): ApiResult<CreditsResponse> =
        get("movie/$movieId/credits")

    override suspend fun getMovieVideos(movieId: Int): ApiResult<MovieVideosResponse> =
        get("movie/$movieId/videos")

    override suspend fun getTrending(category: String): ApiResult<MoviesResponse> =
        get("trending/$category/day") {
            parameter("page", 1)
            parameter("language", "en")
        }

    private suspend inline fun <reified T> get(
        path: String,
        crossinline block: HttpRequestBuilder.() -> Unit = {},
    ): ApiResult<T> {
        if (apiKey.isBlank()) {
            return ApiResult.Error(
                "TMDB API key is missing. Set `tmdbApiKey` in Gradle properties or `TMDB_API_KEY` in your environment."
            )
        }

        return try {
            ApiResult.Success(
                client.get {
                    url("$baseUrl/$path")
                    header("Content-Type", "application/json")
                    parameter("api_key", apiKey)
                    block()
                }.body()
            )
        } catch (exception: ResponseException) {
            ApiResult.Error(exception.toTmdbErrorMessage())
        } catch (exception: Exception) {
            ApiResult.Error(exception.toTmdbConnectionError())
        }
    }

    private suspend fun ResponseException.toTmdbErrorMessage(): String {
        val error = runCatching { response.body<TmdbErrorResponse>() }.getOrNull()
        return error?.statusMessage ?: (message ?: "TMDB request failed.")
    }

    private fun Exception.toTmdbConnectionError(): String {
        return when {
            hasCause<SSLHandshakeException>() || hasCause<CertPathValidatorException>() ->
                "Secure connection to TMDB failed. Check the device date/time and any proxy, VPN, or custom certificate setup."

            else -> message ?: "TMDB request failed."
        }
    }

    private inline fun <reified T : Throwable> Throwable.hasCause(): Boolean {
        var current: Throwable? = this
        while (current != null) {
            if (current is T) return true
            current = current.cause
        }
        return false
    }
}

class FakeMoviesService @Inject constructor() : MoviesService {
    override suspend fun getMovies(category: String): ApiResult<MoviesResponse> {
        return ApiResult.Success(MoviesResponse(FakeMoviesDataProvider.movies))
    }

    override suspend fun getMovieDetail(movieId: Int): ApiResult<MovieResponse> {
        val movie =
            FakeMoviesDataProvider.movieDetails.find { it.id == movieId } ?: return ApiResult.Error(
                "Movie not found"
            )
        return ApiResult.Success(movie)
    }

    override suspend fun getMovieCredits(movieId: Int): ApiResult<CreditsResponse> {
        val credits = FakeCastProvider.cast
        return ApiResult.Success(CreditsResponse(id = movieId, cast = credits))
    }

    override suspend fun getMovieVideos(movieId: Int): ApiResult<MovieVideosResponse> {
        val videos = FakeMoviesDataProvider.movieVideos.find { it.id == movieId }
            ?: return ApiResult.Error("No videos found for this movie")
        return ApiResult.Success(videos)
    }

    override suspend fun getTrending(category: String): ApiResult<MoviesResponse> {
        return ApiResult.Success(MoviesResponse(FakeMoviesDataProvider.movies.take(5)))
    }
}

@Serializable
private data class TmdbErrorResponse(
    @SerialName("status_code") val statusCode: Int? = null,
    @SerialName("status_message") val statusMessage: String? = null,
    val success: Boolean? = null,
)
