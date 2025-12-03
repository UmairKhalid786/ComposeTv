package com.techlads.content.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val id: Int,
    val title: String,
    val adult: Boolean,
    val budget: Int,
    val overview: String,
    val genre: List<GenreDto>,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("backdrop_path") val backdropPath: String,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("spoken_languages") val spokenLanguages: List<LanguageDto>,
)

@Serializable
data class GenreDto(
    val id: Int,
    val name: String,
)

@Serializable
data class LanguageDto(
    @SerialName("english_name") val englishName: String,
    @SerialName("iso_639_1") val iso: String,
    val name: String,
)