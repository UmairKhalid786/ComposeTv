package com.techlads.player.exoplayer

import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_BUFFERING
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.common.Player.STATE_IDLE
import androidx.media3.common.Player.STATE_READY
import androidx.media3.exoplayer.ExoPlayer
import com.techlads.player.domain.state.PlayerState
import com.techlads.player.domain.state.PlayerStateListener

internal class ExoPlayerStateListenerBridge(
    private val stateListener: PlayerStateListener,
    val player: ExoPlayer
) : Player.Listener {

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)

        val state = when (playbackState) {
            STATE_IDLE -> {
                PlayerState.Idle
            }

            STATE_BUFFERING -> {
                PlayerState.Buffering
            }

            STATE_READY -> {
                PlayerState.Idle
            }

            STATE_ENDED -> {
                PlayerState.Complete
            }

            else -> PlayerState.Idle
        }
        stateListener.on(state)
    }
}