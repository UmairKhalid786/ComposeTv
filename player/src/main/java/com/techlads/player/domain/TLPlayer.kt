package com.techlads.player.domain

import androidx.media3.exoplayer.ExoPlayer
import com.techlads.player.domain.state.PlayerStateListener

interface TLPlayer {
    fun play()
    fun pause()
    fun stop()

    fun seekTo(positionMs: Long)
    fun seekForward()
    fun seekBack()

    fun prepare(uri: String, playWhenReady: Boolean)
    fun release()

    fun exoPlayer(): ExoPlayer

    fun setPlaybackEvent(callback: PlayerStateListener)
    fun removePlaybackEvent(callback: PlayerStateListener)
}