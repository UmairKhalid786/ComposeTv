package com.techlads.composetv.features.home.network.data

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val results: List<Movie>
)

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String
)