package com.techlads.composetv.features.player

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.techlads.composetv.features.player.controls.PlayerControls
import com.techlads.composetv.features.player.controls.rememberVideoPlayerState
import com.techlads.composetv.utils.handleDPadKeyEvents
import com.techlads.exoplayer.PlayerFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PlayerScreen(mediaUrl: String, onBackPressed: () -> Unit) {
    PlayerScreenContent(Modifier, mediaUrl, onBackPressed)
}

@SuppressLint("UnsafeOptInUsageError")
@Composable
fun PlayerScreenContent(modifier: Modifier, mediaUrl: String, onBackPressed: () -> Unit) {
    val context = LocalContext.current

    val player = remember {
        PlayerFactory.create(context)
    }

    val coroutineScope = rememberCoroutineScope()
    var contentCurrentPosition: Long by remember { mutableLongStateOf(0L) }
    val videoPlayerState = rememberVideoPlayerState(hideSeconds = 4, coroutineScope)

    BackHandler(onBack = onBackPressed)

    LaunchedEffect(Unit) {
        player.prepare(mediaUrl, true)
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(300)
            contentCurrentPosition = player.currentPosition
        }
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AndroidView(
            modifier = Modifier
                .handleDPadKeyEvents(
                    onEnter = {
                        if (!videoPlayerState.isDisplayed) {
                            coroutineScope.launch {
                                videoPlayerState.showControls()
                            }
                        }
                    },
                )
                .focusable(),
            factory = {
                player.getView()
            },
        )
        DisposableEffect(Unit) {
            onDispose { player.release() }
        }
        PlayerControls(
            modifier = Modifier.align(Alignment.BottomCenter),
            isPlaying = player.isPlaying,
            onPlayPauseToggle = { shouldPlay ->
                if (shouldPlay) {
                    player.play()
                } else {
                    player.pause()
                }
            },
            contentProgressInMillis = contentCurrentPosition,
            contentDurationInMillis = player.duration,
            state = videoPlayerState,
            onSeek = { seekProgress ->
                player.seekTo(player.duration.times(seekProgress).toLong())
            },
        )
    }
}

@Preview
@Composable
private fun PlayerScreenPreview() {
    PlayerScreen("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4") {
    }
}
