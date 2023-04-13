package com.techlads.composetv.home.carousel

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.tv.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.contentColorFor
import com.techlads.composetv.theme.AppTheme
import com.techlads.composetv.widgets.BorderedFocusableItem


@OptIn(ExperimentalTvMaterial3Api::class)
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


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun VerticalCarouselItem(parent: Int, child: Int) {

    var isFocused by remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (isFocused) 1.05f else 1f)


    Card(
        modifier = Modifier
            .zIndex(if (isFocused) 20f else 1f)
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value
            )
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(0.6f)
            .onFocusChanged {
                isFocused = it.isFocused
            }
            .border(
                border = BorderStroke(
                    1.dp,
                    if (isFocused)
                        contentColorFor(backgroundColor = AppTheme.surface)
                    else
                        Color.Transparent
                ), shape = MaterialTheme.shapes.medium
            )
            .clickable { }
            .focusable(),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.surface,
            contentColor = contentColorFor(backgroundColor = AppTheme.surface)
        )
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Item $parent x $child",
                textAlign = TextAlign.Center,
            )
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
