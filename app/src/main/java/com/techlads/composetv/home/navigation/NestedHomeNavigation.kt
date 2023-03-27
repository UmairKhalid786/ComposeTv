package com.techlads.composetv.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun NestedHomeNavigation(navController: NavHostController) {
    NestedHomeScreenNavigation(navController)
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun NestedHomeNavigationPrev() {
    NestedHomeNavigation(rememberAnimatedNavController())
}