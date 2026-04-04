package com.techlads.login.withEmailPassword

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.MaterialTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        LoginPageContent(
            isLoading = uiState.isLoading,
            errorMessage = uiState.errorMessage,
            onInputChanged = viewModel::clearError,
            onLoginClick = viewModel::login,
        )
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun LoginScreenPrev() {
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            LoginPageContent(onLoginClick = { _, _ -> })
        }
    }
}
