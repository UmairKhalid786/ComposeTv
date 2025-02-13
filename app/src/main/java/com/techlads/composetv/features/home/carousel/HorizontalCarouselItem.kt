@file:OptIn(ExperimentalComposeUiApi::class)

package com.techlads.composetv.features.home.carousel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text

@Composable
fun HorizontalCarouselItem(
    parent: CarouselItemPayload,
    onItemFocus: (parentId: String, childId: String) -> Unit,
    onItemClick: (parentId: String, childId: String) -> Unit,
) {
    Column(
        Modifier
            .height(150.dp)
            .padding(top = 16.dp)
    ) {
        Text(text = parent.title, modifier = Modifier.padding(horizontal = 52.dp, vertical = 4.dp))
        PositionFocusedItemInLazyLayout(
            parentFraction = 0.1f,
            childFraction = 0.1f,
        ) {
            LazyRow(
                modifier = Modifier.focusRestorer(),
                contentPadding = PaddingValues(
                    start = 42.dp,
                    top = 8.dp,
                    bottom = 8.dp,
                    end = 100.dp,
                ),
            ) {
                items(parent.items) { child ->
                    CarouselItem(
                        modifier = Modifier,
                        cardPayload = child,
                        onItemClick = { onItemClick(parent.id, child.id) },
                        onItemFocus = { onItemFocus(parent.id, child.id) },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HorizontalCarouselItemPrev() {
    HorizontalCarouselItem(
        parent = CarouselItemPayload(
            id = "1",
            title = "Title",
            type = "Type",
            items = List(10) {
                CardPayload(
                    id = "abc$it", title = "Card $it", image = "empty", promo = ""
                )
            },
        ),
        onItemFocus = { _, _ -> },
        onItemClick = { _, _ -> },
    )
}
