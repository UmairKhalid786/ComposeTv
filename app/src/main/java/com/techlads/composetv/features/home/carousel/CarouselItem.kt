package com.techlads.composetv.features.home.carousel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.techlads.composetv.theme.ComposeTvTheme
import com.techlads.uicomponents.widgets.BorderedFocusableItem

@Composable
fun CarouselItem(
    cardPayload: CardPayload,
    modifier: Modifier = Modifier,
    onItemFocus: () -> Unit,
    onItemClick: () -> Unit,
) {
    BorderedFocusableItem(
        onClick = { onItemClick() },
        borderRadius = 12.dp,
        modifier = modifier
            .testTag(cardPayload.id)
            .padding(horizontal = 8.dp)
            .aspectRatio(1.8f)
            .onFocusChanged {
                if (it.isFocused) {
                    onItemFocus()
                }
            },
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = cardPayload.image,
                modifier = Modifier.fillMaxSize(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun VerticalCarouselItem(
    parent: String,
    child: String,
    onItemFocus: (parent: String, child: String) -> Unit
) {
    BorderedFocusableItem(
        onClick = {
            onItemFocus(parent, child)
        },
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(0.6f),
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = "Item $parent x $child", textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun CarouselItemPrev() {
    ComposeTvTheme {
        CarouselItem(CardPayload("1", "Item 1", "empty", ""), onItemFocus = {}, onItemClick = {})
    }
}

@Preview
@Composable
fun VerticalCarouselItemPrev() {
    ComposeTvTheme {
        VerticalCarouselItem("1", "1") { _, _ ->
        }
    }
}
