package com.techlads.composetv.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.Text
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.techlads.composetv.common.FocusableItem
import com.techlads.composetv.settings.data.SettingsMenuModel
import com.techlads.composetv.settings.navigation.NestedSettingsScreenNavigation

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SettingsScreen() {
    val navController = rememberAnimatedNavController()

    Row {
        SettingsMenu {
            navController.navigate(it.navigation)
        }
        SettingsNavigation(navController)
    }
}

@Composable
fun SettingsNavigation(navController: NavHostController) {
    NestedSettingsScreenNavigation(navController = navController)
}

@Composable
fun SettingsMenu(onMenuSelected: (SettingsMenuModel) -> Unit) {
    val settingsMenu = remember {
        SettingsMenuData.menu
    }
    TvLazyColumn(modifier = Modifier.width(200.dp)) {
        items(settingsMenu.size) {
            val item = settingsMenu[it]
            FocusableItem(onClick = { onMenuSelected(item) }) {
                Text(text = item.text)
            }
        }
    }
}


@Preview
@Composable
fun SettingsScreenPrev() {
    SettingsScreen()
}