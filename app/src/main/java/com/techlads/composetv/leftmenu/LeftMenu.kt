package com.techlads.composetv.leftmenu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import com.techlads.composetv.leftmenu.model.MenuItem
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.CogSolid
import compose.icons.lineawesomeicons.Comment
import compose.icons.lineawesomeicons.TvSolid
import kotlinx.coroutines.delay

@Composable
fun LeftMenu(
    modifier: Modifier = Modifier,
    menuItems: List<MenuItem>,
    defaultFocus: Int = 0,
    onMenuFocused: ((menuItem: MenuItem) -> Unit)? = null,
    onMenuSelected: ((menuItem: MenuItem) -> Unit)? = null,
) {
    var isExpanded by remember { mutableStateOf(false) }

    val width = animateDpAsState(
        targetValue = if (isExpanded) 200.dp else 50.dp,
    )

    val requester = remember {
        FocusRequester()
    }

    LaunchedEffect(key1 = Unit) {
        requester.requestFocus()
        delay(2000)
        isExpanded = true
    }

    Box(
        modifier = modifier
            .background(Color.DarkGray.copy(alpha = 0.2f))
            .width(200.dp)
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
                menuItem = MenuItem("", "Settings", LineAwesomeIcons.CogSolid),
                modifier = Modifier.fillMaxWidth(),
                expanded = isExpanded,
                onMenuFocused = onMenuFocused,
                onMenuSelected = onMenuSelected
            )
        }
    }
}

@Composable
fun MenuHeader(expanded: Boolean = true) {
    val animatedAlpha = animateFloatAsState(
        animationSpec = keyframes {
            this.delayMillis = 500
            this.durationMillis = 500
        },
        targetValue = if (expanded) 1f else 0f,
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = LineAwesomeIcons.TvSolid,
            contentDescription = "App icon"
        )
        Text(text = "Compose Tv", fontWeight = FontWeight.Thin, fontSize = 16.sp , modifier = Modifier.alpha(animatedAlpha.value), maxLines = 1)
    }
}

@Composable
fun CenteredMenu(
    modifier: Modifier = Modifier,
    requester: FocusRequester = FocusRequester(),
    menuItems: List<MenuItem>,
    defaultFocus: Int,
    isExpanded: Boolean = false,
    onMenuFocused: ((menuItem: MenuItem) -> Unit)? = null,
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

@Composable
fun LeftMenuItem(
    modifier: Modifier = Modifier,
    menuItem: MenuItem,
    expanded: Boolean = false,
    requester: FocusRequester = FocusRequester(),
    onMenuFocused: ((menuItem: MenuItem) -> Unit)? = null,
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
            .clickable {
                onMenuSelected?.invoke(menuItem)
            }
            .focusRequester(requester)
            .onFocusChanged {
                isFocused = it.isFocused
                onMenuFocused?.invoke(menuItem)
            }
            .focusable()
            .background(
                if (isFocused) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.surface, shape = ShapeDefaults.Small
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically) {

        menuItem.icon?.let {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = it,
                contentDescription = menuItem.text,
                tint = if (!isFocused) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.surface
            )
            Spacer(modifier = Modifier.padding(horizontal = padding.value))
        }

        AnimatedVisibility(visible = expanded, modifier = Modifier.height(20.dp)) {
            Text(
                text = menuItem.text, color = if (!isFocused) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.surface, maxLines = 1
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