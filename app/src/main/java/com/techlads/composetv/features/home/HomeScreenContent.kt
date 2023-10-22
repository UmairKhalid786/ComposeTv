package com.techlads.composetv.features.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.techlads.composetv.features.home.leftmenu.data.MenuData
import com.techlads.composetv.features.home.navigation.NestedHomeNavigation
import com.techlads.composetv.features.home.navigation.drawer.HomeDrawer
import com.techlads.composetv.features.home.navigation.topbar.HomeTopBar
import com.techlads.composetv.theme.ComposeTvTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreenContent(
    onItemFocus: (parent: Int, child: Int) -> Unit,
    usedTopBar: StateFlow<Boolean>,
    toggleNavigationBar: () -> Unit,
    onSongClick: () -> Unit,
) {
    val navController = rememberAnimatedNavController()

    val selectedId = remember {
        mutableStateOf(MenuData.menuItems.first().id)
    }

    LaunchedEffect(key1 = Unit) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            selectedId.value = destination.route ?: return@addOnDestinationChangedListener
        }
    }

    usedTopBar.collectAsState().value.let {selectedTopBar ->
        when(selectedTopBar) {
            true -> HomeTopBar(content = {
                NestedHomeNavigation(usedTopBar, toggleNavigationBar, navController, onItemFocus, onSongClick)
            }, selectedId = selectedId.value) {
                navController.navigate(it.id)
            }
            false -> HomeDrawer(content = {
                NestedHomeNavigation(usedTopBar, toggleNavigationBar, navController, onItemFocus, onSongClick)
            }, selectedId = selectedId.value) {
                navController.navigate(it.id)
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenContentPrev() {
    ComposeTvTheme {
        HomeScreenContent(
            onItemFocus = { _, _ -> },
            usedTopBar = MutableStateFlow(false),
            toggleNavigationBar = {}) {}
    }
}
