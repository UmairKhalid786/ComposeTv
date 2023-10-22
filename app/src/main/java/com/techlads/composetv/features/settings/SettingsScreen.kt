@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.features.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.techlads.composetv.features.settings.navigation.NestedSettingsScreenNavigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalAnimationApi::class, ExperimentalTvMaterial3Api::class)
@Composable
fun SettingsScreen(usedTopBar: StateFlow<Boolean>, toggleTopBar: () -> Unit) {
    val navController = rememberAnimatedNavController()
//    val homeViewModel: HomeViewModel = remember { HomeViewModel() }

    Row(
        Modifier
            .fillMaxSize(),
    ) {
        SettingsMenu(
            usedTopBar = usedTopBar,
            toggleTopBar = {toggleTopBar()},
            modifier = Modifier
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.2f))
                .padding(vertical = 32.dp, horizontal = 16.dp),
        ) {
            navController.navigate(it.navigation)
        }
        SettingsNavigation(navController)
    }
}

@Composable
fun SettingsNavigation(navController: NavHostController) {
    NestedSettingsScreenNavigation(navController = navController)
}

@Preview
@Composable
fun SettingsScreenPrev() {
    SettingsScreen(MutableStateFlow(false)){}
}
