package com.techlads.composetv.features.home

import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onItemFocus: (parent: String, id: String) -> Unit,
    onSongClick: () -> Unit,
) {
    HomeScreenContent(onItemFocus, homeViewModel.usedTopBar,
        navigationBar = { homeViewModel.updateMenu(it) }, onSongClick
    )
}
