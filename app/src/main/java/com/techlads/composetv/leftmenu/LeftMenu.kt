package com.techlads.composetv.leftmenu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import com.techlads.composetv.leftmenu.model.MenuItem
import com.techlads.composetv.navigation.Screens
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.Comment
import kotlinx.coroutines.delay

@Composable
fun LeftMenu(
    menuItems: List<MenuItem>,
    defaultFocus: Int = 0,
    onMenuSelected: (screen: Screens) -> Unit
) {
    val menus = remember {
        menuItems
    }

    var isExpanded by remember { mutableStateOf(false) }

    val width = animateDpAsState(
        targetValue = if (isExpanded) 200.dp else 50.dp,
    )

    val requester = remember {
        FocusRequester()
    }

    LaunchedEffect(key1 = Unit){
        requester.requestFocus()
        delay(2000)
        isExpanded = true
    }

    Box {
        TvLazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .width(width.value)
        ) {
            items(menus.size) {
                val item = menus[it]
                if (it == defaultFocus) {
                    LeftMenuItem(
                        requester = requester,
                        menuItem = item,
                        modifier = Modifier.fillParentMaxWidth(),
                        expanded = isExpanded
                    )
                } else {
                    LeftMenuItem(
                        menuItem = item,
                        modifier = Modifier.fillParentMaxWidth(),
                        expanded = isExpanded
                    )
                }
            }
        }
    }
}

@Composable
fun LeftMenuItem(
    modifier: Modifier = Modifier,
    menuItem: MenuItem,
    expanded: Boolean = false,
    requester: FocusRequester = FocusRequester()
) {
    val padding = animateDpAsState(
        animationSpec = keyframes {
            this.delayMillis = 100
        },
        targetValue = if (expanded) 8.dp else 4.dp,
    )
    var isFocused by remember { mutableStateOf(false) }

    Row(
        modifier
            .focusRequester(requester)
            .onFocusChanged {
                isFocused = it.isFocused
            }
            .focusable()
            .background(
                if (isFocused)
                    MaterialTheme.colorScheme.onSurface
                else
                    MaterialTheme.colorScheme.surface, shape = ShapeDefaults.Medium
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        menuItem.icon?.let {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = it,
                contentDescription = menuItem.text,
                tint = if (!isFocused)
                    MaterialTheme.colorScheme.onSurface
                else
                    MaterialTheme.colorScheme.surface
            )
            Spacer(modifier = Modifier.padding(horizontal = padding.value))
        }
        AnimatedVisibility(visible = expanded, modifier = Modifier.height(20.dp)) {
            Text(
                text = menuItem.text, color = if (!isFocused)
                    MaterialTheme.colorScheme.onSurface
                else
                    MaterialTheme.colorScheme.surface,
                maxLines = 1
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