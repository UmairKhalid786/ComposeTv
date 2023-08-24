package com.techlads.composetv.features.login.withToken

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.techlads.composetv.navigation.Screens

@Composable
fun DeviceTokenAuthenticationScreen(
    modifier: Modifier = Modifier,
    goToScreen: (Screens) -> Unit,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        DeviceTokenAuthenticationContent(token = "OTF2", "www.google.com") {
            goToScreen(Screens.Login)
        }
    }
}
