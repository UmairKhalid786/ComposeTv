package com.techlads.composetv.features.cast

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import coil.compose.AsyncImage
import com.techlads.composetv.theme.ComposeTvTheme


// create a simple card to show person's image and name

@Composable
fun PersonCard(
    person: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        onClick = { /* No-op */ },
        scale = CardDefaults.scale(1f, focusedScale = 1.01f)
    ) {
        AsyncImage(
            model = person,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.aspectRatio(0.7f)
        )
    }
}

@PreviewScreenSizes
@Composable
private fun PersonCardPreview() {
    ComposeTvTheme {
        PersonCard(
            person = "https://via.placeholder.com/150",
            modifier = Modifier
                .width(150.dp)
        )
    }
}