package com.techlads.player.domain

import android.view.View
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
    fun getView(): View

    val currentPosition: Long

    fun setPlaybackEvent(callback: PlayerStateListener)
    fun removePlaybackEvent(callback: PlayerStateListener)
}