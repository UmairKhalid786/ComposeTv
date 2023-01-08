@file:OptIn(ExperimentalMaterial3Api::class)

package com.techlads.composetv.login

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techlads.composetv.theme.AppTheme

@Composable
fun LoginPage(
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onLoginClick: (user: String, psw: String) -> Unit
) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val username = remember { mutableStateOf(TextFieldValue("")) }
        val password = remember { mutableStateOf(TextFieldValue("")) }

        Text(
            text = "LOGIN",
            style = TextStyle(
                fontSize = 55.sp, fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Thin
            )
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            textStyle = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Thin
            ),
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = { username.value = it },
            modifier = Modifier
                .onFocusChanged { state ->
                    if (state.isFocused) {
                        println("Username")
                    }
                }
                .focusable(true, interactionSource)
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            textStyle = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Thin
            ),
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it },
            modifier = Modifier
                .onFocusChanged { state ->
                    if (state.isFocused) {
                        println("password")
                    }
                }
                .focusable(true, interactionSource)
        )

        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .width(300.dp)
                .padding(40.dp, 0.dp, 40.dp, 0.dp)
        ) {
            Button(
                onClick = { onLoginClick(username.value.text, password.value.text) },
                shape = AppTheme.midShape,
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .onFocusChanged { state ->
                        if (state.isFocused) {
                            println("login")
                        }
                    }
                    .focusable(true, interactionSource)
            ) {
                Text(
                    text = "LOGIN", style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light
                    )
                )
            }
        }
    }
}