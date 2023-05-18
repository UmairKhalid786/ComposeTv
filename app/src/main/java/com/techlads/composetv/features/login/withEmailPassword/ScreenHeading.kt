package com.techlads.composetv.features.login.withEmailPassword

import androidx.tv.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun ScreenHeading(heading: String) {
    Text(
        text = heading, style = TextStyle(
            fontSize = 55.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Thin
        )
    )
}

@Preview
@Composable
fun ScreenHeadingPrev() {
    ScreenHeading("LOGIN")
}