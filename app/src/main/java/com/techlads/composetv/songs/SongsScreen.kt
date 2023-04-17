package com.techlads.composetv.songs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.composetv.R
import com.techlads.composetv.songs.data.SongsTagsData.generateRandomColor
import com.techlads.composetv.widgets.TransparentBorderedFocusableItem

@Composable
fun SongsScreen() {
    Column(Modifier.fillMaxSize()) {
        SongCategories(Modifier.weight(0.8f))
        RecentHistory(Modifier.weight(1.2f))
    }
}

@Composable
fun RecentHistory(modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = "Recent Songs",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        TvLazyRow(contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp)) {
            items(12) {
                TransparentBorderedFocusableItem(onClick = {}, Modifier.padding(4.dp)) {
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
            .clipToBounds()) {
        SongsHomeGreeting()
        Spacer(modifier = Modifier.height(8.dp))
        TagsList()
    }
}

@Composable
fun TagsList(modifier: Modifier = Modifier) {
    TvLazyVerticalGrid(
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp),
        columns = TvGridCells.Fixed(3),
        modifier = modifier
            .fillMaxSize()
    ) {
        items(6) {
            TransparentBorderedFocusableItem(onClick = {}, Modifier.padding(4.dp)) {
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
            .background(generateRandomColor()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.song),
            contentDescription = "Song image",
            modifier = Modifier.size(50.dp)
        )
        Text(text = "Song item $it",
            Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun SongItem(it: Int) {
    Column(
        Modifier
            .width(150.dp)
            .wrapContentHeight()
            .background(generateRandomColor())
    ) {
        Image(
            painter = painterResource(id = R.drawable.song),
            contentDescription = "Song image",
            Modifier.fillMaxWidth()
        )
        Text(
            text = "Song item $it",
            Modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun SongsHomeGreeting() {
    Text(text = "Good Morning", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(start = 32.dp, top = 32.dp))
}


@Preview
@Composable
fun SongsScreenPrev() {
    SongsScreen()
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