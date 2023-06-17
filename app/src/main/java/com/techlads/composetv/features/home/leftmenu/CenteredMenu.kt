package com.techlads.composetv.features.home.leftmenu

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import com.techlads.composetv.features.home.leftmenu.model.MenuItem

@Composable
fun CenteredMenu(
    modifier: Modifier = Modifier,
    requester: FocusRequester = remember { FocusRequester() },
    menuItems: List<MenuItem>,
    defaultFocus: Int,
    isExpanded: Boolean = false,
    onMenuFocused: ((menuItem: MenuItem, isFocused: Boolean) -> Unit)? = null,
    onMenuSelected: ((menuItem: MenuItem) -> Unit)? = null,
) {
    val menus = remember {
        menuItems
    }

    TvLazyColumn(
        modifier
    ) {
        item {
            MenuHeader(isExpanded)
            Spacer(modifier = Modifier.height(32.dp))
        }
        items(menus.size) {
            LeftMenuItem(
                requester = if (it == defaultFocus) { requester } else { null },
                menuItem = menus[it],
                modifier = Modifier.fillParentMaxWidth(),
                expanded = isExpanded,
                onMenuFocused = onMenuFocused,
                onMenuSelected = onMenuSelected
            )
        }
    }
}