package com.techlads.composetv.features.favorites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvGridItemSpan
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.material3.Text
import com.techlads.composetv.features.home.carousel.VerticalCarouselItem

@Composable
fun FavoritesScreen() {
    FavoritesGrid(Modifier)
}

@Composable
fun FavoritesGrid(modifier: Modifier) {
    TvLazyVerticalGrid(
        modifier = modifier,
        columns = TvGridCells.Fixed(5),
        contentPadding = PaddingValues(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 48.dp),
    ) {
        item(span = {
            TvGridItemSpan(5)
        }) {
            GridHeader()
        }
        items(30) {
            VerticalCarouselItem(parent = 0, child = 0) { _, _ ->
            }
        }
    }
}

@Composable
fun GridHeader() {
    Text(
        text = "Favorites",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 24.dp, start = 8.dp),
    )
}

@Preview
@Composable
fun FavoritesScreenPrev() {
    FavoritesScreen()
}
