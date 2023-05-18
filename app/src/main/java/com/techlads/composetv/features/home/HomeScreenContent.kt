package com.techlads.composetv.features.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.techlads.composetv.features.home.navigation.NestedHomeNavigation
import com.techlads.composetv.features.home.leftmenu.LeftMenu

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreenContent(viewModel: HomeViewModel) {

    val menuItems by viewModel.menuItems.collectAsState()
    val navController = rememberAnimatedNavController()

    val isExpanded = remember {
        mutableStateOf(false)
    }

    Row {
        LeftMenu(
            modifier = Modifier.wrapContentWidth(),
            defaultFocus = 0,
            isExpanded = isExpanded.value,
            menuItems = menuItems,
            onMenuFocused = { _, isSelected ->
                isExpanded.value = !isExpanded.value && isSelected
                setViewModelState(isSelected, viewModel)
            }
        ) {
            navController.navigate(it.id)
        }

        NestedHomeNavigation(navController)
    }
}

fun setViewModelState(isSelected: Boolean, viewModel: HomeViewModel) {
    if (isSelected) {
        viewModel.menuOpen()
    } else {
        viewModel.menuClosed()
    }
}

@Preview
@Composable
fun HomeScreenContentPrev() {
    HomeScreenContent(hiltViewModel())
}