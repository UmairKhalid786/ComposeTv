package com.techlads.login.withEmailPassword

import android.graphics.drawable.BitmapDrawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.ImageResult
import kotlinx.coroutines.Deferred

@Stable
@Immutable
class LoginBackgroundState(
    private val coilImageLoader: ImageLoader,
    private val coilBuilder: ImageRequest.Builder,
) {
    val drawable by lazy { mutableStateOf<ImageBitmap?>(null) }
    private var job: Deferred<ImageResult>? = null

    fun load(url: String, onSuccess: () -> Unit = {}, onError: () -> Unit = {}) {
        job?.cancel()

        val request = coilBuilder.data(url).target(onSuccess = { result ->
                drawable.value = (result as? BitmapDrawable)?.bitmap?.asImageBitmap()
                onSuccess()
            }, onError = {
                onError()
            }).build()

        job = coilImageLoader.enqueue(request).job
    }
}

data class Movie(
    val title: String,
    val details: String,
    val imageUrl: String,
    val metadata: String
)


@Composable
fun backgroundImageState(): LoginBackgroundState {
    val context = LocalContext.current
    val imageLoader = remember { ImageLoader(context) }
    val builder = remember { ImageRequest.Builder(context) }

    return remember(imageLoader, builder) {
        LoginBackgroundState(
            imageLoader,
            builder,
        )
    }
}