package com.techlads.composetv.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreenContent(){
    Box(contentAlignment = Alignment.Center) {
        Text(text = "Home Screen")
    }
}

@Preview
@Composable
fun HomeScreenContentPrev() {
    HomeScreenContent()
}