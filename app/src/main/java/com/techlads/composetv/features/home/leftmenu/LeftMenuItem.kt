package com.techlads.composetv.features.home.leftmenu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text
import com.techlads.composetv.features.home.leftmenu.model.MenuItem

@Composable
fun LeftMenuItem(
    modifier: Modifier = Modifier,
    menuItem: MenuItem,
    expanded: Boolean = false,
    requester: FocusRequester = FocusRequester(),
    onMenuFocused: ((menuItem: MenuItem, isFocused: Boolean) -> Unit)? = null,
    onMenuSelected: ((menuItem: MenuItem) -> Unit)? = null,
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
            .padding(vertical = 4.dp)
            .focusRequester(requester)
            .onFocusChanged {
                isFocused = it.isFocused
                onMenuFocused?.invoke(menuItem, it.isFocused)
            }
            .focusable()
            .background(
                color = if (isFocused) colorScheme.surface else colorScheme.onSurface,
                shape = ShapeDefaults.Small
            )
            .clickable {
                onMenuSelected?.invoke(menuItem)
            }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically) {

        menuItem.icon?.let {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = it,
                contentDescription = menuItem.text,
                tint = if (isFocused)
                    colorScheme.onSurface
                else
                    colorScheme.surface
            )
            Spacer(modifier = Modifier.padding(horizontal = padding.value))
        }

        AnimatedVisibility(visible = expanded, modifier = Modifier.height(20.dp)) {
            Text(
                text = menuItem.text,
                color = if (isFocused) colorScheme.onSurface else colorScheme.surface,
                maxLines = 1
            )
        }
    }
}
