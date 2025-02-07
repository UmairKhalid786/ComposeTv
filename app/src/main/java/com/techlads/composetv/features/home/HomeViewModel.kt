package com.techlads.composetv.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techlads.composetv.features.home.carousel.HomeCarouselState
import com.techlads.composetv.features.home.leftmenu.data.MenuData
import com.techlads.composetv.features.home.leftmenu.model.MenuItem
import com.techlads.composetv.features.home.network.TmdbApiService
import com.techlads.composetv.features.home.network.data.Movie
import com.techlads.composetv.utils.toMutable
import com.techlads.network.ApiResult
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

    val menuState: StateFlow<Boolean> = MutableStateFlow(false)

    private val _menuItems = MutableStateFlow<List<MenuItem>>(emptyList())
    val menuItems: StateFlow<List<MenuItem>> = _menuItems.asStateFlow()

    private val _usedTopBar = MutableStateFlow<NavigationEvent>(NavigationEvent.TopBar)
    val usedTopBar: StateFlow<NavigationEvent> = _usedTopBar.asStateFlow()

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies.asStateFlow()

    private val _homeState = MutableStateFlow<HomeCarouselState>(HomeCarouselState(emptyList()))
    val homeState: StateFlow<HomeCarouselState> get() = _homeState.asStateFlow()

    init {
        _menuItems.value = MenuData.menuItems
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
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

    fun menuClosed() {
        menuState.toMutable().value = false
    }

    fun menuOpen() {
        menuState.toMutable().value = true
    }

    fun updateMenu(menu: NavigationEvent){
        _usedTopBar.value = menu
    }
}


sealed class NavigationEvent {
    data object None : NavigationEvent()
    data object TopBar : NavigationEvent()
    data object LeftMenu : NavigationEvent()
}