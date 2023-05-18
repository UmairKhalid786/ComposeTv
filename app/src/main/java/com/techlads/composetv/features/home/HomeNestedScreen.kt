package com.techlads.composetv.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.techlads.composetv.features.home.hero.HeroItem
import com.techlads.composetv.features.home.carousel.HomeCarousel

@Composable
fun HomeNestedScreen(
    onItemFocus: (parent: Int, child: Int) -> Unit,
    onItemClick: (parent: Int, child: Int) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        HeroItem()
        HomeCarousel(Modifier.weight(1f), onItemFocus = onItemFocus, onItemClick = onItemClick)
    }
}