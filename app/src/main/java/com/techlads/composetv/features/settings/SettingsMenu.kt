package com.techlads.composetv.features.settings

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techlads.composetv.features.settings.data.SettingsMenuModel
import com.techlads.composetv.features.settings.navigation.SettingsScreens

@Composable
fun SettingsMenu(
    modifier: Modifier = Modifier,
    viewModel: SettingsMenuViewModel = hiltViewModel<SettingsMenuViewModel>(),
    onMenuSelected: (SettingsMenuModel) -> Unit
) {
    val settingsMenu = remember {
        SettingsMenuData.menu
    }

    LazyColumn(modifier = modifier.width(200.dp)) {
        items(settingsMenu) { item ->
            SettingsMenuItem(item) {
                when (item.navigation) {
                    SettingsScreens.Logout.title -> {
                        viewModel.logout()
                    }
                    else -> {
                        onMenuSelected(item)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingsMenuPrev() {
    SettingsMenu {}
}
