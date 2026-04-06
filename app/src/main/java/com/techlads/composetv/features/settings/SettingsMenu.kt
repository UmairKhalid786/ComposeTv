package com.techlads.composetv.features.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.auth.AuthState
import com.techlads.composetv.features.settings.data.SettingsMenuModel
import com.techlads.composetv.features.settings.navigation.SettingsScreens

@Composable
fun SettingsMenu(
    modifier: Modifier = Modifier,
    viewModel: SettingsMenuViewModel = hiltViewModel<SettingsMenuViewModel>(),
    onMenuSelected: (SettingsMenuModel) -> Unit
) {
    val settingsMenu = remember {
        SettingsMenuData.menu
    }
    val authState by viewModel.authState.collectAsStateWithLifecycle()

    LazyColumn(modifier = modifier.width(200.dp)) {
        item {
            SettingsSessionSummary(authState = authState)
        }
        items(settingsMenu) { item ->
            SettingsMenuItem(item) {
                when (item.navigation) {
                    SettingsScreens.Logout.title -> {
                        viewModel.logout()
                    }
                    else -> {
                        onMenuSelected(item)
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsSessionSummary(authState: AuthState) {
    val status = when (authState) {
        AuthState.Loading -> "Checking session"
        is AuthState.LoggedIn -> "Logged in"
        AuthState.LoggedOut -> "Logged out"
    }
    val username = (authState as? AuthState.LoggedIn)?.user?.name ?: (authState as? AuthState.LoggedIn)?.user?.id

    Text(
        text = "Account",
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        style = MaterialTheme.typography.titleMedium,
    )
    Text(
        text = status,
        modifier = Modifier.padding(horizontal = 16.dp),
        style = MaterialTheme.typography.bodyMedium,
    )
    Text(
        text = username ?: "No active TMDB session",
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
        style = MaterialTheme.typography.bodySmall,
        color = LocalContentColor.current.copy(alpha = 0.7f),
    )
}

@Preview
@Composable
fun SettingsMenuPrev() {
    SettingsMenu {}
}
