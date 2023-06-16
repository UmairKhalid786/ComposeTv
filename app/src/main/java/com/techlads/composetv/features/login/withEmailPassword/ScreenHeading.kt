package com.techlads.composetv.features.login.withEmailPassword

import androidx.compose.material3.MaterialTheme
import androidx.tv.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ScreenHeading(heading: String) {
    Text(
        text = heading, style = MaterialTheme.typography.headlineLarge
    )
}

@Preview
@Composable
fun ScreenHeadingPrev() {
    ScreenHeading("LOGIN")
}