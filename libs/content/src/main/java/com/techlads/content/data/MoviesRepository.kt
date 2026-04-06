package com.techlads.content.data

import com.techlads.network.ApiResult
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    val localMoviesDataSource: LocalMoviesDataSource,
    val remoteMoviesDataSource: RemoteMoviesDataSource,
) {
    suspend fun getPopularMovies() = remoteOrLocal(
        remote = { remoteMoviesDataSource.fetchPopularMovies() },
        local = { localMoviesDataSource.fetchPopularMovies() },
    )

    suspend fun getTopRatedMovies() = remoteOrLocal(
        remote = { remoteMoviesDataSource.fetchTopRatedMovies() },
        local = { localMoviesDataSource.fetchTopRatedMovies() },
    )

    suspend fun getNowPlaying() = remoteOrLocal(
        remote = { remoteMoviesDataSource.fetchNowPlayingMovies() },
        local = { localMoviesDataSource.fetchNowPlayingMovies() },
    )

    suspend fun getUpcoming() = remoteOrLocal(
        remote = { remoteMoviesDataSource.fetchUpcomingMovies() },
        local = { localMoviesDataSource.fetchUpcomingMovies() },
    )

    suspend fun getMovieDetail(movieId: Int) = remoteOrLocal(
        remote = { remoteMoviesDataSource.fetchMovieDetail(movieId) },
        local = { localMoviesDataSource.fetchMovieDetail(movieId) },
    )

    suspend fun getMovieCredit(movieId: Int) = remoteOrLocal(
        remote = { remoteMoviesDataSource.fetchMovieCredits(movieId) },
        local = { localMoviesDataSource.fetchMovieCredit(movieId) },
    )

    suspend fun getMovieVideos(movieId: Int) = remoteOrLocal(
        remote = { remoteMoviesDataSource.fetchMovieVideos(movieId) },
        local = { localMoviesDataSource.fetchMovieVideos(movieId) },
    )

    private suspend fun <T> remoteOrLocal(
        remote: suspend () -> ApiResult<T>,
        local: suspend () -> ApiResult<T>,
    ): ApiResult<T> = when (val remoteResult = remote()) {
        is ApiResult.Success -> remoteResult
        is ApiResult.Error -> local()
    }
}
