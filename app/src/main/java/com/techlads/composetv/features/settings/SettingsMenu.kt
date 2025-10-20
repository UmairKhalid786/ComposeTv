package com.techlads.composetv.features.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Switch
import androidx.tv.material3.Text
import com.techlads.composetv.features.home.NavigationEvent
import com.techlads.composetv.features.settings.data.SettingsMenuModel
import com.techlads.uicomponents.widgets.FocusableItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SettingsMenu(
    modifier: Modifier = Modifier,
    usedTopBar: StateFlow<NavigationEvent>,
    navigationBar: (NavigationEvent) -> Unit,
    onMenuSelected: (SettingsMenuModel) -> Unit
) {
    val settingsMenu = remember {
        SettingsMenuData.menu
    }

    LazyColumn(modifier = modifier.width(200.dp)) {
        items(settingsMenu.size) {
            val item = settingsMenu[it]
            SettingsMenuItem(item) {
                onMenuSelected(item)
            }
        }
        item {
            val menu by usedTopBar.collectAsState()
            FocusableItem(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                onClick = {
                    navigationBar(
                        if (menu == NavigationEvent.LeftMenu) {
                            NavigationEvent.TopBar
                        } else {
                            NavigationEvent.LeftMenu
                        }
                    )
                }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp)
                ) {
                    Text(
                        text = "Toggle Top Bar",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                    Switch(checked = menu == NavigationEvent.TopBar, onCheckedChange = {})
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingsMenuPrev() {
    SettingsMenu(usedTopBar = MutableStateFlow(NavigationEvent.TopBar), navigationBar = {}) {}
}
