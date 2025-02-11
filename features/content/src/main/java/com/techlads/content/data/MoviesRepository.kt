package com.techlads.content.data

import com.techlads.content.TmdbApiService
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val apiService: TmdbApiService) {
    suspend fun getPopularMovies() = apiService.getPopularMovies()
    suspend fun getTopRatedMovies() = apiService.getTopRatedMovies()
    suspend fun getNowPlaying() = apiService.getNowPlaying()
    suspend fun getUpcoming() = apiService.getUpcoming()
}