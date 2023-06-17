@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.features.home.leftmenu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.techlads.composetv.features.home.leftmenu.model.MenuItem

@Composable
fun LeftMenuItem(
    modifier: Modifier = Modifier,
    menuItem: MenuItem,
    expanded: Boolean = false,
    requester: FocusRequester? = null,
    onMenuFocused: ((menuItem: MenuItem, isFocused: Boolean) -> Unit)? = null,
    onMenuSelected: ((menuItem: MenuItem) -> Unit)? = null,
) {
    val padding = animateDpAsState(
        animationSpec = keyframes {
            this.delayMillis = 100
        },
        targetValue = if (expanded) 8.dp else 4.dp,
    )

    Surface(
        onClick = { onMenuSelected?.invoke(menuItem) },
        modifier = modifier
            .onFocusChanged { focusState ->
                onMenuFocused?.invoke(menuItem, focusState.isFocused)
            }
            .focusRequester(requester ?: FocusRequester())
            .padding(bottom = 8.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            menuItem.icon?.let {
                androidx.tv.material3.Icon(
                    imageVector = it,
                    contentDescription = menuItem.text,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.padding(horizontal = padding.value))
            }
            AnimatedVisibility(visible = expanded, modifier = Modifier.height(20.dp)) {
                Text(
                    text = menuItem.text,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
