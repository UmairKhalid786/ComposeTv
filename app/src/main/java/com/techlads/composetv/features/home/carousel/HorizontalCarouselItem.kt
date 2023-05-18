package com.techlads.composetv.features.home.carousel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.tv.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyRow

@Composable
fun HorizontalCarouselItem(parent: Int, onItemFocus: (child: Int, parent: Int) -> Unit) {

    Column(Modifier.height(150.dp)) {
        Text(text = "Row $parent", modifier = Modifier.padding(horizontal = 24.dp))
        TvLazyRow(
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 8.dp,
                bottom = 8.dp,
                end = 100.dp
            )
        ) {
            items(15) { child ->
                CarouselItem(parent, child) {
                    onItemFocus(child, parent)
                }
            }
        }
    }
}

@Preview
@Composable
fun HorizontalCarouselItemPrev() {
    HorizontalCarouselItem(1) { child, parent ->

    }
}