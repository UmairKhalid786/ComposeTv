package com.techlads.composetv.features.mp3.player

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun Mp3PlayerScreen() {
    Mp3PlayerScreenContent(modifier = Modifier.fillMaxSize())
}

@Preview(device = Devices.TV_1080p)
@Composable
private fun Mp3PlayerScreenPrev() {
    Mp3PlayerScreen()
}