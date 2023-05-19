package com.techlads.player.exoplayer

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DefaultDataSource.Factory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.techlads.player.domain.TLPlayer
import com.techlads.player.domain.state.PlayerStateListener
import java.lang.ref.WeakReference

internal class ExoPlayerImpl(private val context: WeakReference<Context>, private val player: ExoPlayer) :
    TLPlayer {

    private var listener: Player.Listener? = null

    override fun play() {
        player.play()
    }

    override fun pause() {
        player.pause()
    }

    override fun stop() {
        player.stop()
    }

    override fun seekTo(positionMs: Long) {
        player.seekTo(positionMs)
    }

    override fun seekForward() {
        player.seekForward()
    }

    override fun seekBack() {
        player.seekBack()
    }

    override fun prepare(uri: String) {
        val context = context.get() ?: return
        player.apply {
            setMediaSource(prepareMediaSource(context, uri))
            prepare()
        }
    }

    override fun release() {
        player.release()
    }

    override fun setPlaybackEvent(callback: PlayerStateListener) {
        listener = ExoPlayerStateListenerBridge(callback, player).apply {
            player.addListener(this)
        }
    }

    override fun removePlaybackEvent(callback: PlayerStateListener) {
        listener?.let {
            player.removeListener(it)
        }
        listener = null
    }

    private fun prepareMediaSource(context: Context, uri: String) =
        ProgressiveMediaSource
            .Factory(Factory(context, Factory(context)))
            .createMediaSource(MediaItem.fromUri(uri))
}