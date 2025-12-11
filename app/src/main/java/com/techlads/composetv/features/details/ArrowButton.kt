package com.techlads.composetv.features.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

@Composable
fun ArrowDownButton(modifier: Modifier = Modifier,
                    text: String,
                    icon: ImageVector,
                    onClick: () -> Unit) {
    Column(modifier = Modifier
        .clickable {
            onClick()
        }
        .clip(
            MaterialTheme.shapes.medium
        )
        .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text, modifier = modifier, style = MaterialTheme.typography.titleMedium)
        Icon(icon, contentDescription = text)
    }
}

@Preview
@Composable
private fun ArrowDownButtonPreview() {
    ArrowDownButton(
        text = "Arrow Down",
        icon = Icons.Default.KeyboardDoubleArrowDown,
        onClick = { }
    )
}