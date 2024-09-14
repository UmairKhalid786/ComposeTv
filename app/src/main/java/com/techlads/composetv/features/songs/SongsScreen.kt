@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.features.songs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.composetv.R
import com.techlads.composetv.widgets.BorderedFocusableItem

@Composable
fun SongsScreen(onSongClick: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        SongCategories(Modifier.weight(0.8f))
        RecentHistory(Modifier.weight(1.2f), onSongClick)
    }
}

@Composable
fun RecentHistory(modifier: Modifier = Modifier, onSongClick: () -> Unit) {
    Column(modifier) {
        Text(
            text = "Recent Songs",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 32.dp),
        )
        LazyRow(contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp)) {
            items(12) {
                BorderedFocusableItem(
                    onClick = onSongClick,
                    borderRadius = 12.dp,
                    modifier = Modifier.padding(4.dp)
                ) {
                    SongItem(it)
                }
            }
        }
    }
}

@Composable
fun SongCategories(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .clipToBounds()
    ) {
        SongsHomeGreeting()
        Spacer(modifier = Modifier.height(8.dp))
        TagsList()
    }
}

@Composable
fun TagsList(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp),
        columns = GridCells.Fixed(3),
        modifier = modifier.fillMaxSize()
    ) {
        items(6) {
            BorderedFocusableItem(onClick = {},
                borderRadius = 12.dp,
                modifier = Modifier.padding(4.dp)) {
                TagItem(it)
            }
        }
    }
}

@Composable
fun TagItem(it: Int) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.song),
            contentDescription = "Song image",
            modifier = Modifier
                .size(50.dp)
                .clip(MaterialTheme.shapes.large)
        )
        Text(
            text = "Song item $it",
            Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
        )
    }
}

@Composable
fun SongItem(it: Int) {
    Column(
        Modifier
            .width(150.dp)
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.medium)
    ) {
        Image(
            painter = painterResource(id = R.drawable.song),
            contentDescription = "Song image",
            Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
        )
        Text(
            text = "Song item $it", Modifier.padding(16.dp)
        )
    }
}

@Composable
fun SongsHomeGreeting() {
    Text(
        text = "Good Morning",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(start = 32.dp, top = 32.dp)
    )
}

@Preview
@Composable
fun SongsScreenPrev() {
    SongsScreen {}
}

@Preview
@Composable
fun SongItemPrev() {
    SongItem(1)
}

@Preview
@Composable
fun TagItemPrev() {
    TagItem(1)
}
