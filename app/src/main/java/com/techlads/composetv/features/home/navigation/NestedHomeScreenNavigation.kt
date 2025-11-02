package com.techlads.composetv.features.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.techlads.composetv.features.favorites.FavoritesScreen
import com.techlads.composetv.features.home.HomeNestedScreen
import com.techlads.composetv.features.home.NavigationEvent
import com.techlads.composetv.features.movies.MoviesScreen
import com.techlads.composetv.features.search.SearchScreen
import com.techlads.composetv.features.settings.SettingsScreen
import com.techlads.composetv.features.songs.SongsScreen
import com.techlads.composetv.navigation.tabEnterTransition
import com.techlads.composetv.navigation.tabExitTransition
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NestedHomeScreenNavigation(
    navController: NavHostController,
    onItemClick: (parent: String, child: String) -> Unit,
    onItemFocus: (parent: String, child: String) -> Unit,
    onSongClick: () -> Unit
) {
    AnimatedNavHost(navController = navController, startDestination = NestedScreens.Home.title) {
        // e.g will add auth routes here if when we will extend project
        composable(
            NestedScreens.Home.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            HomeNestedScreen(onItemFocus = onItemFocus, onItemClick = onItemClick, homeViewModel = hiltViewModel())
        }

        composable(
            NestedScreens.Search.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            SearchScreen()
        }

        composable(
            NestedScreens.Movies.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            MoviesScreen(onItemClick)
        }

        composable(
            NestedScreens.Songs.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            SongsScreen(onSongClick)
        }

        composable(
            NestedScreens.Favorites.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            FavoritesScreen()
        }

        composable(
            NestedScreens.Settings.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            SettingsScreen()
        }
    }
}
