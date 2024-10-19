package com.techlads.composetv.features.home.carousel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techlads.composetv.utils.fadingEdge

@Composable
fun HomeCarousel(
    modifier: Modifier,
    onItemFocus: (parent: Int, child: Int) -> Unit,
    onItemClick: (child: Int, parent: Int) -> Unit,
) {
    val topFade = Brush.verticalGradient(0f to Color.Transparent, 0.3f to Color.Black)
    val enableFadeEdge = remember { mutableStateOf(false) }

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
        items(15) {
            HorizontalCarouselItem(it, onItemFocus = { p, c ->
                onItemFocus(p, c)
                enableFadeEdge.value = p > 0
            }, onItemClick = onItemClick)
        }
    }
}

@Preview
@Composable
fun HomeCarouselPrev() {
    Column {
        HomeCarousel(Modifier, onItemFocus = { _, _ -> }) { _, _ -> }
    }
}