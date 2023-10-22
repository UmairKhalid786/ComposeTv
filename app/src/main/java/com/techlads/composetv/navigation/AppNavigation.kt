package com.techlads.composetv.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
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
    AnimatedNavHost(navController = navController, startDestination = Screens.LoginToken.title) {
        // e.g will add auth routes here if when we will extend project
        composable(
            Screens.Login.title,
        ) {
            LoginScreen {
                navController.navigateSingleTopTo(Screens.WhoIsWatching.title)
            }
        }

        composable(
            Screens.LoginToken.title,
        ) {
            DeviceTokenAuthenticationScreen(onSkip = {
                navController.navigateSingleTopTo(Screens.Home.title)
            }) {
                navController.navigateSingleTopTo(it.title)
            }
        }

        composable(
            Screens.WhoIsWatching.title,
        ) {
            WhoIsWatchingScreen {
                navController.navigateSingleTopTo(Screens.Home.title)
            }
        }
        composable(
            Screens.Mp3Player.title,) {
            AudioPlayerScreen {
                navController.navigateUp()
            }
        }

        composable(
            Screens.Home.title,) {
            HomeScreen(homeViewModel, { _, _ ->
                navController.navigate(Screens.ProductDetail.title)
            }) {
                navController.navigate(Screens.Mp3Player.title)
            }
        }

        composable(
            Screens.Player.title,
        ) {
            PlayerScreen(
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                onBackPressed = {
                    navController.navigateUp()
                },
            )
        }

        composable(
            Screens.ProductDetail.title,
        ) {
            ProductDetailsScreen(
                onBackPressed = {
                    navController.navigateUp()
                },
                onPlayClick = {
                    navController.navigate(Screens.Player.title)
                },
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

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id,
        ) {
            saveState = true
            inclusive = true
        }
        launchSingleTop = true
        restoreState = true
    }
