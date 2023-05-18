package com.techlads.composetv.features.login.withEmailPassword

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.tv.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.techlads.composetv.theme.AppTheme
import com.techlads.composetv.theme.LightBlue

@Composable
fun LoginButton(onClick: () -> Unit) {

    val defaultColor = remember {
        Color.White.copy(0.6F)
    }
    val selectedColor = remember {
        LightBlue
    }
    val selectionColor = remember { mutableStateOf(defaultColor) }

    Box(
        modifier = Modifier
            .width(300.dp)
            .padding(40.dp, 0.dp, 40.dp, 0.dp)
    ) {
        Button(
            onClick = onClick,
            shape = AppTheme.midShape,
            colors = ButtonDefaults.buttonColors(selectionColor.value),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .onFocusChanged { state ->
                    selectionColor.value = if (state.isFocused) {
                        selectedColor
                    } else {
                        defaultColor
                    }
                }
                .focusable(true)
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