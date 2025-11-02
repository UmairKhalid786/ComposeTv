package com.techlads.composetv.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techlads.auth.UserSession
import com.techlads.composetv.features.home.carousel.CardPayload
import com.techlads.composetv.features.home.carousel.CarouselItemPayload
import com.techlads.composetv.features.home.carousel.HomeCarouselState
import com.techlads.composetv.features.home.hero.HeroItemState
import com.techlads.content.data.MoviesRepository
import com.techlads.content.data.MoviesResponse
import com.techlads.network.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MoviesRepository,
    userSession: UserSession
) : ViewModel() {
    val userState = userSession.authState

    private val _homeItems: MutableStateFlow<HeroItemState> = MutableStateFlow(
        HeroItemState(
            list = listOf()
        )
    )
    val heroItemState: StateFlow<HeroItemState> = _homeItems.asStateFlow()

    private val _homeState = MutableStateFlow(HomeCarouselState(emptyList()))
    val homeState: StateFlow<HomeCarouselState> get() = _homeState.asStateFlow()

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            try {
                handleMoviesResponse(
                    title = "now playing",
                    result = async { repo.getNowPlaying() }.await()
                )
                handleMoviesResponse(
                    title = "popular",
                    result = async { repo.getPopularMovies() }.await()
                )
                handleMoviesResponse(
                    title = "top rated",
                    result = async { repo.getTopRatedMovies() }.await()
                )
                handleMoviesResponse(
                    title = "upcoming",
                    result = async { repo.getUpcoming() }.await()
                )
            } catch (e: Exception) {
                // Handle error
                throw e
            }
        }
    }

    private fun CoroutineScope.handleMoviesResponse(
        title: String,
        result: ApiResult<MoviesResponse>
    ) {
        when (result) {
            is ApiResult.Success -> _homeState.update {
                it.copy(
                    it.items + CarouselItemPayload(
                        id = title,
                        title = "${title.capitalize(Locale.ROOT)} Videos",
                        type = title,
                        items = result.data.results.map {
                            CardPayload(
                                id = it.id.toString(),
                                title = it.title,
                                image = if (it.backdropPath.startsWith("https")) it.backdropPath else "https://image.tmdb.org/t/p/w500" + it.backdropPath,
                                promo = null
                            )
                        }
                    )
                )
            }

            is ApiResult.Error -> {
                // Handle error
            }
        }
    }
}


sealed class NavigationEvent {
    data object None : NavigationEvent()
    data object TopBar : NavigationEvent()
    data object LeftMenu : NavigationEvent()
}