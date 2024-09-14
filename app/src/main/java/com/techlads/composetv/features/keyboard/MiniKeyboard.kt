@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.features.keyboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SpaceBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.techlads.composetv.theme.ComposeTvTheme

@Composable
fun MiniKeyboard(modifier: Modifier) {
    var sizeInDp by remember { mutableStateOf(DpSize.Zero) }
    val density = LocalDensity.current

    val extrasHeight by remember {
        derivedStateOf {
            sizeInDp.width / 7
        }
    }

    Row(modifier = Modifier.padding(8.dp)) {
        LazyVerticalGrid(
            modifier = modifier
                .onSizeChanged {
                    sizeInDp = density.run {
                        DpSize(
                            it.width.toDp(),
                            it.height.toDp(),
                        )
                    }
                },
            columns = GridCells.Fixed(7),
        ) {
            items(KeysGenerator.alphabet.value + KeysGenerator.specialCharV1.value) {
                KeyItem(key = it)
            }
            item(span = { GridItemSpan(2) }) {
                KeyItem(modifier = Modifier.aspectRatio(2f)) {
                    Icon(imageVector = Icons.Default.SpaceBar, contentDescription = "Backspace")
                }
            }
            item(span = { GridItemSpan(2) }) {
                KeyItem(modifier = Modifier.aspectRatio(2f)) {
                    Icon(
                        imageVector = Icons.Default.KeyboardBackspace,
                        contentDescription = "Backspace",
                    )
                }
            }
            item(span = { GridItemSpan(2) }) {
                KeyItem(modifier = Modifier.aspectRatio(2f)) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                    )
                }
            }
        }
        LazyColumn {
            item {
                KeyItem(
                    modifier = Modifier
                        .width(extrasHeight * 1.5f)
                        .height(extrasHeight),
                ) {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        contentDescription = "Search",
                    )
                }
            }
            item {
                KeyItem(
                    modifier = Modifier
                        .width(extrasHeight * 1.5f)
                        .height(extrasHeight),
                ) {
                    Text(text = "&123")
                }
            }
        }
    }
}

@Composable
fun KeyItem(key: Any, modifier: Modifier = Modifier) {
    KeyItem(modifier = modifier.aspectRatio(1f)) {
        Text(text = key.toString())
    }
}

@Composable
fun KeyItem(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier.padding(3.dp),
        onClick = {},
        shape = ClickableSurfaceDefaults.shape(shape = MaterialTheme.shapes.small),
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}

@Preview
@Composable
fun MiniKeyboardPrev() {
    ComposeTvTheme {
        MiniKeyboard(modifier = Modifier.size(400.dp))
    }
}
