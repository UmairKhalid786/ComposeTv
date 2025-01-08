package com.techlads.composetv.features.mp3.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.IconButtonDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.composetv.R
import com.techlads.composetv.features.player.controls.PlayerControlsState
import com.techlads.composetv.features.player.controls.VideoPlayerControllerIndicator
import com.techlads.composetv.features.player.controls.rememberVideoPlayerState

@Composable
fun AudioPlayerScreenContent(modifier: Modifier){
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Poster()
            Spacer(modifier = Modifier.size(32.dp))
            Details()
            Spacer(modifier = Modifier.size(32.dp))
            SongProgressBar(modifier = Modifier.width(600.dp)) {}
            Mp3Controls()
        }
    }
}

@Composable
fun Mp3Controls() {
    Row {
        Control(modifier = Modifier.size(48.dp), content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_shuffle),
                contentDescription = "Shuffle"
            )
        })
        Spacer(modifier = Modifier.size(32.dp))

        Control(modifier = Modifier.size(48.dp), content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_previous),
                contentDescription = "Previous"
            )
        })
        Spacer(modifier = Modifier.size(32.dp))
        Control(modifier = Modifier.size(48.dp), content = {
            Icon(painter = painterResource(id = R.drawable.ic_pause), contentDescription = "Play")
        })
        Spacer(modifier = Modifier.size(32.dp))
        Control(modifier = Modifier.size(48.dp), content = {
            Icon(painter = painterResource(id = R.drawable.ic_next), contentDescription = "Next")
        })
        Spacer(modifier = Modifier.size(32.dp))
        Control(modifier = Modifier.size(48.dp), content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_repeat), contentDescription = "Repeat"
            )
        })
    }
}

@Composable
fun Control(
    modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    IconButton(modifier = modifier,
        scale = IconButtonDefaults.scale(focusedScale = 1.6f, pressedScale = 1.1f),
        onClick = { /*TODO*/ }) {
        content()
    }
}

@Composable
fun SongProgressBar(
    modifier: Modifier = Modifier,
    state: PlayerControlsState = rememberVideoPlayerState(coroutineScope = rememberCoroutineScope()),
    onSeek: (seekProgress: Float) -> Unit
) {
    val contentProgressInMillis = 1000L
    val contentDurationInMillis = 10000L

    val contentProgress by remember(contentProgressInMillis, contentDurationInMillis) {
        derivedStateOf {
            contentProgressInMillis.toFloat() / contentDurationInMillis
        }
    }

    Column {
        Row(
            modifier.height(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            VideoPlayerControllerIndicator(
                modifier = Modifier
                    .shadow(4.dp),
                progress = contentProgress, onSeek = onSeek, state = state
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Row(modifier = modifier) {
            Text(text = "00:00", style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "00:10", style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Composable
fun Details() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Chris Brown - Babie ft. Tyga", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.size(2.dp))
        Text(text = "Jake Diaz ・ 2021", style = MaterialTheme.typography.titleSmall)
    }
}

@Composable
fun Poster(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.song),
        contentDescription = "Song poster",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .width(200.dp)
            .shadow(24.dp, MaterialTheme.shapes.extraLarge)
            .clip(MaterialTheme.shapes.extraLarge)
            .aspectRatio(1f)
    )
}
