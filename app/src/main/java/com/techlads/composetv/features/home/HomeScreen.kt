package com.techlads.composetv.features.home

import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    onItemFocus: (parent: String, id: String) -> Unit,
    onItemClick: (parent: String, id: String) -> Unit,
    onSongClick: () -> Unit,
) {
    HomeScreenContent(onItemClick = onItemClick, onItemFocus = onItemFocus, onSongClick)
}
