@file:OptIn(ExperimentalComposeUiApi::class)

package com.techlads.composetv.features.home.carousel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text

@Composable
fun HorizontalCarouselItem(
    parent: CarouselItemPayload,
    focusedParentId: String,
    focusedChildId: String,
    focusedChildIndex: Int,
    restoreFocusVersion: Int,
    onItemFocus: (parentId: String, childId: String) -> Unit,
    onItemClick: (parentId: String, childId: String) -> Unit,
) {
    val rowState = rememberSaveable(parent.id, saver = LazyListState.Saver) {
        LazyListState(
            firstVisibleItemIndex = if (parent.id == focusedParentId) focusedChildIndex else 0
        )
    }

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
                state = rowState,
                modifier = Modifier.focusRestorer(),
                contentPadding = PaddingValues(
                    start = 42.dp,
                    top = 8.dp,
                    bottom = 8.dp,
                    end = 100.dp,
                ),
            ) {
                itemsIndexed(parent.items, key = { _, child -> child.id }) { _, child ->
                    CarouselItem(
                        modifier = Modifier,
                        cardPayload = child,
                        shouldRestoreFocus = parent.id == focusedParentId && child.id == focusedChildId,
                        restoreFocusVersion = restoreFocusVersion,
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
        focusedParentId = "",
        focusedChildId = "",
        focusedChildIndex = 0,
        restoreFocusVersion = 0,
        onItemFocus = { _, _ -> },
        onItemClick = { _, _ -> },
    )
}
