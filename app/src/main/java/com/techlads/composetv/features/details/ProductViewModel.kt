package com.techlads.composetv.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techlads.content.data.MovieResponse
import com.techlads.content.data.MovieVideosResponse
import com.techlads.content.data.MoviesRepository
import com.techlads.network.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val repo: MoviesRepository
) : ViewModel() {

    private val _details: MutableStateFlow<ProductDetailsState?> =
        MutableStateFlow<ProductDetailsState?>(null)
    val details = _details.asStateFlow()

    private val _videos: MutableStateFlow<ProductVideosState?> =
        MutableStateFlow<ProductVideosState?>(null)
    val videos = _videos.asStateFlow()

    init {
        viewModelScope.launch {
            savedStateHandle.getStateFlow<String?>("id", null).collect {
                Timber.tag("ProductViewModel.ID").e(it.toString())
                it?.let {
                    async { getMovieDetails(it.toInt()) }.await()
                    async { getMovieVideos(it.toInt()) }.await()
                }
            }
        }
    }

    private suspend fun getMovieDetails(id: Int) {
        _details.value = ProductDetailsState.Loading
        _details.value = when (val response = repo.getMovieDetail(id)) {
            is ApiResult.Success -> {
                ProductDetailsState.Success(response.data.toUIDetails())
            }

            is ApiResult.Error -> ProductDetailsState.Error("Something went wrong !!")
        }
    }

    private suspend fun getMovieVideos(id: Int) {
        _videos.value = ProductVideosState.Loading
        _videos.value = when (val response = repo.getMovieVideos(id)) {
            is ApiResult.Success -> {
                ProductVideosState.Success(response.data.toUiVideos().also { it.videos.forEach {
                    Timber.tag("ApiResult").e(it.toString())
                } })
            }

            is ApiResult.Error -> ProductVideosState.Error("Something went wrong !!")
        }
    }
}

sealed class ProductDetailsState {
    data object Loading : ProductDetailsState()
    data class Success(val details: Details) : ProductDetailsState()
    data class Error(val message: String) : ProductDetailsState()
}

sealed class ProductVideosState {
    data object Loading : ProductVideosState()
    data class Success(val details: Videos) : ProductVideosState()
    data class Error(val message: String) : ProductVideosState()
}

data class Details(
    val title: String,
    val background: String,
    val description: String,
    val releaseDate: String,
    val genres: List<String>
)

data class Videos(val videos: List<Video>) {
    data class Video(
        val name: String,
        val size: Int,
        val site: String,
        val type: String,
        val key: String,
        val official: Boolean,
        val publishedAt: String,
        val iso639: String,
        val iso3166: String,
    )
}

fun MovieResponse.toUIDetails() = Details(
    title = title,
    background = backdropPath,
    description = overview,
    releaseDate = releaseDate,
    genres = genres.map { it.name })

fun MovieVideosResponse.toUiVideos() = Videos(
    videos = results.map {
        Videos.Video(
            name = it.name,
            size = it.size,
            site = it.site,
            type = it.type,
            key = it.key,
            official = it.official,
            publishedAt = it.publishedAt,
            iso639 = it.iso639,
            iso3166 = it.iso639,
        )
    })