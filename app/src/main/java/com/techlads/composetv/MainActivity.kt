package com.techlads.composetv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.techlads.composetv.features.home.HomeViewModel
import com.techlads.composetv.navigation.AppNavigation
import com.techlads.composetv.theme.ComposeTvTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: HomeViewModel by viewModels()

        setContent {
            val displayDialog = remember {
                mutableStateOf(false)
            }

            App(navController = rememberAnimatedNavController(), viewModel)

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

    @Composable
    fun App(navController: NavHostController, viewModel: HomeViewModel) {
        ComposeTvTheme {
            AppNavigation(navController, viewModel)
        }
    }
}
