package com.techlads.composetv.features.domain.usecases

import javax.inject.Inject

data class MoviesUseCase @Inject constructor(
    val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    val getMovieDetails: GetMovieDetailsUseCase
)