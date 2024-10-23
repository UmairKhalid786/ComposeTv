package com.techlads.composetv.features.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.MaterialTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.techlads.composetv.features.home.leftmenu.data.MenuData
import com.techlads.composetv.features.home.navigation.NestedHomeNavigation
import com.techlads.composetv.features.home.navigation.drawer.HomeDrawer
import com.techlads.composetv.features.home.navigation.topbar.HomeTopBar
import com.techlads.composetv.features.login.withEmailPassword.backgroundImageState
import com.techlads.composetv.theme.ComposeTvTheme
import com.techlads.composetv.utils.Storage
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

    val backgroundState = backgroundImageState()

    val selectedId = remember {
        mutableStateOf(MenuData.menuItems.first().id)
    }

    LaunchedEffect(key1 = Unit) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            selectedId.value = destination.route ?: return@addOnDestinationChangedListener
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        val targetBitmap by remember(backgroundState) { backgroundState.drawable }

        val overlayColor = MaterialTheme.colorScheme.background.copy(alpha = 0.9f)

        Crossfade(targetState = targetBitmap) {
            it?.let {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .drawWithContent {
                            drawContent()
                            drawRect(color = overlayColor)
                        },
                    bitmap = it,
                    contentDescription = "Hero item background",
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }

    usedTopBar.collectAsState().value.let { selectedTopBar ->

        when (selectedTopBar) {
            true -> HomeTopBar(content = {
                NestedHomeNavigation(usedTopBar,
                    toggleNavigationBar,
                    navController,
                    onItemClick = { parent, child ->
                        onItemFocus(parent, child)
                    },
                    onItemFocus = { _, child ->
                        backgroundState.load(Storage.movies[child % Storage.movies.size].imageUrl)
                    },
                    onSongClick
                )
            }, selectedId = selectedId.value) {
                navController.navigate(it.id)
            }

            false -> HomeDrawer(content = {
                NestedHomeNavigation(usedTopBar,
                    toggleNavigationBar,
                    navController,
                    onItemClick = { parent, child ->
                        onItemFocus(parent, child)
                    },
                    onItemFocus = { _, child ->
                        backgroundState.load(Storage.movies[child % Storage.movies.size].imageUrl)
                    },
                    onSongClick
                )
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
        HomeScreenContent(onItemFocus = { _, _ -> },
            usedTopBar = MutableStateFlow(false),
            toggleNavigationBar = {}) {}
    }
}
