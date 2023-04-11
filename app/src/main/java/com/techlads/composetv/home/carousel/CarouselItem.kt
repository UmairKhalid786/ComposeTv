package com.techlads.composetv.home.carousel

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
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


@Composable
fun CarouselItem(parent: Int, child: Int, onItemFocus: (item: Int) -> Unit) {

    var isFocused by remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (isFocused) 1.2f else 1f)

    Card(
        modifier = Modifier
            .zIndex(if (isFocused) 20f else 1f)
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value
            )
            .padding(horizontal = 8.dp)
            .fillMaxHeight()
            .aspectRatio(1.8f)
            .onFocusChanged {
                isFocused = it.isFocused
                if (isFocused) {
                    onItemFocus(child)
                }
            }
            .border(
                border = BorderStroke(
                    1.dp,
                    if (isFocused)
                        Color.LightGray
                    else
                        Color.Transparent
                ), shape = MaterialTheme.shapes.medium
            )
            .clickable { }
            .focusable()
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = "Item $parent x $child", textAlign = TextAlign.Center)
        }
    }
}


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
                        Color.LightGray
                    else
                        Color.Transparent
                ), shape = MaterialTheme.shapes.medium
            )
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
fun CarouselItemPrev() {
    CarouselItem(1, 1){}
}


@Preview
@Composable
fun VerticalCarouselItemPrev() {
    VerticalCarouselItem(1, 1)
}
