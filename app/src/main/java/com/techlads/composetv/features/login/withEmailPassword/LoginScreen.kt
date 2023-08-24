package com.techlads.composetv.features.login.withEmailPassword

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techlads.composetv.theme.ComposeTvTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    goToHomeScreen: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 68.dp),
        contentAlignment = Alignment.Center,
    ) {
        LoginPage { _, _ ->
            goToHomeScreen()
        }
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun LoginScreenPrev() {
    ComposeTvTheme {
        LoginScreen(Modifier.fillMaxSize()) {
        }
    }
}
