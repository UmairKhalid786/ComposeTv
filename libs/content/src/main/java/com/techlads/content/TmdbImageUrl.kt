package com.techlads.content

private const val TMDB_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

fun String?.toTmdbImageUrl(): String? {
    val path = this?.trim().orEmpty()
    return when {
        path.isEmpty() -> null
        path.startsWith("http://", ignoreCase = true) -> path
        path.startsWith("https://", ignoreCase = true) -> path
        else -> "$TMDB_IMAGE_BASE_URL$path"
    }
}
