package com.techlads.composetv.leftmenu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.tv.foundation.lazy.list.TvLazyColumn
import com.techlads.composetv.leftmenu.model.MenuItem

@Composable
fun CenteredMenu(
    modifier: Modifier = Modifier,
    requester: FocusRequester = FocusRequester(),
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
        items(menus.size) {
            val item = menus[it]
            if (it == defaultFocus) {
                LeftMenuItem(
                    requester = requester,
                    menuItem = item,
                    modifier = Modifier.fillParentMaxWidth(),
                    expanded = isExpanded,
                    onMenuFocused = onMenuFocused,
                    onMenuSelected = onMenuSelected
                )
            } else {
                LeftMenuItem(
                    menuItem = item,
                    modifier = Modifier.fillParentMaxWidth(),
                    expanded = isExpanded,
                    onMenuFocused = onMenuFocused,
                    onMenuSelected = onMenuSelected
                )
            }
        }
    }
}