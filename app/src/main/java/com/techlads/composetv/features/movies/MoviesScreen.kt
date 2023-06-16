package com.techlads.composetv.features.movies

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.tv.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvGridItemSpan
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import com.techlads.composetv.features.domain.model.Movie
import com.techlads.composetv.features.home.carousel.VerticalMovieItem

@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
    onItemFocus: (parent: Int, child: Int) -> Unit
) {

    LaunchedEffect(key1 = true) {
        viewModel.getNowPlayingMovies()
    }

    val movies = viewModel.moviesState.value.data?.collectAsLazyPagingItems()
    MoviesGrid(movies, Modifier, onItemFocus)

}

@Composable
fun MoviesGrid(
    nowPlayingMovies: LazyPagingItems<Movie>?,
    modifier: Modifier,
    onItemFocus: (parent: Int, child: Int) -> Unit,
) {
    when (nowPlayingMovies?.loadState?.refresh) { //First load
        is LoadState.Error -> {
            ErrorMessageText()
        }
        is LoadState.Loading -> {
            ProgressIndicator()
        }
        else -> { }
    }

    when (nowPlayingMovies?.loadState?.refresh) { // Pagination
        is LoadState.Error -> {
            ErrorMessageText()
        }
        is LoadState.Loading -> {

        }
        else -> {
            MoviesVerticalGrid(
                modifier = modifier,
                movies = nowPlayingMovies,
                onItemFocus = onItemFocus
            )
        }
    }
}


@Composable
fun MoviesVerticalGrid(
    modifier: Modifier,
    movies: LazyPagingItems<Movie>?,
    onItemFocus: (parent: Int, child: Int) -> Unit
) {
    TvLazyVerticalGrid(
        modifier = modifier,
        columns = TvGridCells.Fixed(5),
        contentPadding = PaddingValues(
            start = 24.dp,
            top = 24.dp,
            end = 24.dp,
            bottom = 48.dp
        )
    ) {
        item(span = {
            TvGridItemSpan(5)
        }) {
            GridHeader()
        }
        if (movies != null) items(movies.itemCount) { index ->
            val movie = movies[index]
            movie?.let {
                VerticalMovieItem(
                    movie = it,
                    onItemFocus = onItemFocus
                )
            }
        }
    }
}

@Composable
fun ProgressIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .wrapContentSize()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun ErrorMessageText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Something went wrong.",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 24.dp, start = 8.dp)
        )
    }
}

@Composable
fun GridHeader() {
    Text(
        text = "Movies",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 24.dp, start = 8.dp)
    )
}


@Preview
@Composable
fun MoviesScreenPrev() {
    MoviesScreen { _, _ -> }
}