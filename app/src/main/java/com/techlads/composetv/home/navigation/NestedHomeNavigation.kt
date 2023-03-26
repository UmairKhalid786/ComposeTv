package com.techlads.composetv.home.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import com.techlads.composetv.hero.HeroItem

@Composable
fun NestedHomeNavigation() {
    Column(Modifier.fillMaxSize()) {
        HeroItem()
        HomeCarousel(Modifier.weight(1f))
    }
}

@Composable
fun HomeCarousel(modifier: Modifier) {
    TvLazyColumn(modifier) {
        items(15) {
            HorizontalCarouselItem(it)
        }
    }
}

@Composable
fun HorizontalCarouselItem(index: Int) {
    Column(Modifier.height(150.dp)) {
        Text(text = "Row $index", modifier = Modifier.padding(horizontal = 24.dp))
        TvLazyRow(
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 8.dp,
                bottom = 8.dp,
                end = 100.dp
            )
        ) {
            items(15) {
                CarouselItem(index, it)
            }
        }
    }
}

@Composable
fun CarouselItem(parent: Int, child: Int) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxHeight()
            .aspectRatio(1.8f)
            .clickable { }
            .focusable()
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = "Item $parent x $child", textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun NestedHomeNavigationPrev() {
    NestedHomeNavigation()
}