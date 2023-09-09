@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.features.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.techlads.composetv.features.settings.data.SettingsMenuModel
import com.techlads.composetv.widgets.FocusableItem

@Composable
fun SettingsMenuItem(item: SettingsMenuModel, onMenuSelected: (SettingsMenuModel) -> Unit) {
    FocusableItem(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = { onMenuSelected(item) }) {
        Text(
            text = item.text, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Preview
@Composable
fun SettingsMenuItemPrev() {
    SettingsMenuItem(SettingsMenuModel("Menu", "")) {}
}
