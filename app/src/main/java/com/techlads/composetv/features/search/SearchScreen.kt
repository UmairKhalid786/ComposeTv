package com.techlads.composetv.features.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techlads.composetv.features.keyboard.MiniKeyboard
import com.techlads.composetv.theme.ComposeTvTheme

@Composable
fun SearchScreen() {
    Row(modifier = Modifier.fillMaxSize()) {
        MiniKeyboard(modifier = Modifier.width(400.dp))
    }
}


@Preview(device = Devices.TV_1080p)
@Composable
fun SearchScreenPrev() {
    ComposeTvTheme {
        SearchScreen()
    }
}