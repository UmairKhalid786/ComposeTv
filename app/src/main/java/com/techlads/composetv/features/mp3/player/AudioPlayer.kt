package com.techlads.composetv.features.mp3.player

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun AudioPlayerScreen(onBackPressed: () -> Unit) {
    BackHandler(onBack = onBackPressed)
    AudioPlayerScreenContent(modifier = Modifier.fillMaxSize())
}

@Preview(device = Devices.TV_1080p)
@Composable
private fun AudioPlayerScreenPrev() {
    AudioPlayerScreen {}
}