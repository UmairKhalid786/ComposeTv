package com.techlads.composetv.features.cast

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.MaterialTheme
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
        border = CardDefaults.border(
           focusedBorder = Border(
               border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.onSurface),
               shape = CircleShape
           )
        ),
        shape = CardDefaults.shape(CircleShape),
        onClick = { /* No-op */ },
        scale = CardDefaults.scale(1f, focusedScale = 1.01f)
    ) {
        AsyncImage(
            model = person,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.aspectRatio(1f)
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