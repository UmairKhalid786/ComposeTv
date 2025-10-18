package com.techlads.composetv.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.techlads.composetv.features.details.ProductDetailsScreen
import com.techlads.composetv.features.home.HomeScreen
import com.techlads.composetv.features.home.HomeViewModel
import com.techlads.composetv.features.login.withEmailPassword.LoginScreen
import com.techlads.composetv.features.login.withToken.DeviceTokenAuthenticationScreen
import com.techlads.composetv.features.mp3.player.AudioPlayerScreen
import com.techlads.composetv.features.player.PlayerScreen
import com.techlads.composetv.features.wiw.WhoIsWatchingScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(navController: NavHostController, homeViewModel: HomeViewModel) {
    NavHost(navController = navController, startDestination = Screens.Login.route) {
        // e.g will add auth routes here if when we will extend project
        composable(
            Screens.Login.route,
        ) {
            LoginScreen {
                navController.navigateSingleTopTo(Screens.WhoIsWatching.route)
            }
        }

        composable(
            Screens.LoginToken.route,
        ) {
            DeviceTokenAuthenticationScreen(onSkip = {
                navController.navigateSingleTopTo(Screens.Home.route)
            }) {
                navController.navigateSingleTopTo(it.route)
            }
        }

        composable(
            Screens.WhoIsWatching.route,
        ) {
            WhoIsWatchingScreen {
                navController.navigateSingleTopTo(Screens.Home.route)
            }
        }
        composable(
            Screens.Mp3Player.route,
        ) {
            AudioPlayerScreen {
                navController.navigateUp()
            }
        }

        composable(
            Screens.Home.route,
        ) {
            HomeScreen(homeViewModel, { _, child ->
                navController.navigate(Screens.ProductDetail.createRoute(child.toInt()))
            }) {
                navController.navigate(Screens.Mp3Player.route)
            }
        }

        composable(
            Screens.Player.route,
        ) {
            PlayerScreen(
//                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                "https://www.youtube.com/watch?v=kI7RdKPpPFc",
                onBackPressed = {
                    navController.navigateUp()
                },
            )
        }

        composable(
            Screens.ProductDetail.route,
        ) { backStackEntry ->
            ProductDetailsScreen(
                onBackPressed = {
                navController.navigateUp()
            }, onPlayClick = {
                navController.navigate(Screens.Player.route)
            }, viewModel = hiltViewModel()
            )
        }
    }
}

fun tabExitTransition(
    duration: Int = 500,
) = fadeOut(tween(duration / 2, easing = LinearEasing))

fun tabEnterTransition(
    duration: Int = 500,
    delay: Int = duration - 350,
) = fadeIn(tween(duration, duration - delay))

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(
        this@navigateSingleTopTo.graph.findStartDestination().id,
    ) {
        saveState = true
        inclusive = true
    }
    launchSingleTop = true
    restoreState = true
}
