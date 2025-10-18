package com.techlads.player.domain.state

interface PlayerStateListener {
    fun on(state: PlayerState)
}