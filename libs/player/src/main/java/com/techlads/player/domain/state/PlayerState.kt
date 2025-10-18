package com.techlads.player.domain.state

sealed class PlayerState {
    object Playing : PlayerState()
    object Pause : PlayerState()
    object Stop : PlayerState()
    object Idle : PlayerState()
    object Buffering : PlayerState()
    object Complete : PlayerState()

    class SeekStart(position: Long) : PlayerState()
    class SeekEnd(position: Long) : PlayerState()
}