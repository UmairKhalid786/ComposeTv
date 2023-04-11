package com.techlads.composetv.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.Text
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.techlads.composetv.settings.data.SettingsMenuModel
import com.techlads.composetv.widgets.FocusableItem
import com.techlads.composetv.settings.navigation.NestedSettingsScreenNavigation

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SettingsScreen() {
    val navController = rememberAnimatedNavController()

    Row(Modifier.padding(16.dp).fillMaxSize()) {
        SettingsMenu(Modifier.fillMaxHeight().padding(vertical = 16.dp)) {
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
fun SettingsMenu(modifier: Modifier, onMenuSelected: (SettingsMenuModel) -> Unit) {
    val settingsMenu = remember {
        SettingsMenuData.menu
    }
    TvLazyColumn(modifier = modifier.width(200.dp)) {
        items(settingsMenu.size) {
            val item = settingsMenu[it]
            FocusableItem(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),onClick = { onMenuSelected(item) }) {
                Text(text = item.text, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp))
            }
        }
    }
}


@Preview
@Composable
fun SettingsScreenPrev() {
    SettingsScreen()
}