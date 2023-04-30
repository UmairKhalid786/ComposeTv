package com.techlads.player

import android.content.Context
import androidx.media3.common.AudioAttributes
import androidx.media3.common.MediaItem
import androidx.media3.common.TrackSelectionParameters
import androidx.media3.database.DatabaseProvider
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.cache.Cache
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.NoOpCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import androidx.media3.datasource.cronet.CronetDataSource
import androidx.media3.datasource.cronet.CronetUtil
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.drm.DefaultDrmSessionManagerProvider
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.util.EventLogger
import java.io.File
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.Executors.newSingleThreadExecutor


private const val USE_CRONET_FOR_NETWORKING = true
private const val DOWNLOAD_CONTENT_DIRECTORY = "downloads"


object PlayerFactory {

    private val mediaItems = arrayListOf<MediaItem>()

    private var dataSourceFactory: DataSource.Factory? = null
    private var sourceFactory: DataSource.Factory? = null
    private var downloadCache: Cache? = null
    private var downloadDirectory: File? = null
    private var databaseProvider: DatabaseProvider? = null

    private var trackSelectionParameters: TrackSelectionParameters? = null

    private fun buildSelector(context: Context): TrackSelectionParameters {
        if (trackSelectionParameters == null)
            trackSelectionParameters = TrackSelectionParameters.Builder(context).build()

        return trackSelectionParameters!!
    }


    fun create(
        context: Context,
        startAutoPlay: Boolean = false,
        playerListener: PlayerEventListener
    ): ExoPlayer {
        val playerBuilder = ExoPlayer.Builder(context)
        createMediaSourceFactory(context)?.let {
            playerBuilder.setMediaSourceFactory(it)
        }

        return playerBuilder.build().apply {
            this.trackSelectionParameters = buildSelector(context)
            addListener(playerListener)
            addAnalyticsListener(EventLogger())
            setAudioAttributes(AudioAttributes.DEFAULT, true)
            playWhenReady = startAutoPlay
        }
    }

    private fun createMediaSourceFactory(context: Context): MediaSource.Factory? {
        dataSourceFactory = getDataSourceFactory(context)
        val drmSessionManagerProvider = DefaultDrmSessionManagerProvider().apply {
            setDrmHttpDataSourceFactory(
                getHttpDataSourceFactory(context)
            )
        }

        return DefaultMediaSourceFactory(context)
            .setDataSourceFactory(dataSourceFactory!!)
            .setDrmSessionManagerProvider(drmSessionManagerProvider)
    }

    fun getMediaItems() = mediaItems

    @Synchronized
    fun getHttpDataSourceFactory(context: Context): DataSource.Factory? {
        if (sourceFactory != null) return sourceFactory

        if (USE_CRONET_FOR_NETWORKING) {
            CronetUtil.buildCronetEngine(context.applicationContext)?.let { engine ->
                sourceFactory = CronetDataSource.Factory(engine, newSingleThreadExecutor())
            }
        }
        if (sourceFactory == null) {
            setCookieManager()
            sourceFactory = DefaultHttpDataSource.Factory()
        }

        return sourceFactory
    }

    private fun setCookieManager() {
        // We don't want to use Cronet, or we failed to instantiate a CronetEngine.
        CookieManager().apply {
            setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER)
            CookieHandler.setDefault(this)
        }
    }

    @Synchronized
    fun getDataSourceFactory(context: Context): DataSource.Factory? {
        if (dataSourceFactory == null) {
            val ctx = context.applicationContext
            val upstreamFactory = DefaultDataSource.Factory(
                ctx,
                getHttpDataSourceFactory(ctx) ?: return null
            )
            dataSourceFactory = buildReadOnlyCacheDataSource(
                upstreamFactory,
                getDownloadCache(ctx) ?: return null
            )
        }
        return dataSourceFactory
    }

    private fun buildReadOnlyCacheDataSource(
        upstreamFactory: DataSource.Factory, cache: Cache
    ): CacheDataSource.Factory {
        return CacheDataSource.Factory()
            .setCache(cache)
            .setUpstreamDataSourceFactory(upstreamFactory)
            .setCacheWriteDataSinkFactory(null)
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
    }

    @Synchronized
    private fun getDownloadCache(context: Context): Cache? {
        if (downloadCache == null) {
            val downloadContentDirectory: File = File(
                getDownloadDirectory(context),
                DOWNLOAD_CONTENT_DIRECTORY
            )
            downloadCache = SimpleCache(
                downloadContentDirectory,
                NoOpCacheEvictor(),
                getDatabaseProvider(context) ?: return null
            )
        }
        return downloadCache
    }

    @Synchronized
    private fun getDownloadDirectory(context: Context): File? {
        if (downloadDirectory == null) {
            downloadDirectory = context.getExternalFilesDir(null)
            if (downloadDirectory == null) {
                downloadDirectory = context.filesDir
            }
        }
        return downloadDirectory
    }

    @Synchronized
    private fun getDatabaseProvider(context: Context): DatabaseProvider? {
        if (databaseProvider == null) {
            databaseProvider = StandaloneDatabaseProvider(context)
        }
        return databaseProvider
    }
}