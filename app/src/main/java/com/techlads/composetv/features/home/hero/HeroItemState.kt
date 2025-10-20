package com.techlads.composetv.features.home.hero

import com.techlads.composetv.features.Movie

data class HeroItemState(
    val list: List<Movie> = emptyList(),
)