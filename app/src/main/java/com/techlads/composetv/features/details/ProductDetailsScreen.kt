package com.techlads.composetv.features.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.KeyboardDoubleArrowDown
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.Glow
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.techlads.composetv.R
import com.techlads.composetv.features.cast.PersonCard
import com.techlads.composetv.features.details.ProductDetailsState.Success
import com.techlads.composetv.theme.ComposeTvTheme
import com.techlads.uicomponents.widgets.TvButton

@Composable
fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel,
    onBackPressed: () -> Unit,
    onPlayClick: () -> Unit
) {
    val uiDetails = viewModel.details.collectAsStateWithLifecycle()
    uiDetails.value?.let {
        TVProductDetailsContent(
            modifier = modifier,
            state = it,
            onBackPressed = onBackPressed,
            onPlayClick = onPlayClick
        )
    }
}

@Composable
private fun TVProductDetailsContent(
    modifier: Modifier = Modifier,
    state: ProductDetailsState,
    onBackPressed: () -> Unit,
    onPlayClick: () -> Unit
) {
    BackHandler(onBack = onBackPressed)
    when (state) {
        is Success -> TVProductDetails(modifier = modifier, state.details, onPlayClick)
        else -> {}
    }
}

@Composable
private fun TVProductDetails(
    modifier: Modifier = Modifier, details: Details, onPlayClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        val background = MaterialTheme.colorScheme.surface
        val state = rememberPagerState(pageCount = { 2 })
        AsyncImage(
            model = details.background,
            contentDescription = "Product Banner",
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    drawContent()
                    drawRect(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                background.copy(alpha = 0.8f), background.copy(alpha = 0.1f)
                            ), startX = 0.0f, endX = size.width
                        )
                    )
                }
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Crop)

        VerticalPager(
            state = state,
        ) { page ->
            when (page) {
                0 -> {
                    key("content_detail") {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            ContentDetail(
                                Modifier
                                    .fillMaxSize()
                                    .padding(start = 72.dp, top = 120.dp),
                                details = details,
                                onPlayClick = onPlayClick
                            )

                            ArrowDownButton(
                                modifier = Modifier.align(Alignment.Center),
                                text = "More Details",
                                icon = Icons.Default.KeyboardDoubleArrowDown,
                                onClick = { }
                            )
                        }
                    }
                }

                else -> {
                    key("more_details") {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            MoreDetailsSection(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 90.dp),
                                details = details
                            )

                            ArrowDownButton(
                                modifier = Modifier.align(Alignment.BottomCenter),
                                text = "Press Up",
                                icon = Icons.Default.KeyboardDoubleArrowUp,
                                onClick = { }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MoreDetailsSection(modifier: Modifier, details: Details) {
    Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        UserRatingsSection(modifier = Modifier.fillMaxWidth(), details = details)
        Spacer(modifier = Modifier.height(16.dp))
        CastAndCrew(modifier = Modifier.fillMaxWidth(), details = details)
    }
}

@Composable
fun CastAndCrew(modifier: Modifier, details: Details) {
    Column(modifier) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp, start = 72.dp),
            text = stringResource(R.string.cast_and_crew),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(clip = false),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 72.dp)
        ) {
            items(details.cast) {
                PersonCard(
                    person = it, modifier = Modifier.width((30 * 4).dp)
                )
            }
        }
    }
}

@Composable
private fun UserRatingsSection(modifier: Modifier = Modifier, details: Details) {
    Column(modifier) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp, start = 72.dp),
            text = stringResource(R.string.user_ratings),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        var gridWidth by remember { mutableStateOf(0) }
        val cardCount = 3
        val spacing = 16.dp
        val horizontalPadding = 72.dp
        val density = LocalDensity.current

        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    gridWidth = coordinates.size.width
                }
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.spacedBy(spacing),
            contentPadding = PaddingValues(horizontal = horizontalPadding)) {

            val cardWidthPx = if (gridWidth > 0) {
                with(density) {
                    val totalSpacing = spacing.toPx() * (cardCount - 1)
                    val totalPadding = horizontalPadding.toPx() * 2
                    ((gridWidth - totalSpacing - totalPadding) / cardCount).toDp()
                }
            } else 200.dp

            item {
                RatingCard(
                    modifier = Modifier
                        .width(cardWidthPx)
                        .height(cardWidthPx),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(
                            16.dp, Alignment.CenterVertically
                        ),
                    ) {
                        Text(
                            text = "Rotten Tomatoes",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Fastfood,
                                contentDescription = "92%",
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(Modifier.size(8.dp))
                            Text(
                                text = "92%", style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ), color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }
            item {
                RatingCard(
                    modifier = Modifier
                        .width(cardWidthPx)
                        .height(cardWidthPx),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(
                            16.dp, Alignment.CenterVertically
                        ),
                    ) {
                        Text(
                            text = "IMDb", style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            ), color = MaterialTheme.colorScheme.onSurface
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Star,
                                contentDescription = "Rating",
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(Modifier.size(8.dp))
                            Text(
                                text = "8.3/10",
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }
            item {
                RatingCard(
                    modifier = Modifier
                        .width(cardWidthPx)
                        .height(cardWidthPx),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(
                            16.dp, Alignment.CenterVertically
                        ),
                    ) {
                        Text(
                            text = "Metacritic", style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            ), color = MaterialTheme.colorScheme.onSurface
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Feedback,
                                contentDescription = "Rating",
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(Modifier.size(8.dp))
                            Text(
                                text = "Generally Favorable",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RatingCard(
    modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit
) {
    Card(
        colors = CardDefaults.colors(containerColor = MaterialTheme.colorScheme.surface),
        glow = CardDefaults.glow(
            glow = Glow(elevationColor = MaterialTheme.colorScheme.border, elevation = 2.dp)
        ),
        scale = CardDefaults.scale(focusedScale = 1.01f),
        modifier = modifier,
        onClick = { /* No-op */ }) {
        content()
    }
}

@Composable
private fun ContentDetail(
    modifier: Modifier = Modifier, details: Details, onPlayClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        // Title
        Text(
            text = details.title,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(Modifier.size(16.dp))

        // Info Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TVInfoChip(text = details.releaseDate)
            Spacer(Modifier.width(16.dp))
            TVInfoChip(text = details.genres.joinToString())
            Spacer(Modifier.width(16.dp))
            TVRatingChip(rating = "8.3")
        }

        Spacer(Modifier.height(24.dp))

        // Description
        Text(
            modifier = Modifier.fillMaxWidth(0.6f),
            text = details.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(Modifier.height(32.dp))

        // Play Button
        TvButton(onClick = onPlayClick) {
            Text(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = stringResource(R.string.play),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun TVInfoChip(text: String) {
    Box {
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
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
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
            modifier = Modifier.background(Color.Red), state = Success(
                details = Details(
                    title = "Venom 3: Let There Be Carnage",
                    background = "www.test.image.jpg",
                    description = "This is a dummy movie" + " This is to test the details screen in tv compose application." + " This description is to be long enough to test the text wrapping capabilities" + " of the text component in compose for tv. " + " Hopefully it works as expected.",
                    releaseDate = "14 august 1994",
                    cast = List(10) { "https://via.placeholder.com/150" },
                    genres = List(5) { "Genre $it" })

            ), onBackPressed = {}) {}
    }
}
