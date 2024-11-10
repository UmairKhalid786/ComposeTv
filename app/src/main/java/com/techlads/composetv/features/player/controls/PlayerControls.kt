package com.techlads.composetv.features.player.controls

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import com.techlads.composetv.R

@Composable
fun PlayerControls(
    modifier: Modifier = Modifier,
    state: PlayerControlsState = rememberVideoPlayerState(coroutineScope = rememberCoroutineScope()),
    isPlaying: Boolean,
    onPlayPauseToggle: (Boolean) -> Unit,
    onSeek: (seekProgress: Float) -> Unit,
    contentProgressInMillis: Long,
    contentDurationInMillis: Long,
) {
    val focusRequester = remember { FocusRequester() }

    val contentProgress by remember(contentProgressInMillis, contentDurationInMillis) {
        derivedStateOf {
            contentProgressInMillis.toFloat() / contentDurationInMillis
        }
    }

    val contentProgressString by remember(contentProgressInMillis) {
        derivedStateOf {
            val contentProgressMinutes = (contentProgressInMillis / 1000) / 60
            val contentProgressSeconds = (contentProgressInMillis / 1000) % 60
            val contentProgressMinutesStr =
                if (contentProgressMinutes < 10) {
                    contentProgressMinutes.padStartWith0()
                } else {
                    contentProgressMinutes.toString()
                }
            val contentProgressSecondsStr =
                if (contentProgressSeconds < 10) {
                    contentProgressSeconds.padStartWith0()
                } else {
                    contentProgressSeconds.toString()
                }
            "$contentProgressMinutesStr:$contentProgressSecondsStr"
        }
    }

    val contentDurationString by remember(contentDurationInMillis) {
        derivedStateOf {
            val contentDurationMinutes =
                (contentDurationInMillis / 1000 / 60).coerceAtLeast(minimumValue = 0)
            val contentDurationSeconds =
                (contentDurationInMillis / 1000 % 60).coerceAtLeast(minimumValue = 0)
            val contentDurationMinutesStr =
                if (contentDurationMinutes < 10) {
                    contentDurationMinutes.padStartWith0()
                } else {
                    contentDurationMinutes.toString()
                }
            val contentDurationSecondsStr =
                if (contentDurationSeconds < 10) {
                    contentDurationSeconds.padStartWith0()
                } else {
                    contentDurationSeconds.toString()
                }
            "$contentDurationMinutesStr:$contentDurationSecondsStr"
        }
    }

    LaunchedEffect(state.isDisplayed) {
        if (state.isDisplayed) {
            focusRequester.requestFocus()
        }
    }

    LaunchedEffect(isPlaying) {
        if (!isPlaying) {
            state.showControls(seconds = Int.MAX_VALUE)
        } else {
            state.showControls()
        }
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = state.isDisplayed,
        enter = expandVertically { it },
        exit = shrinkVertically { it },
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black,
                            Color.Transparent,
                            Color.Black,
                        ),
                    ),
                )
                .padding(
                    horizontal = 56.dp,
                    vertical = 32.dp,
                ),
        ) {
            VideoHeaders()
            Spacer(modifier = Modifier.weight(1.0f))
            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                VideoPlayerControlsIcon(
                    icon = R.drawable.ic_auto_awesome_motion,
                    state = state,
                    isPlaying = isPlaying,
                    contentDescription = "Playlist",
                )
                VideoPlayerControlsIcon(
                    modifier = Modifier.padding(start = 12.dp),
                    icon = R.drawable.ic_cc,
                    state = state,
                    isPlaying = isPlaying,
                    contentDescription = "Closed Captions Icon",
                )
                VideoPlayerControlsIcon(
                    modifier = Modifier.padding(start = 12.dp),
                    icon = R.drawable.ic_settings,
                    state = state,
                    isPlaying = isPlaying,
                    contentDescription = "Settings Icon",
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                VideoPlayerControlsIcon(
                    modifier = Modifier.focusRequester(focusRequester),
                    icon = if (!isPlaying) R.drawable.ic_play else R.drawable.ic_pause,
                    onClick = { onPlayPauseToggle(!isPlaying) },
                    state = state,
                    isPlaying = isPlaying,
                    contentDescription = "Play arrow",
                )
                ControllerText(text = contentProgressString)
                VideoPlayerControllerIndicator(
                    progress = contentProgress,
                    onSeek = onSeek,
                    state = state,
                )
                ControllerText(text = contentDurationString)
            }
        }
    }
}

@Composable
fun VideoHeaders() {
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = "This is Dummy video title",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "This is Dummy video description | acting as placeholder for details",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

private fun Long.padStartWith0() = this.toString().padStart(2, '0')

@Preview
@Composable
private fun PlayerControlsPrev() {
    PlayerControls(
        modifier = Modifier.fillMaxSize(),
        isPlaying = false,
        onPlayPauseToggle = {},
        onSeek = {},
        contentProgressInMillis = 0,
        contentDurationInMillis = 0,
    )
}

@Preview
@Composable
fun VideoHeadersPrev() {
    VideoHeaders()
}
