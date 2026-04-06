package com.techlads.composetv.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techlads.composetv.features.Movie
import com.techlads.composetv.features.home.carousel.CardPayload
import com.techlads.composetv.features.home.carousel.CarouselItemPayload
import com.techlads.composetv.features.home.carousel.HomeCarouselState
import com.techlads.composetv.features.home.hero.HeroItemState
import com.techlads.content.data.MovieDto
import com.techlads.content.data.MoviesRepository
import com.techlads.content.data.MoviesResponse
import com.techlads.content.toTmdbImageUrl
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
) : ViewModel() {
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
        fetchHeroItems()
    }

    private fun fetchHeroItems() {
        viewModelScope.launch {
            when (val result = repo.getPopularMovies()) {
                is ApiResult.Success -> {
                    val heroItems = result.data.results.take(5).map {
                        Movie(
                            title = it.title,
                            imageUrl = it.displayImageUrl(),
                            metadata = "2023  •  1h 45m  •  Action, Adventure",
                            details = it.overview
                        )
                    }
                    _homeItems.update {
                        it.copy(
                            list = heroItems
                        )
                    }
                }

                is ApiResult.Error -> {
                    // Handle error
                }
            }
        }
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
                                image = it.displayImageUrl(),
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

private fun MovieDto.displayImageUrl(): String =
    backdropPath.toTmdbImageUrl() ?: posterPath.toTmdbImageUrl().orEmpty()
