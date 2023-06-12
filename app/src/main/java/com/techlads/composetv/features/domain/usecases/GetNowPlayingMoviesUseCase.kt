package com.techlads.composetv.features.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.techlads.composetv.features.domain.model.Movie
import com.techlads.composetv.features.domain.paging.NowPlayingMovieSource
import com.techlads.composetv.features.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(private val repository: MoviesRepository) {

    fun invoke(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 24),
            pagingSourceFactory = {
                NowPlayingMovieSource(repository)
            }
        ).flow
    }
}