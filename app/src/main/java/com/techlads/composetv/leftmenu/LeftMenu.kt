package com.techlads.composetv.leftmenu

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techlads.composetv.home.navigation.NestedScreens
import com.techlads.composetv.leftmenu.model.MenuItem
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.CogSolid
import compose.icons.lineawesomeicons.Comment
import kotlinx.coroutines.delay

@Composable
fun LeftMenu(
    modifier: Modifier = Modifier,
    menuItems: List<MenuItem>,
    defaultFocus: Int = 0,
    isExpanded: Boolean,
    onMenuFocused: ((menuItem: MenuItem, isFocused: Boolean) -> Unit)? = null,
    onMenuSelected: ((menuItem: MenuItem) -> Unit)? = null,
) {

    val width = animateDpAsState(
        targetValue = if (isExpanded) 200.dp else 50.dp,
    )

    val requester = remember {
        FocusRequester()
    }

    LaunchedEffect(key1 = Unit) {
        requester.requestFocus()
        delay(2000)
    }

    Box(
        modifier = modifier
            .background(Color.DarkGray.copy(alpha = 0.2f))
            .wrapContentWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .width(width.value)
        ) {
            MenuHeader(isExpanded)
            Spacer(modifier = Modifier.weight(1f))
            CenteredMenu(
                modifier = Modifier,
                requester,
                menuItems,
                defaultFocus,
                isExpanded,
                onMenuFocused = onMenuFocused,
                onMenuSelected = onMenuSelected
            )
            Spacer(modifier = Modifier.weight(1f))
            LeftMenuItem(
                menuItem = MenuItem(NestedScreens.Settings.title, "Settings", LineAwesomeIcons.CogSolid),
                modifier = Modifier.fillMaxWidth(),
                expanded = isExpanded,
                onMenuFocused = onMenuFocused,
                onMenuSelected = onMenuSelected
            )
        }
    }
}

@Preview
@Composable
fun LeftMenuItemPreview() {
    LeftMenuItem(menuItem = MenuItem("", "Menu example"))
}

@Preview
@Composable
fun LeftMenuItemPreviewWithIcon() {
    LeftMenuItem(menuItem = MenuItem("", "Menu example", LineAwesomeIcons.Comment))
}

@Preview
@Composable
fun MenuHeaderPreview() {
    MenuHeader(true)
}

@Preview
@Composable
fun MenuHeaderPreviewNotExpanded() {
    MenuHeader(false)
}