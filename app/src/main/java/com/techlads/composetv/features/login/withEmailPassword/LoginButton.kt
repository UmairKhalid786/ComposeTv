@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.features.login.withEmailPassword

import androidx.compose.foundation.layout.*
import androidx.tv.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api

@Composable
fun LoginButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "LOGIN", style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
            )
        )
    }
}


@Preview
@Composable
fun LoginButtonPrev() {
    LoginButton(Modifier.wrapContentSize()) {

    }
}