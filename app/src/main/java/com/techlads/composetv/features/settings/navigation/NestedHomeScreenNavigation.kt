package com.techlads.composetv.features.settings.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.techlads.composetv.navigation.tabEnterTransition
import com.techlads.composetv.navigation.tabExitTransition
import com.techlads.composetv.features.settings.screens.about.AboutScreen
import com.techlads.composetv.features.settings.screens.profile.ProfileScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NestedSettingsScreenNavigation(navController: NavHostController) {
    AnimatedNavHost(navController = navController, startDestination = SettingsScreens.Profile.title) {
        // e.g will add auth routes here if when we will extend project
        composable(
            SettingsScreens.Profile.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            ProfileScreen()
        }
        composable(
            SettingsScreens.AboutMe.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            AboutScreen()
        }
    }
}