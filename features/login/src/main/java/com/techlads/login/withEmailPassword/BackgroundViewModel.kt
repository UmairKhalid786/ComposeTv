package com.techlads.login.withEmailPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techlads.content.data.MoviesRepository
import com.techlads.network.getOrElse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BackgroundViewModel @Inject constructor(
    private val movies: MoviesRepository
) : ViewModel() {

    private val _crossFadeState = MutableStateFlow<CrossFadeState?>(null)
    val crossFadeState: StateFlow<CrossFadeState?> = _crossFadeState.asStateFlow()

    init {
        viewModelScope.launch {
            playDefault()
            _crossFadeState.value = CrossFadeState(
                images = listOf(),
                durationMs = 500f,
                delaySec = 5L
            )
        }
    }

    fun changeBackground(parent: String, child: String) {
        // You can implement logic to change background based on navigation here
    }

    fun play(url: String) {
        // You can implement logic to change background based on navigation here
        _crossFadeState.update {
            it?.copy(images = listOf(url))
        }
    }

    fun play(urls: List<String>) {
        // You can implement logic to change background based on navigation here
        _crossFadeState.update {
            it?.copy(images = urls)
        }
    }

    fun playDefault() {
        viewModelScope.launch {
            val popularMovies = movies.getPopularMovies().getOrElse()?.results?.map { it.backdropPath } ?: emptyList()
            play(popularMovies)
        }
    }
}