package com.techlads.content.data

import kotlinx.serialization.Serializable
import javax.inject.Named

@Serializable
data class CreditsResponse(
    val id: Int,
    val cast: List<Cast>,
)

@Serializable
data class Cast(
    val id: Int,
    val name: String,
    @Named("profile_path")
    val profilePath: String
)