package com.techlads.composetv.features.home

import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onItemFocus: (parent: Int, child: Int) -> Unit,
    onSongClick: () -> Unit,
) {
    HomeScreenContent(onItemFocus, homeViewModel.usedTopBar,
        { homeViewModel.updateMenu(it) }, onSongClick
    )
}
