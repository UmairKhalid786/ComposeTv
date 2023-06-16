package com.techlads.composetv.features.domain.usecases

import com.techlads.composetv.features.domain.Resource
import com.techlads.composetv.features.domain.model.MovieDetails
import com.techlads.composetv.features.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MoviesRepository) {

    suspend fun invoke(id: Int): Resource<MovieDetails> {
        return  try {
            repository.getMovieDetails(id)
        } catch (e: Exception) {
            return Resource.error("Something went wrong, Try again!")
        }
    }
}