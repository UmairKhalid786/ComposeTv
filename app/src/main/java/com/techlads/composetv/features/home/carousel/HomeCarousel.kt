package com.techlads.composetv.features.home.carousel

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn

@Composable
fun HomeCarousel(modifier: Modifier) {
    TvLazyColumn(modifier, contentPadding = PaddingValues(bottom = 100.dp)) {
        items(15) {
            HorizontalCarouselItem(it){ child, parent ->
                
            }
        }
    }
}

@Preview
@Composable
fun HomeCarouselPrev() {
    HomeCarousel(Modifier)
}