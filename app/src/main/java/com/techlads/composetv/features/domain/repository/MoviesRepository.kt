package com.techlads.composetv.features.domain.repository

import com.techlads.composetv.features.domain.Resource
import com.techlads.composetv.features.domain.model.MovieDetails
import com.techlads.composetv.features.domain.model.MoviesResponse

interface MoviesRepository {
    suspend fun getNowPlayingMovies(page: Int): MoviesResponse
    suspend fun getMovieDetails(id: Int): Resource<MovieDetails>
}