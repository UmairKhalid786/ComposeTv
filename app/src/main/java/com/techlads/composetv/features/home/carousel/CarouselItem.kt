package com.techlads.composetv.features.home.carousel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text
import com.techlads.composetv.widgets.BorderedFocusableItem

@Composable
fun CarouselItem(parent: Int, child: Int, onItemFocus: (item: Int) -> Unit) {
    BorderedFocusableItem(
        onClick = {  },
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .aspectRatio(1.8f)
            .onFocusChanged {
                if (it.isFocused) {
                    onItemFocus(child)
                }
            }) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = "Item $parent x $child", textAlign = TextAlign.Center)
        }
    }
}


@Composable
fun VerticalCarouselItem(parent: Int, child: Int) {
    BorderedFocusableItem(
        onClick = {  },
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(0.6f)) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = "Item $parent x $child", textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun CarouselItemPrev() {
    CarouselItem(1, 1) {}
}


@Preview
@Composable
fun VerticalCarouselItemPrev() {
    VerticalCarouselItem(1, 1)
}
