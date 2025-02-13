package com.techlads.composetv.features.home.navigation.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
import com.techlads.composetv.features.home.leftmenu.model.isCircleIcon
import com.techlads.composetv.features.home.navigation.NestedScreens
import com.techlads.composetv.features.settings.screens.profile.ProfilePicture

@Composable
fun HomeTopBar(
    content: @Composable () -> Unit,
    minimiseTopBar: Boolean = false,
    selectedId: String = MenuData.menuItems.first().id,
    onMenuSelected: ((menuItem: MenuItem) -> Unit)?
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val searchItem = MenuData.menuItems[1]

    Column {
        AnimatedVisibility(minimiseTopBar.not()) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                TabRow(selectedTabIndex = selectedTabIndex, indicator = { _, _ ->
                    Box(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .background(Color.Transparent)
                    )
                }) {
                    NavigationTabItem(item = MenuData.settingsItem,
                        isSelected = selectedId == MenuData.settingsItem.id,
                        onMenuSelected = {
                            onMenuSelected?.invoke(MenuData.settingsItem)
                        })
                    MenuData.menuItems.forEachIndexed { index, menuItem ->
                        if (menuItem.id != NestedScreens.Search.title) {
                            NavigationTabItem(item = menuItem,
                                isSelected = selectedId == menuItem.id,
                                onMenuSelected = {
                                    selectedTabIndex = index
                                    onMenuSelected?.invoke(it)
                                })
                        }
                    }
                    NavigationTabItem(
                        item = searchItem,
                        isSelected = selectedId == searchItem.id,
                        onMenuSelected = {
                            onMenuSelected?.invoke(searchItem)
                        })
                }
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight(700))
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }
}

@Composable
fun TabRowScope.NavigationTabItem(
    item: MenuItem,
    isSelected: Boolean,
    enabled: Boolean = true,
    onMenuSelected: ((menuItem: MenuItem) -> Unit)?
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Tab(
        selected = isSelected,
        enabled = enabled,
        onClick = {
            onMenuSelected?.invoke(item)
        },
        onFocus = {},
        interactionSource = interactionSource,
        colors = TabDefaults.pillIndicatorTabColors(
            selectedContentColor = MaterialTheme.colorScheme.onSurface,
            focusedContentColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .background(
                when {
                    isFocused -> MaterialTheme.colorScheme.inverseSurface
                    isSelected -> MaterialTheme.colorScheme.surfaceVariant.copy(
                        alpha = 0.5f
                    )
                    else -> Color.Transparent
                }, if (item.isCircleIcon()) CircleShape else MaterialTheme.shapes.extraLarge
            )
    ) {
        when (item.id) {
            NestedScreens.Search.title -> Box(
                modifier = Modifier.size(36.dp),
                contentAlignment = Alignment.Center
            ) {
                item.icon?.let { icon ->
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = icon,
                        contentDescription = item.text,
                    )
                }
            }

            NestedScreens.Settings.title -> Box(modifier = Modifier.size(36.dp)) {
                ProfilePicture()
            }

            else -> Text(
                item.text, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun HomeTopBarPrev() {
    HomeTopBar(content = {
        Text(text = "Hello World")
    }, onMenuSelected = null)
}
