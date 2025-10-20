package com.techlads.login.withEmailPassword

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.MaterialTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
    goToHomeScreen: () -> Unit,
) {
    val state by loginViewModel.crossFadeState.collectAsStateWithLifecycle()
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.CenterEnd,
    ) {
        state?.let {
            LoginPageContent(state = it) { _, _ ->
                goToHomeScreen()
            }
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
