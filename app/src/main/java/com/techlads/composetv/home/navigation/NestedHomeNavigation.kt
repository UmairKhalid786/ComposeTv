package com.techlads.composetv.home.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.techlads.composetv.hero.HeroItem
import com.techlads.composetv.home.carousel.HomeCarousel

@Composable
fun NestedHomeNavigation() {
    Column(Modifier.fillMaxSize()) {
        HeroItem()
        HomeCarousel(Modifier.weight(1f))
    }
}

@Preview
@Composable
fun NestedHomeNavigationPrev() {
    NestedHomeNavigation()
}