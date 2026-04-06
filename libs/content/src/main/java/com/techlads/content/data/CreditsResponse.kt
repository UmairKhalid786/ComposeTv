package com.techlads.content.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    val id: Int,
    val cast: List<Cast>,
)

@Serializable
data class Cast(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val profilePath: String? = null,
)
