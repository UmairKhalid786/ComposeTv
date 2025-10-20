package com.techlads.content.data

import com.techlads.content.MoviesService
import javax.inject.Inject
import javax.inject.Named

data class LocalMoviesDataSource @Inject constructor(
    @Named("FakeMoviesService") private val apiService: MoviesService
) {
    suspend fun fetchPopularMovies() = apiService.getMovies("popular")
    suspend fun fetchTopRatedMovies() = apiService.getMovies("top_rated")
    suspend fun fetchNowPlayingMovies() = apiService.getMovies("now_playing")
    suspend fun fetchUpcomingMovies() = apiService.getMovies("upcoming")
    suspend fun fetchMovieDetail(movieId: Int) = apiService.getMovieDetail(movieId)
    suspend fun fetchMovieVideos(movieId: Int) = apiService.getMovieVideos(movieId)
}

