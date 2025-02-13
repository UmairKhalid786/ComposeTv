package com.techlads.content.data

import com.techlads.content.TmdbApiService
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val apiService: TmdbApiService) {
    suspend fun getPopularMovies() = apiService.getMovies("popular")
    suspend fun getTopRatedMovies() = apiService.getMovies("top_rated")
    suspend fun getNowPlaying() = apiService.getMovies("now_playing")
    suspend fun getUpcoming() = apiService.getMovies("upcoming")
    suspend fun getMovieDetail(movieId: Int) = apiService.getMovieDetail(movieId)
    suspend fun getMovieVideos(movieId: Int) = apiService.getMovieVideos(movieId)
}