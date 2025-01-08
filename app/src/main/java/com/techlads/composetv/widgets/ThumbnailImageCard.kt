package com.techlads.composetv.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.composetv.theme.ComposeTvTheme

@Composable
fun ThumbnailImageCard(
    modifier: Modifier = Modifier,
    content: @Composable (BoxScope.() -> Unit),
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.small,
            ).aspectRatio(0.6f),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Preview
@Composable
fun ThumbnailImageCardPreview() {
    ComposeTvTheme {
        ThumbnailImageCard(
            Modifier
                .width(150.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = MaterialTheme.shapes.small,
                ),
        ) {
            Text(text = "1x1")
        }
    }
}
