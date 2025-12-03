package com.techlads.content.data

import com.techlads.network.ApiResult
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    val localMoviesDataSource: LocalMoviesDataSource,
    val remoteMoviesDataSource: RemoteMoviesDataSource,
) {
    suspend fun getPopularMovies() = remoteMoviesDataSource.fetchPopularMovies().apply {
        // Simulate caching to local data source
        if (this is ApiResult.Error) {
            localMoviesDataSource.fetchPopularMovies()
        }
    }

    suspend fun getTopRatedMovies() = remoteMoviesDataSource.fetchTopRatedMovies().apply {
        // Simulate caching to local data source
        if (this is ApiResult.Error) {
            localMoviesDataSource.fetchTopRatedMovies()
        }
    }

    suspend fun getNowPlaying() = remoteMoviesDataSource.fetchNowPlayingMovies().apply {
        // Simulate caching to local data source
        if (this is ApiResult.Error) {
            localMoviesDataSource.fetchNowPlayingMovies()
        }
    }

    suspend fun getUpcoming() = remoteMoviesDataSource.fetchUpcomingMovies().apply {
        // Simulate caching to local data source
        if (this is ApiResult.Error) {
            localMoviesDataSource.fetchUpcomingMovies()
        }
    }

    suspend fun getMovieDetail(movieId: Int) =
        remoteMoviesDataSource.fetchMovieDetail(movieId).apply {
            // Simulate caching to local data source
            if (this is ApiResult.Error) {
                localMoviesDataSource.fetchMovieDetail(movieId)
            }
        }

    suspend fun getMovieVideos(movieId: Int) =
        remoteMoviesDataSource.fetchMovieVideos(movieId).apply {
            // Simulate caching to local data source
            if (this is ApiResult.Error) {
                localMoviesDataSource.fetchMovieVideos(movieId)
            }
        }
}