package com.techlads.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.Text


@Composable
fun LandingScreen(
    onApiKeyEntered: (String) -> Unit, onGuestMode: () -> Unit
) {

    var inputKey by remember { mutableStateOf("") }
    val apiKeyUrl = "https://developer.themoviedb.org/reference/getting-started"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome! To access all features, please provide your TMDB API key.")
        Spacer(Modifier.height(8.dp))
        Text("Don't have one? Visit:")
        Text(
            text = apiKeyUrl, color = Color.Cyan, modifier = Modifier.clickable {
                // On TV, show this URL or provide a QR code for convenience
            })
        Spacer(Modifier.height(8.dp))
        Text("You can keep your key in the app or enter it each time.")
        Spacer(Modifier.height(16.dp))
        TextField(
            value = inputKey,
            onValueChange = { inputKey = it },
            label = { Text("TMDB API Key") })
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { onApiKeyEntered(inputKey) }, enabled = inputKey.isNotBlank()
        ) {
            Text("Continue")
        }
        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = onGuestMode) {
            Text("Enter Guest Mode")
        }
    }
}

@Preview
@Composable
private fun LandingScreenPreview() {
    MaterialTheme {
        LandingScreen(onApiKeyEntered = {}, onGuestMode = {})
    }
}