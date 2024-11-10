@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.features.home.hero

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.Carousel
import androidx.tv.material3.CarouselDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.SurfaceDefaults
import androidx.tv.material3.Text
import androidx.tv.material3.rememberCarouselState
import com.techlads.composetv.features.login.withEmailPassword.Movie
import com.techlads.composetv.features.login.withEmailPassword.backgroundImageState
import com.techlads.composetv.theme.ComposeTvTheme
import com.techlads.composetv.utils.Storage.movies
import kotlinx.coroutines.delay

@Composable
fun HeroItem(modifier: Modifier = Modifier) {

    val carouselState = rememberCarouselState()
    val backgroundState = backgroundImageState()
    var focused by remember { mutableStateOf(false) }
    val height by animateDpAsState(if (focused) 300.dp else 200.dp, label = "")

    Carousel(itemCount = movies.size,
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .onFocusChanged {
                focused = it.hasFocus
            }
            .height(height)
            .fillMaxWidth(),
        carouselState = carouselState,
        carouselIndicator = {
            CarouselDefaults.IndicatorRow(itemCount = movies.size,
                activeItemIndex = carouselState.activeItemIndex,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(30.dp),
                indicator = { isActive ->
                    val activeColor = MaterialTheme.colorScheme.onSurface
                    val inactiveColor = activeColor.copy(alpha = 0.3f)
                    Surface(
                        colors = SurfaceDefaults.colors(containerColor = if (isActive) activeColor else inactiveColor),
                        shape = CircleShape,
                        modifier = Modifier.size(8.dp)
                    ) {}
                })
        },
        contentTransformEndToStart = fadeIn(tween(1000)).togetherWith(fadeOut(tween(1000))),
        contentTransformStartToEnd = fadeIn(tween(1000)).togetherWith(fadeOut(tween(1000)))
    ) {
        LaunchedEffect(it) {
            backgroundState.load(movies[it].imageUrl, onSuccess = {}, onError = {})
        }

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            ProductDetails(
                focused = focused,
                movie = movies[it],
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(0.5f)
                    .padding(32.dp)
            )
        }
    }
}

@Composable
fun ProductDetails(
    movie: Movie,
    modifier: Modifier = Modifier,
    focused: Boolean = false,
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(focused) {
        if (focused) {
            delay(200)
            focusRequester.requestFocus()
        }
    }

    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        AnimatedContent(movie.metadata) {
            Text(
                modifier = Modifier.graphicsLayer { alpha = 0.5f },
                text = it,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }


        AnimatedContent(movie.title) {
            Text(
                text = it,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Black
                ),
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        Spacer(modifier = Modifier.size(2.dp))

        AnimatedContent(movie.details) {
            Text(
                modifier = Modifier.graphicsLayer { alpha = 0.5f },
                text = it,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraLight),
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        Spacer(modifier = Modifier.size(16.dp))
        AnimatedVisibility(focused, enter = fadeIn(), exit = fadeOut()) {
            Button(onClick = {}, modifier = Modifier
                .focusRequester(focusRequester)
                // Duration of animation here should be less than or equal to carousel's
                // contentTransform duration to ensure the item below does not disappear
                // abruptly.
                .animateEnterExit(
                    enter = slideInHorizontally(animationSpec = tween(1000)) { it / 2 },
                    exit = slideOutHorizontally(animationSpec = tween(1000))
                ), contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        contentDescription = null,
                        imageVector = Icons.Default.PlayArrow,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(text = "Watch now")
                }
            }
        }
    }
}

@Preview
@Composable
fun HeroItemPrev() {
    ComposeTvTheme {
        HeroItem()
    }
}
