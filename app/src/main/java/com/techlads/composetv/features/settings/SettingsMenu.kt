package com.techlads.composetv.features.settings

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import com.techlads.composetv.features.settings.data.SettingsMenuModel

@Composable
fun SettingsMenu(modifier: Modifier = Modifier, onMenuSelected: (SettingsMenuModel) -> Unit) {
    val settingsMenu = remember {
        SettingsMenuData.menu
    }
    TvLazyColumn(modifier = modifier.width(200.dp)) {
        items(settingsMenu.size) {
            val item = settingsMenu[it]
            SettingsMenuItem(item) {
                onMenuSelected(item)
            }
        }
    }
}

@Preview
@Composable
fun SettingsMenuPrev() {
    SettingsMenu {

    }
}