package com.techlads.composetv.movies

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.tv.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvGridItemSpan
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import com.techlads.composetv.home.carousel.VerticalCarouselItem

@Composable
fun MoviesScreen() {
    MoviesGrid(Modifier)
}

@Composable
fun MoviesGrid(modifier: Modifier) {
    TvLazyVerticalGrid(
        modifier = modifier,
        columns = TvGridCells.Fixed(5),
        contentPadding = PaddingValues(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 48.dp)
    ) {
        item(span = {
            TvGridItemSpan(5)
        }) {
            GridHeader()
        }
        items(30) {
            VerticalCarouselItem(parent = 0, child = 0)
        }
    }
}

@Composable
fun GridHeader() {
    Text(
        text = "Movies",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 24.dp, start = 8.dp)
    )
}


@Preview
@Composable
fun MoviesScreenPrev() {
    MoviesScreen()
}