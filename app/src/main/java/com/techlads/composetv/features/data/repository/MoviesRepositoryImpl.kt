package com.techlads.composetv.features.data.repository


import com.techlads.composetv.features.domain.Resource
import com.techlads.composetv.features.domain.Resource.Companion.loading
import com.techlads.composetv.features.domain.datasource.network.MoviesApi
import com.techlads.composetv.features.domain.model.MovieDetails
import com.techlads.composetv.features.domain.model.MoviesResponse
import com.techlads.composetv.features.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val api: MoviesApi): MoviesRepository {
    override suspend fun getNowPlayingMovies(page: Int): MoviesResponse =
        api.getNowPlayingMovies(page)

    override suspend fun getMovieDetails(id: Int): Resource<MovieDetails> =
        api.getMovieDetails(id)
}
