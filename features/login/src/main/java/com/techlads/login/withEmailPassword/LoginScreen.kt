package com.techlads.login.withEmailPassword

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.MaterialTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    goToHomeScreen: () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        LoginPageContent { _, _ ->
            goToHomeScreen()
        }
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun LoginScreenPrev() {
    MaterialTheme {
        LoginScreen(Modifier.fillMaxSize()) {
        }
    }
}
