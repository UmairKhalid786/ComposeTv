package com.techlads.composetv.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.techlads.composetv.home.navigation.NestedHomeNavigation
import com.techlads.composetv.leftmenu.LeftMenu
import com.techlads.composetv.leftmenu.data.MenuData

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreenContent() {

    val navController = rememberAnimatedNavController()

    val isExpanded = remember {
        mutableStateOf(false)
    }

    Row {
        LeftMenu(
            modifier = Modifier.wrapContentWidth(),
            defaultFocus = 0,
            isExpanded = isExpanded.value,
            menuItems = MenuData.menuItems,
            onMenuFocused = { _, isSelected ->
                isExpanded.value = !isExpanded.value && isSelected
            }
        ) {
            navController.navigate(it.id)
        }

        NestedHomeNavigation(navController)
    }
}

@Preview
@Composable
fun HomeScreenContentPrev() {
    HomeScreenContent()
}