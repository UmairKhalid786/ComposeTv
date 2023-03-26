package com.techlads.composetv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.techlads.composetv.navigation.AppNavigation
import com.techlads.composetv.theme.Material3Theme

class LoginActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val displayDialog = remember {
                mutableStateOf(false)
            }

            App(navController = rememberAnimatedNavController())

            registerOnBackPress {
                displayDialog.value = true
            }

            if (displayDialog.value)
                CustomDialog(openDialogCustom = displayDialog){
                    finish()
                }
        }
    }

    @Composable
    fun App(navController: NavHostController) {
        Material3Theme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNavigation(navController)
            }
        }
    }
}
