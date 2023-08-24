@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.features.login.withEmailPassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.techlads.composetv.theme.ComposeTvTheme
import com.techlads.composetv.widgets.TvButton

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun LoginPage(
    onLoginClick: (user: String, psw: String) -> Unit,
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
            keyboardType = KeyboardType.Password,
        ) { password.value = it }
        Spacer(modifier = Modifier.height(40.dp))

        TvButton(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            onClick = { onLoginClick(username.value, password.value) },
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "LOGIN",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                ),
            )
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
