@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.features.home.navigation.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Tab
import androidx.tv.material3.TabDefaults
import androidx.tv.material3.TabRow
import androidx.tv.material3.TabRowScope
import androidx.tv.material3.Text
import com.techlads.composetv.R
import com.techlads.composetv.features.home.leftmenu.data.MenuData
import com.techlads.composetv.features.home.leftmenu.model.MenuItem
import com.techlads.composetv.features.home.navigation.NestedScreens
import com.techlads.composetv.features.settings.screens.profile.ProfilePicture

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeTopBar(
    content: @Composable () -> Unit,
    selectedId: String = MenuData.menuItems.first().id,
    onMenuSelected: ((menuItem: MenuItem) -> Unit)?
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            TabRow(selectedTabIndex = selectedTabIndex,
                indicator = { _, _ ->
                    Box(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .background(Color.Transparent)
                    )
                }
            ) {
                NavigationTabItem(item = MenuData.settingsItem,
                    isSelected = selectedId == MenuData.settingsItem.id,
                    onMenuSelected = {
//                        selectedTabIndex = index
                        onMenuSelected?.invoke(MenuData.settingsItem)
                    })
                MenuData.topBarMenuItems.forEachIndexed { index, menuItem ->
                    NavigationTabItem(
                        item = menuItem,
                        isSelected = selectedId == menuItem.id,
                        onMenuSelected = {
                            selectedTabIndex = index
                            onMenuSelected?.invoke(it)
                        })
                }
            }
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight(700))
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TabRowScope.NavigationTabItem(
    item: MenuItem,
    isSelected: Boolean,
    enabled: Boolean = true,
    onMenuSelected: ((menuItem: MenuItem) -> Unit)?
) {
    Tab(
        selected = isSelected, enabled = enabled,
        onFocus = {
            onMenuSelected?.invoke(item)
        },
        colors = TabDefaults.pillIndicatorTabColors(
            selectedContentColor = Color.Black,
            focusedSelectedContentColor = if (item.id != NestedScreens.Settings.title) Color.Black else Color.White
        ),
        modifier = Modifier
            .clip(if (item.id == NestedScreens.Settings.title) CircleShape else MaterialTheme.shapes.small)
            .background(
                if (isSelected) Color.White.copy(
                    alpha = 0.7f
                ) else Color.Transparent
            )
    ) {
        when (item.id) {
            NestedScreens.Search.title ->
                Box(modifier = Modifier.size(36.dp), contentAlignment = Alignment.Center) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = item.icon ?: return@Tab,
                        contentDescription = item.text,
                    )
                }

            NestedScreens.Settings.title -> Box(modifier = Modifier.size(36.dp)) {
                ProfilePicture()
            }

            else -> Text(
                item.text,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun HomeTopBarPrev() {
    HomeTopBar(content = {
        Text(text = "Hello World")
    }, onMenuSelected = null)
}
