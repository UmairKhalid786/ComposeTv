package com.techlads.composetv.features.player

import android.annotation.SuppressLint
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.techlads.player.PlayerFactory
import com.techlads.player.domain.state.PlayerState
import com.techlads.player.domain.state.PlayerStateListener
import timber.log.Timber


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

    val stateListener = remember {
        object : PlayerStateListener {
            override fun on(state: PlayerState) {
                Timber.d("State $state")
            }
        }
    }

    BackHandler(onBack = onBackPressed)

    LaunchedEffect(Unit) {
        player.prepare(mediaUrl, true)
        player.setPlaybackEvent(callback = stateListener)
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        DisposableEffect(
            AndroidView(
                modifier = Modifier.focusable(),
                factory = {
                    PlayerView(context).apply {
                        hideController()
                        useController = false
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT

                        this.player = player.exoPlayer()
                        layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    }
                }
            )
        ) {
            onDispose { player.release() }
        }
    }
}

@UnstableApi
@Preview
@Composable
private fun PlayerScreenPreview() {
    PlayerScreen("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4") {

    }
}