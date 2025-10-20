package com.techlads.login.withToken

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DeviceTokenAuthenticationScreen(
    modifier: Modifier = Modifier,
    onSkip: () -> Unit,
    onLogin: () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        DeviceTokenAuthenticationContent(token = "OTF2", "www.google.com", skip = onSkip) {
            onLogin()
        }
    }
}


@Preview
@Composable
private fun DeviceTokenAuthenticationScreenPreview() {
    MaterialTheme {
        DeviceTokenAuthenticationScreen(Modifier.fillMaxSize(), onSkip = {}) {

        }
    }
}