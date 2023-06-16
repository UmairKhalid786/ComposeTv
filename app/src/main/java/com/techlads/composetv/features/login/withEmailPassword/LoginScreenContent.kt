package com.techlads.composetv.features.login.withEmailPassword

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techlads.composetv.theme.ComposeTvTheme

@Composable
fun LoginPage(
    onLoginClick: (user: String, psw: String) -> Unit
) {
    Column(
        modifier = Modifier
            .width(320.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        ScreenHeading("LOGIN")
        Spacer(modifier = Modifier.height(20.dp))
        TvTextField(value = username.value, label = "Username") {
            username.value = it
        }
        Spacer(modifier = Modifier.height(20.dp))
        TvTextField(
            value = password.value,
            label = "Password",
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        ) { password.value = it }
        Spacer(modifier = Modifier.height(40.dp))

        LoginButton(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            onLoginClick(username.value, password.value)
        }
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun LoginPagePrev() {
    ComposeTvTheme {
        LoginPage { u, p ->

        }
    }
}
