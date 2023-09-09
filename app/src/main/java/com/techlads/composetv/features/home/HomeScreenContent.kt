package com.techlads.composetv.features.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.techlads.composetv.features.home.navigation.NestedHomeNavigation
import com.techlads.composetv.features.home.navigation.drawer.HomeDrawer
import com.techlads.composetv.theme.ComposeTvTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreenContent(
    onItemFocus: (parent: Int, child: Int) -> Unit,
    onSongClick: () -> Unit
) {
    val navController = rememberAnimatedNavController()

    Row {
        HomeDrawer(content = {
            NestedHomeNavigation(navController, onItemFocus, onSongClick)
        }) {
            navController.navigate(it.id)
        }
    }
}

@Preview
@Composable
fun HomeScreenContentPrev() {
    ComposeTvTheme {
        HomeScreenContent({ _, _ -> }) {}
    }
}
