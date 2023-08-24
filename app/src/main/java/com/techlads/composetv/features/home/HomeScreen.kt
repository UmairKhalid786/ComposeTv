package com.techlads.composetv.features.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onItemFocus: (parent: Int, child: Int) -> Unit,
) {
    HomeScreenContent(viewModel, onItemFocus)
}
