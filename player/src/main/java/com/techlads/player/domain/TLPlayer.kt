package com.techlads.player.domain

import com.techlads.player.domain.state.PlayerStateListener

interface TLPlayer {
    fun play()
    fun pause()
    fun stop()

    fun seekTo(positionMs: Long)
    fun seekForward()
    fun seekBack()

    fun prepare(uri: String)
    fun release()

    fun setPlaybackEvent(callback: PlayerStateListener)
    fun removePlaybackEvent(callback: PlayerStateListener)
}