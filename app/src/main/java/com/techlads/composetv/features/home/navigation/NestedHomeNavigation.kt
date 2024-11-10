package com.techlads.composetv.features.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.techlads.composetv.features.home.NavigationEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun NestedHomeNavigation(
    usedTopBar: StateFlow<NavigationEvent>,
    navigationBar: (NavigationEvent) -> Unit,
    navController: NavHostController,
    onItemClick: (parent: Int, child: Int) -> Unit,
    onItemFocus: (parent: Int, child: Int) -> Unit,
    onSongClick: () -> Unit
) {
    NestedHomeScreenNavigation(
        usedTopBar, navigationBar, navController, onItemClick, onItemFocus, onSongClick
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
private fun NestedHomeNavigationPrev() {
    NestedHomeNavigation(MutableStateFlow(NavigationEvent.TopBar),
        {},
        rememberAnimatedNavController(),
        { _, _ -> },
        { _, _ -> }) {}
}
