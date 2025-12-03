@file:OptIn(ExperimentalComposeUiApi::class)

package com.techlads.composetv.features.movies

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.composetv.features.home.carousel.VerticalCarouselItem

@Composable
fun MoviesScreen(onItemFocus: (parent: String, child: String) -> Unit) {
    MoviesGrid(Modifier, onItemFocus)
}

@Composable
fun MoviesGrid(modifier: Modifier, onItemFocus: (parent: String, child: String) -> Unit) {
    LazyVerticalGrid(
        modifier = modifier.focusRestorer(),
        columns = GridCells.Fixed(5),
        contentPadding = PaddingValues(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 48.dp),
    ) {
        item(span = {
            GridItemSpan(5)
        }) {
            GridHeader()
        }
        items(30) {
            VerticalCarouselItem(parent = "0", child = "0", onItemFocus)
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
