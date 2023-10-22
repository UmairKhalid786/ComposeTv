package com.techlads.composetv.features.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun NestedHomeNavigation(
    usedTopBar: StateFlow<Boolean>,
    toggleTopBar: () -> Unit,
    navController: NavHostController,
    onItemFocus: (parent: Int, child: Int) -> Unit,
    onSongClick: () -> Unit
) {
    NestedHomeScreenNavigation(usedTopBar, toggleTopBar, navController, onItemFocus, onSongClick)
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
private fun NestedHomeNavigationPrev() {
    NestedHomeNavigation(MutableStateFlow(false), {},  rememberAnimatedNavController(), { _, _ -> }) {}
}
