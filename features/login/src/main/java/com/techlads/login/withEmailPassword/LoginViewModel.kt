package com.techlads.login.withEmailPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techlads.content.data.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    movies: MoviesRepository
) : ViewModel() {
    private val _crossFadeState = MutableStateFlow<CrossFadeState?>(null)
    val crossFadeState: StateFlow<CrossFadeState?> = _crossFadeState.asStateFlow()

    init {
        viewModelScope.launch {
//            val popularMovies = movies.getPopularMovies()
//            CrossFadeState(
//                images = movies.getPopularMovies(),
//                durationMs = 1000f,
//                delaySec = 5L
//            )
        }
    }
}