@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.features.movies

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
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.techlads.composetv.features.home.carousel.VerticalCarouselItem

@Composable
fun MoviesScreen(onItemFocus: (parent: Int, child: Int) -> Unit) {
    MoviesGrid(Modifier, onItemFocus)
}

@Composable
fun MoviesGrid(modifier: Modifier, onItemFocus: (parent: Int, child: Int) -> Unit) {
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
            VerticalCarouselItem(parent = 0, child = 0, onItemFocus)
        }
    }
}

@Composable
fun GridHeader() {
    Text(
        text = "Movies",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 24.dp, start = 8.dp),
    )
}

@Preview(device = "id:tv_1080p")
@Composable
fun MoviesScreenPrev() {
    MoviesScreen { _, _ -> }
}
