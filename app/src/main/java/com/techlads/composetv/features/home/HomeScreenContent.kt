package com.techlads.composetv.features.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.techlads.composetv.features.home.leftmenu.data.MenuData
import com.techlads.composetv.features.home.navigation.NestedHomeNavigation
import com.techlads.composetv.features.home.navigation.drawer.HomeDrawer
import com.techlads.composetv.features.home.navigation.topbar.HomeTopBar
import com.techlads.composetv.theme.ComposeTvTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreenContent(
    onItemFocus: (parent: Int, child: Int) -> Unit,
    onSongClick: () -> Unit
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

    HomeTopBar(content = {
        NestedHomeNavigation(navController, onItemFocus, onSongClick)
    }, selectedId = selectedId.value) {
        navController.navigate(it.id)
    }
}

@Preview
@Composable
fun HomeScreenContentPrev() {
    ComposeTvTheme {
        HomeScreenContent({ _, _ -> }) {}
    }
}
