package com.techlads.composetv.features.details

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.composetv.R
import com.techlads.composetv.theme.AppTheme
import com.techlads.composetv.theme.ComposeTvTheme
import com.techlads.composetv.widgets.Button
import com.techlads.composetv.widgets.ThumbnailImageCard
import kotlinx.coroutines.delay

const val ANIMATION_DELAY = 600L

@Composable
fun ProductDetailsScreen(onBackPressed: () -> Unit, onPlayClick: () -> Unit) {
    ProductDetailsContent(onBackPressed, onPlayClick = onPlayClick)
}

@Composable
private fun ProductDetailsContent(onBackPressed: () -> Unit, onPlayClick: () -> Unit) {

    BackHandler(onBack = onBackPressed)

    val isLoaded = remember {
        mutableStateOf(false)
    }

    val animatedPortraitSize = animateDpAsState(targetValue = if (isLoaded.value) 150.dp else 1.dp)

    LaunchedEffect(key1 = Unit) {
        delay(ANIMATION_DELAY)
        isLoaded.value = true
    }

    Box {
        SearchIcon(
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.TopStart)
                .padding(24.dp)
                .zIndex(1f)
        )

        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxSize()
        ) {
            BannerImage(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(.4f)
            )
            Column(modifier = Modifier.weight(.6f)) {
                ButtonSection(onPlayClick)
                DetailsSection()
            }
        }

        ThumbnailImageCard(
            Modifier
                .align(Alignment.CenterStart)
                .padding(start = 30.dp)
                .width(animatedPortraitSize.value)
        ) {
            Text(text = "1x1")
        }
    }
}


@Composable
fun SearchIcon(modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = "search",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun BannerImage(modifier: Modifier) {
    Image(
        modifier = modifier
            .fillMaxSize()
            .height(200.dp),
        painter = painterResource(id = R.drawable.hero_item),
        contentDescription = "Hero item background",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ButtonSection(onPlayClick: () -> Unit) {

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit) {
        delay(ANIMATION_DELAY)
        focusRequester.requestFocus()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = AppTheme.surface.copy(alpha = 0.5f)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(280.dp))

        Button(
            text = "Play",
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .focusRequester(focusRequester), onClick = onPlayClick
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            color = LocalContentColor.current,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            text = stringResource(R.string.watch_trailer)
        )
    }
}

@Composable
fun DetailsSection() {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.size(220.dp))
        Column(
            Modifier
                .fillMaxWidth()
                //.weight(.7f)
                .padding(24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier,
                color = LocalContentColor.current,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineLarge,
                text = stringResource(R.string.movie_name)
            )

            Spacer(modifier = Modifier.size(10.dp))

            MovieInfoSection()

            Spacer(modifier = Modifier.size(10.dp))
            Text(
                modifier = Modifier,
                color = LocalContentColor.current,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge,
                text = stringResource(R.string.movie_desciption)
            )
        }
    }
}

@Composable
fun MovieInfoSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier,
            color = LocalContentColor.current,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(R.string.release_year)
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            modifier = Modifier,
            color = LocalContentColor.current,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(R.string.movie_duration_text)
        )
        Spacer(modifier = Modifier.size(12.dp))
        Rating(rating = "8.3")
    }
}

@Composable
fun Rating(rating: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(18.dp),
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = "search",
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier,
            color = LocalContentColor.current,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
            text = rating
        )
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun DetailsScreenPrev() {
    ComposeTvTheme {
        ProductDetailsScreen(onPlayClick = {}, onBackPressed = {})
    }
}
