package com.techlads.composetv.features.home

import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    onItemFocus: (parent: Int, child: Int) -> Unit,
    onSongClick: () -> Unit
) {
    HomeScreenContent(onItemFocus, onSongClick)
}
