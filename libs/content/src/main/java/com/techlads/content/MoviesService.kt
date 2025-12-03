package com.techlads.content

import com.techlads.content.data.FakeMoviesDataProvider
import com.techlads.content.data.MovieResponse
import com.techlads.content.data.MovieVideosResponse
import com.techlads.content.data.MoviesResponse
import com.techlads.network.ApiResult
import com.techlads.network.di.safeGet
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject
import javax.inject.Named

interface MoviesService {
    suspend fun getMovies(category: String): ApiResult<MoviesResponse>
    suspend fun getMovieDetail(movieId: Int): ApiResult<MovieResponse>
    suspend fun getMovieVideos(movieId: Int): ApiResult<MovieVideosResponse>
    suspend fun getTrending(category: String): ApiResult<MoviesResponse>
}

class TmdbApiServiceImpl @Inject constructor(
    private val client: HttpClient,
    @Named("TMDBBaseUrl") private val baseUrl: String,
    @Named("TMDBApiKey") private val apiKey: String
) : MoviesService {

    override suspend fun getMovies(category: String): ApiResult<MoviesResponse> = client.safeGet {
        url("$baseUrl/movie/$category")
        header("Content-Type", "application/json")
        parameter("api_key", apiKey)
        parameter("page", 1)
        parameter("language", "en")
    }

    override suspend fun getMovieDetail(movieId: Int): ApiResult<MovieResponse> = client.safeGet {
        url("$baseUrl/movie/$movieId")
        header("Content-Type", "application/json")
        parameter("api_key", apiKey)
    }

    override suspend fun getMovieVideos(movieId: Int): ApiResult<MovieVideosResponse> = client.safeGet {
        url("$baseUrl/movie/${movieId}/videos")
        header("Content-Type", "application/json")
        parameter("api_key", apiKey)
    }

    override suspend fun getTrending(category: String): ApiResult<MoviesResponse> = client.safeGet {
        url("$baseUrl/trending/$category/day")
        header("Content-Type", "application/json")
        parameter("api_key", apiKey)
        parameter("page", 1)
        parameter("language", "en")
    }
}

class FakeMoviesService @Inject constructor() : MoviesService {
    override suspend fun getMovies(category: String): ApiResult<MoviesResponse> {
        return ApiResult.Success(MoviesResponse(FakeMoviesDataProvider.movies))
    }

    override suspend fun getMovieDetail(movieId: Int): ApiResult<MovieResponse> {
        val movie = FakeMoviesDataProvider.movieDetails.find { it.id == movieId }
            ?: return ApiResult.Error("Movie not found")
        return ApiResult.Success(movie)
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