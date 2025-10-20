package com.techlads.composetv.features.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.composetv.features.home.carousel.VerticalCarouselItem
import com.techlads.composetv.features.keyboard.MiniKeyboard
import com.techlads.composetv.theme.ComposeTvTheme

@Composable
fun SearchScreen() {
    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp)) {
            SearchView()
            MiniKeyboard(modifier = Modifier.width(300.dp))
        }
        ContentGrid()
    }
}

@Composable
fun ContentGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(start = 12.dp, top = 24.dp, end = 12.dp, bottom = 48.dp),
    ) {
        item(span = {
            GridItemSpan(3)
        }) {
            GridHeader()
        }
        items(30) {
            VerticalCarouselItem(parent = "0", child = "0") { a, b -> }
        }
    }
}

@Composable
fun GridHeader() {
    Text(
        text = "Search Results",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 24.dp, start = 8.dp),
    )
}

@Composable
fun SearchView() {
    Column {
        Text(
            text = "Start typing to search",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(all = 12.dp),
        )
        Spacer(modifier = Modifier.height(1.dp).background(MaterialTheme.colorScheme.onSurface))
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun SearchScreenPrev() {
    ComposeTvTheme {
        SearchScreen()
    }
}
