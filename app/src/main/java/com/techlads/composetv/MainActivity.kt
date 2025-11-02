package com.techlads.composetv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.tv.material3.MaterialTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.techlads.composetv.features.home.HomeViewModel
import com.techlads.composetv.navigation.AppNavigation
import com.techlads.composetv.theme.ComposeTvTheme
import com.techlads.login.withEmailPassword.BackgroundViewModel
import com.techlads.login.withEmailPassword.CrossFadeBackground
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val backgroundViewModel by viewModels<BackgroundViewModel>()

        setContent {
            ComposeTvTheme {
                val state by backgroundViewModel.crossFadeState.collectAsStateWithLifecycle()
                val background = MaterialTheme.colorScheme.surface

                state?.let {
                    CrossFadeBackground(
                        state = it, modifier = Modifier
                            .fillMaxSize()
                            .drawWithContent {
                                drawContent()
                                drawRect(
                                    Brush.radialGradient(
                                        listOf(
                                            background.copy(0.8f),
                                            background.copy(0.7f),
                                            background.copy(0.6f),
                                        )
                                    ), size = size
                                )
                            })
                }


                val displayDialog = remember {
                    mutableStateOf(false)
                }
                val homeViewModel: HomeViewModel by viewModels()
                App(
                    navController = rememberAnimatedNavController(),
                    homeViewModel = homeViewModel,
                    backgroundViewModel = backgroundViewModel
                )

                registerOnBackPress {
                    displayDialog.value = true
                }

                if (displayDialog.value) {
                    CustomDialog(openDialogCustom = displayDialog) {
                        finish()
                    }
                }
            }
        }
    }

    @Composable
    fun App(
        navController: NavHostController,
        homeViewModel: HomeViewModel,
        backgroundViewModel: BackgroundViewModel
    ) {
        AppNavigation(
            navController, homeViewModel = homeViewModel, backgroundViewModel = backgroundViewModel
        )
    }
}
