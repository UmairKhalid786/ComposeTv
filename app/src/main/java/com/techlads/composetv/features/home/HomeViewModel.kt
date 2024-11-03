package com.techlads.composetv.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techlads.composetv.features.home.leftmenu.data.MenuData
import com.techlads.composetv.features.home.leftmenu.model.MenuItem
import com.techlads.composetv.features.home.network.TmdbApiService
import com.techlads.composetv.features.home.network.data.Movie
import com.techlads.composetv.utils.toMutable
import com.techlads.network.di.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: TmdbApiService
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies.asStateFlow()

    fun fetchPopularMovies() {
        viewModelScope.launch {
            try {
                when (val response = api.getPopularMovies()) {
                    is ApiResult.Success -> _movies.value = response.data.results
                    is ApiResult.Error -> {
                        // Handle error
                    }
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    val menuItems: StateFlow<List<MenuItem>> = MutableStateFlow(emptyList())
    val menuState: StateFlow<Boolean> = MutableStateFlow(false)
    private val _usedTopBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val usedTopBar: StateFlow<Boolean> = _usedTopBar

    init {
        menuItems.toMutable().value = MenuData.menuItems
        fetchPopularMovies()
    }

    fun menuClosed() {
        menuState.toMutable().value = false
    }

    fun menuOpen() {
        menuState.toMutable().value = true
    }

    fun toggleTopBar(){
        _usedTopBar.value = !_usedTopBar.value
    }
}
