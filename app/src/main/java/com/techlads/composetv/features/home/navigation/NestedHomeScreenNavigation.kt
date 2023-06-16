package com.techlads.composetv.features.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.techlads.composetv.features.favorites.FavoritesScreen
import com.techlads.composetv.features.home.HomeNestedScreen
import com.techlads.composetv.features.movies.MoviesScreen
import com.techlads.composetv.navigation.tabEnterTransition
import com.techlads.composetv.navigation.tabExitTransition
import com.techlads.composetv.features.search.SearchScreen
import com.techlads.composetv.features.settings.SettingsScreen
import com.techlads.composetv.features.songs.SongsScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NestedHomeScreenNavigation(
    navController: NavHostController,
    onItemClick: (parent: Int, child: Int) -> Unit
) {
    AnimatedNavHost(navController = navController, startDestination = NestedScreens.Home.title) {
        // e.g will add auth routes here if when we will extend project
        composable(
            NestedScreens.Home.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            HomeNestedScreen(onItemFocus = { _, _ -> }, onItemClick = onItemClick)
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
            MoviesScreen(onItemFocus = onItemClick)
        }

        composable(
            NestedScreens.Songs.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            SongsScreen()
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