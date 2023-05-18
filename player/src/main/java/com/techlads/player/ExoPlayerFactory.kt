package com.techlads.player

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.techlads.player.domain.TLPlayer
import com.techlads.player.exoplayer.ExoPlayerImpl
import java.lang.ref.WeakReference

object PlayerFactory {
    fun create(
        context: Context,
    ): TLPlayer = ExoPlayerImpl(WeakReference(context), ExoPlayer.Builder(context).build())
}