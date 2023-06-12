package com.techlads.composetv.features.domain.datasource.network

import com.techlads.composetv.features.domain.Resource
import com.techlads.composetv.features.domain.model.MovieDetails
import com.techlads.composetv.features.domain.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey: String = "52577e7ae3a086197e871c8041c6e48b",
        @Query("language") language: String = "en"
    ): MoviesResponse


    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "52577e7ae3a086197e871c8041c6e48b",
        @Query("language") language: String = "en"
    ): Resource<MovieDetails>
}
