package com.techlads.composetv.features.home.carousel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.techlads.composetv.R
import com.techlads.composetv.features.domain.model.Movie
import com.techlads.composetv.utils.Constants.IMAGE_BASE_UR
import com.techlads.composetv.widgets.BorderedFocusableItem

@Composable
fun CarouselItem(
    parent: Int,
    child: Int,
    onItemFocus: (parent: Int, child: Int) -> Unit,
    onItemClick: (parent: Int, child: Int) -> Unit
) {
    BorderedFocusableItem(
        onClick = { onItemClick(parent, child) },
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .aspectRatio(1.8f)
            .onFocusChanged {
                if (it.isFocused) {
                    onItemFocus(parent, child)
                }
            }) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = "Item $parent x $child", textAlign = TextAlign.Center)
        }
    }
}


@Composable
fun VerticalCarouselItem(parent: Int, child: Int, onItemFocus: (parent: Int, child: Int) -> Unit) {
    BorderedFocusableItem(
        onClick = {
            onItemFocus(parent, child)
        },
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(0.6f)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

            Text(text = "Item $parent x $child", textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun VerticalMovieItem(movie: Movie,
                      onItemFocus: (parent: Int, child: Int) -> Unit) {
    BorderedFocusableItem(
        onClick = {
            onItemFocus(movie.id, movie.genreIds[0])
        },
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(0.6f)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = "$IMAGE_BASE_UR/${movie.posterPath}")
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.ic_placeholder)
                            crossfade(true)
                        }).build()
                ),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = "Movie Banner"
            )
        }
    }
}

@Preview
@Composable
fun CarouselItemPrev() {
    CarouselItem(1, 1, onItemClick = { _, _ -> }, onItemFocus = { _, _ -> })
}


@Preview
@Composable
fun VerticalCarouselItemPrev() {
    VerticalCarouselItem(1, 1) { _, _ ->

    }
}
