package com.techlads.composetv.features.login.withEmailPassword

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.SurfaceDefaults
import androidx.tv.material3.Text

@Composable
fun TvTextField(
    value: String,
    placeholder: String,
    mutableInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    val isFocused by mutableInteractionSource.collectIsFocusedAsState()

    val container by rememberUpdatedState(if (isFocused) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.surface)

    BasicTextField(
        modifier = modifier,
        value = value,
        decorationBox = {
            Surface(colors = SurfaceDefaults.colors(containerColor = container), shape = MaterialTheme.shapes.medium) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        Text(text = placeholder, modifier.graphicsLayer { alpha = 0.6f }, style = MaterialTheme.typography.bodyMedium)
                    }
                    it() // This is where the actual BasicTextField will be placed
                }
            }
        },
        onValueChange = onValueChange,
        interactionSource = mutableInteractionSource,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
    )
}

@Preview
@Composable
fun TvTextFieldPrev() {
    TvTextField("Test", "Enter Test") {}
}
