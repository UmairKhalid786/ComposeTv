package com.techlads.composetv.features.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.techlads.composetv.features.domain.Resource
import com.techlads.composetv.features.domain.model.MovieDetails
import com.techlads.composetv.features.domain.usecases.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val useCase: MoviesUseCase): ViewModel() {

    private val _moviesState = mutableStateOf(MoviesScreenState())
    val moviesState: State<MoviesScreenState> = _moviesState

    private val _movieDetails = mutableStateOf<Resource<MovieDetails>?>(null)
    val movieDetails: State<Resource<MovieDetails>?> = _movieDetails


    private val _movieId = mutableStateOf(0)
    val movieId: State<Int> = _movieId

    fun setMovieId(id: Int) {
            _movieId.value = id
    }

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            _moviesState.value = MoviesScreenState(
                data = useCase.getNowPlayingMoviesUseCase.invoke().cachedIn(viewModelScope)
            )
        }
    }

    fun getMovieById() {
        if (movieId.value != 0) {
            viewModelScope.launch {
                _movieDetails.value = useCase.getMovieDetails.invoke(movieId.value)
            }
        }
    }

}