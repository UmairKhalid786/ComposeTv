package com.techlads.exoplayer

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.techlads.player.domain.TLPlayer
import java.lang.ref.WeakReference

object PlayerFactory {

    fun create(
        context: Context
    ): TLPlayer {
        val exoPlayer = ExoPlayer.Builder(context).build()
        return ExoPlayerImpl(
            WeakReference(context),
            exoPlayer
        ) {
            PlayerView(context).apply {
                hideController()
                player = exoPlayer
                useController = false
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            }
        }
    }
}