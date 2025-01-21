package com.techlads.composetv.features.home.carousel

data class HomeCarouselState(
    val items: List<CarouselItemPayload>,
)

fun HomeCarouselState.findIndexById(parentId: String, childId: String): Pair<Int, Int> {
    val parentIndex = items.indexOfFirst { it.id == parentId }
    val childIndex = items[parentIndex].items.indexOfFirst { it.id == childId }

    return parentIndex to childIndex
}