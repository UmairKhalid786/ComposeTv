package com.techlads.content.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieVideosResponse(
    val id: Int, val results: List<Video>
)

@Serializable
data class Video(
    val name: String,
    val size: Int,
    val site: String,
    val type: String,
    val key: String,
    val official: Boolean,
    @SerialName("published_at") val publishedAt: String,
    @SerialName("iso_639_1") val iso639: String,
    @SerialName("iso_3166_1") val iso3166: String,
)