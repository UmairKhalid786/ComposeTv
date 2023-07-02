package com.techlads.exoplayer

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.techlads.player.domain.TLPlayer
import java.lang.ref.WeakReference

object PlayerFactory {
    fun create(
        context: Context,
    ): TLPlayer = ExoPlayerImpl(
        WeakReference(context),
        ExoPlayer.Builder(context).build()
    ) { PlayerView(context) }
}