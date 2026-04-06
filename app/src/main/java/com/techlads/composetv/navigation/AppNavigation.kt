package com.techlads.composetv.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.auth.AuthState
import com.techlads.composetv.features.details.ProductDetailsScreen
import com.techlads.composetv.features.home.HomeScreen
import com.techlads.composetv.features.mp3.player.AudioPlayerScreen
import com.techlads.composetv.features.player.PlayerScreen
import com.techlads.composetv.features.wiw.WhoIsWatchingScreen
import com.techlads.login.withEmailPassword.BackgroundViewModel
import com.techlads.login.withEmailPassword.LoginScreen
import com.techlads.login.withToken.DeviceTokenAuthenticationScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(
    navController: NavHostController,
    backgroundViewModel: BackgroundViewModel,
    viewModel: AppNavigationViewModel = hiltViewModel(),
) {

    val state by viewModel.authState.collectAsStateWithLifecycle()
    LaunchedEffect(state) {
        when (state) {
            AuthState.Loading -> Unit
            is AuthState.LoggedIn -> navController.navigate(Screens.WhoIsWatching.route) {
                popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                launchSingleTop = true
            }

            AuthState.LoggedOut -> navController.navigate(Screens.Login.route) {
                popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    NavHost(navController = navController, startDestination = Screens.AuthLoading.route) {
        // e.g will add auth routes here if when we will extend project
        composable(
            Screens.AuthLoading.route,
        ) {
            AuthStateScreen()
        }

        composable(
            Screens.Login.route,
        ) {
            LoginScreen()
        }

        composable(
            Screens.LoginToken.route,
        ) {
            DeviceTokenAuthenticationScreen(onSkip = {
                navController.navigateSingleTopTo(Screens.Home.route)
            }, onLogin = {
                navController.navigateSingleTopTo(Screens.Login.route)
            })
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
            HomeScreen(onItemClick = { _, child ->
                navController.navigate(Screens.ProductDetail.createRoute(child.toInt()))
            }, onItemFocus = { parent, child ->
                backgroundViewModel.changeBackground(parent, child)
            }) {
                navController.navigate(Screens.Mp3Player.route)
            }
        }

        composable(
            Screens.Player.route,
        ) {
            PlayerScreen(
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
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

@Composable
private fun AuthStateScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Checking session",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Restoring your TMDB login state.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
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
