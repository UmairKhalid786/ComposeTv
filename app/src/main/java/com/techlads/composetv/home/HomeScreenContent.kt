package com.techlads.composetv.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.techlads.composetv.leftmenu.LeftMenu

@Composable
fun HomeScreenContent() {
    LeftMenu(defaultFocus = 2,menuItems = MenuData.menuItems) {

    }
}

@Preview
@Composable
fun HomeScreenContentPrev() {
    HomeScreenContent()
}