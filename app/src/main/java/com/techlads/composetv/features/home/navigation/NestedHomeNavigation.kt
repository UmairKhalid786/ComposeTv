package com.techlads.composetv.features.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun NestedHomeNavigation(
    navController: NavHostController,
    onItemClick: (parent: String, child: String) -> Unit,
    onItemFocus: (parent: String, child: String) -> Unit,
    onSongClick: () -> Unit
) {
    NestedHomeScreenNavigation(
        navController, onItemClick, onItemFocus, onSongClick
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
private fun NestedHomeNavigationPrev() {
    NestedHomeNavigation(
        rememberAnimatedNavController(),
        { _, _ -> },
        { _, _ -> }) {}
}
