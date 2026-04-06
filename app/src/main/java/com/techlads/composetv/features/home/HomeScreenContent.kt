package com.techlads.composetv.features.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.techlads.composetv.features.home.leftmenu.data.MenuData
import com.techlads.composetv.features.home.navigation.NestedHomeNavigation
import com.techlads.composetv.features.home.navigation.topbar.HomeTopBar
import com.techlads.composetv.theme.ComposeTvTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreenContent(
    onItemClick: (parent: String, id: String) -> Unit,
    onItemFocus: (parent: String, id: String) -> Unit,
    onSongClick: () -> Unit,
) {
    val navController = rememberAnimatedNavController()

    var selectedId by rememberSaveable {
        mutableStateOf(MenuData.menuItems.first().id)
    }

    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            selectedId = destination.route ?: return@OnDestinationChangedListener
        }
        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    HomeTopBar(content = {
        NestedHomeNavigation(
            navController, onItemClick = { parent, child ->
            onItemClick(parent, child)
        }, onItemFocus = { parent, child ->
            onItemFocus(parent, child)
        }, onSongClick
        )
    }, selectedId = selectedId) {
        navController.navigate(it.id)
    }
}

@Preview
@Composable
fun HomeScreenContentPrev() {
    ComposeTvTheme {
        HomeScreenContent(onItemFocus = { _, _ -> }, onItemClick = { _, _ -> }) {}
    }
}
