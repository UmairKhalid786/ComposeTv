@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.features.settings.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.composetv.features.settings.data.SettingsMenuModel

@Composable
fun PreferencesContainer(
    modifier: Modifier = Modifier,
    preference: SettingsMenuModel,
    content: @Composable () -> Unit,
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(64.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ContentHeading(title = preference.text)
            Spacer(modifier = Modifier.padding(8.dp))
            Spacer(
                modifier = Modifier.height(1.dp).fillMaxWidth()
                    .background(color = LocalContentColor.current.copy(alpha = 0.1f)),
            )
            Spacer(modifier = Modifier.padding(8.dp))
            content()
        }
    }
}

@Composable
fun ContentHeading(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
            .wrapContentWidth(),
    )
}

@Preview
@Composable
fun ContentHeadingPrev() {
    ContentHeading(SettingsMenuModel("Profile", "").text)
}
