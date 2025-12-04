package com.techlads.composetv.features.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.techlads.composetv.R
import com.techlads.composetv.features.details.ProductDetailsState.Success
import com.techlads.composetv.theme.ComposeTvTheme
import com.techlads.uicomponents.widgets.TvButton
import kotlinx.coroutines.delay

@Composable
fun ProductDetailsScreen(
    viewModel: ProductViewModel,
    onBackPressed: () -> Unit,
    onPlayClick: () -> Unit
) {
    val uiDetails = viewModel.details.collectAsStateWithLifecycle()
    uiDetails.value?.let {
        TVProductDetailsContent(
            state = it,
            onBackPressed = onBackPressed,
            onPlayClick = onPlayClick
        )
    }
}

@Composable
private fun TVProductDetailsContent(
    state: ProductDetailsState,
    onBackPressed: () -> Unit,
    onPlayClick: () -> Unit
) {
    BackHandler(onBack = onBackPressed)
    when (state) {
        is Success -> TVProductDetails(state.details, onPlayClick)
        // ...handle other states if needed...
        else -> {}
    }
}

@Composable
private fun TVProductDetails(details: Details, onPlayClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val background = MaterialTheme.colorScheme.surface

        // Banner
        AsyncImage(
            model = details.background,
            contentDescription = "Product Banner",
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    drawContent()
                    drawRect(brush = Brush.horizontalGradient(
                        colors = listOf(
                            background.copy(alpha = 0.8f),
                            background.copy(alpha = 0.1f)
                        ),
                        startX = 0.0f,
                        endX = size.width
                    ))
                }
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Crop
        )

        // Foreground content
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.6f)
                .padding(start = 72.dp, end = 48.dp, top = 140.dp, bottom = 48.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // Title
            Text(
                text = details.title,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Info Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                TVInfoChip(text = details.releaseDate)
                Spacer(Modifier.width(16.dp))
                TVInfoChip(text = details.genres.joinToString())
                Spacer(Modifier.width(16.dp))
                TVRatingChip(rating = "8.3")
            }

            // Description
            Text(
                text = details.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Play Button
            val focusRequester = remember { FocusRequester() }
            LaunchedEffect(Unit) {
                delay(400)
                focusRequester.requestFocus()
            }

            TvButton(
                modifier = Modifier
                    .focusRequester(focusRequester),
                onClick = onPlayClick
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = stringResource(R.string.play),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun TVInfoChip(text: String) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                shape = MaterialTheme.shapes.small
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun TVRatingChip(rating: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                shape = MaterialTheme.shapes.small
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = "Rating",
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text = rating,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun DetailsScreenPrev() {
    ComposeTvTheme {
        TVProductDetailsContent(
            state = Success(
                details = Details(
                    title = "Hello world",
                    background = "www.test.image.jpg",
                    description = "This is a dummy movie",
                    releaseDate = "14 august 1994",
                    genres = List(5) { "Genre $it" })
            ), onBackPressed = {}) {}
    }
}