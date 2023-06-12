package com.techlads.composetv.features.movies

import androidx.paging.PagingData
import com.techlads.composetv.features.domain.model.Movie
import kotlinx.coroutines.flow.Flow

data class MoviesScreenState(
    val data: Flow<PagingData<Movie>>? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: String? = null
)