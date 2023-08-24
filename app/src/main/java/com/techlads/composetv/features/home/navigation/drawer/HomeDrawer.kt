@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.features.home.navigation.drawer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.techlads.composetv.features.home.leftmenu.MenuHeader
import com.techlads.composetv.features.home.leftmenu.data.MenuData
import com.techlads.composetv.features.home.leftmenu.model.MenuItem

val navigationRow: @Composable (
    drawerValue: DrawerValue,
    menu: MenuItem,
    modifier: Modifier,
    onMenuSelected: ((menuItem: MenuItem) -> Unit)?,
) -> Unit =
    { drawerValue, menu, modifier, onMenuSelected ->
        val padding = animateDpAsState(
            animationSpec = keyframes {
                this.delayMillis = 100
            },
            targetValue = if (drawerValue == DrawerValue.Open) 4.dp else 0.dp,
        )

        Surface(
            onClick = { onMenuSelected?.invoke(menu) },
            modifier =
            modifier
                .padding(vertical = 4.dp)
                .then(if (drawerValue == DrawerValue.Open) modifier.width(170.dp) else modifier),
        ) {
            Row(
                Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                menu.icon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = menu.text,
                        modifier = Modifier.size(16.dp),
                    )
                    Spacer(modifier = Modifier.padding(horizontal = padding.value))
                }
                AnimatedVisibility(
                    visible = drawerValue == DrawerValue.Open,
                    modifier = Modifier.height(20.dp),
                ) {
                    Text(
                        text = menu.text,
                        maxLines = 1,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }

@Composable
fun HomeDrawer(content: @Composable () -> Unit, onMenuSelected: ((menuItem: MenuItem) -> Unit)?) {
    ModalNavigationDrawer(
        drawerContent = { drawer ->
            Column(
                Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                MenuHeader(expanded = drawer == DrawerValue.Open)
                Spacer(modifier = Modifier.height(16.dp))
                MenuData.menuItems.forEach {
                    navigationRow(drawer, it, Modifier) {
                        onMenuSelected?.invoke(it)
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                navigationRow(drawer, MenuData.settingsItem, Modifier) {
                    onMenuSelected?.invoke(it)
                }
            }
        },
        content = content,
    )
}

@Preview
@Composable
fun HomeDrawerPrev() {
    HomeDrawer(content = {
        Text(text = "Hello World")
    }, onMenuSelected = null)
}
