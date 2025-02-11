@file:OptIn(ExperimentalFoundationApi::class)

package com.techlads.composetv.features.home.carousel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.BringIntoViewSpec
import androidx.compose.foundation.gestures.LocalBringIntoViewSpec
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techlads.composetv.utils.fadingEdge

@Composable
fun HomeCarousel(
    homeState: HomeCarouselState,
    modifier: Modifier,
    onItemFocus: (parentId: String, childId: String) -> Unit,
    onItemClick: (parentId: String, childId: String) -> Unit,
) {
    val topFade by rememberUpdatedState(
        Brush.verticalGradient(
            0f to Color.Transparent,
            0.3f to Color.Black
        )
    )
    val enableFadeEdge = remember { mutableStateOf(false) }

    PositionFocusedItemInLazyLayout(
        parentFraction = 0.25f,
        childFraction = 0.1f,
    ) {
        LazyColumn(
            modifier
                .testTag(SECTIONS_LIST_TAG)
                .then(
                    if (enableFadeEdge.value) {
                        Modifier.fadingEdge(topFade)
                    } else {
                        Modifier
                    }
                ),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            items(homeState.items) {
                HorizontalCarouselItem(it, onItemFocus = { p, c ->
                    onItemFocus(p, c)
                    enableFadeEdge.value = it.items.firstOrNull { it.id == p } != null
                }, onItemClick = onItemClick)
            }
        }
    }
}

@Preview
@Composable
fun HomeCarouselPrev() {
    Column {
        HomeCarousel(homeState = HomeCarouselState(items = (1..20).map { it ->
            CarouselItemPayload(id = it.toString(), title = "Item $it", type = "empty", items = (1..10).map {
                CardPayload(id = it.toString(), title = "Card $it", image = "empty", promo = "")
            })
        }), modifier = Modifier, onItemFocus = { _, _ -> }) { _, _ -> }
    }
}


@Composable
fun PositionFocusedItemInLazyLayout(
    parentFraction: Float = 0.3f,
    childFraction: Float = 0f,
    content: @Composable () -> Unit,
) {
    // a bring into view spec that pivots around the center of the scrollable container
    val bringIntoViewSpec = remember(parentFraction, childFraction) {
        object : BringIntoViewSpec {
            override fun calculateScrollDistance(
                // initial position of item requesting focus
                offset: Float,
                // size of item requesting focus
                size: Float,
                // size of the lazy container
                containerSize: Float,
            ): Float {
                val childSmallerThanParent = size <= containerSize
                val initialTargetForLeadingEdge =
                    parentFraction * containerSize - (childFraction * size)
                val spaceAvailableToShowItem = containerSize - initialTargetForLeadingEdge

                val targetForLeadingEdge =
                    if (childSmallerThanParent && spaceAvailableToShowItem < size) {
                        containerSize - size
                    } else {
                        initialTargetForLeadingEdge
                    }

                return offset - targetForLeadingEdge
            }
        }
    }

    // LocalBringIntoViewSpec will apply to all scrollables in the hierarchy.
    CompositionLocalProvider(
        LocalBringIntoViewSpec provides bringIntoViewSpec,
        content = content,
    )
}
