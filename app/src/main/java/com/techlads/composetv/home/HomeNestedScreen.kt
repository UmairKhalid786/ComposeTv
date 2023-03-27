package com.techlads.composetv.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.techlads.composetv.hero.HeroItem
import com.techlads.composetv.home.carousel.HomeCarousel

@Composable
fun HomeNestedScreen(){
    Column(Modifier.fillMaxSize()) {
        HeroItem()
        HomeCarousel(Modifier.weight(1f))
    }
}