package com.techlads.composetv.features.home.carousel

data class CarouselItemPayload(
    val id: String,
    val title: String,
    val type: String,
    val items: List<CardPayload>,
)