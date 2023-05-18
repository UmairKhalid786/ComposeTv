package com.techlads.composetv.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.techlads.composetv.features.home.HomeScreen
import com.techlads.composetv.features.home.HomeViewModel
import com.techlads.composetv.features.login.withToken.DeviceTokenAuthenticationScreen
import com.techlads.composetv.features.login.withEmailPassword.LoginScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(navController: NavHostController, viewModel: HomeViewModel) {
    AnimatedNavHost(navController = navController, startDestination = Screens.Home.title) {
        // e.g will add auth routes here if when we will extend project
        composable(
            Screens.Login.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            LoginScreen {
                navController.navigateSingleTopTo(Screens.Home.title)
            }
        }

        composable(
            Screens.LoginToken.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            DeviceTokenAuthenticationScreen {
                navController.navigateSingleTopTo(it.title)
            }
        }

        composable(
            Screens.Home.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            HomeScreen(viewModel)
        }
    }
}

fun tabExitTransition(
    duration: Int = 500
) = fadeOut(tween(duration / 2, easing = LinearEasing))

fun tabEnterTransition(
    duration: Int = 500, delay: Int = duration - 350
) = fadeIn(tween(duration, duration - delay))

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
            inclusive = true
        }
        launchSingleTop = true
        restoreState = true
    }