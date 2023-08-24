package com.techlads.composetv.features.wiw

import androidx.compose.runtime.Composable
import com.techlads.composetv.features.wiw.data.Avatar

@Composable
fun WhoIsWatchingScreen(onProfileSelection: (avatar: Avatar) -> Unit) {
    WhoIsWatchingContent(onProfileSelection)
}
