package com.techlads.content.di

import com.techlads.content.FakeMoviesService
import com.techlads.content.MoviesService
import com.techlads.content.TmdbApiServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    @Named("TmdbApiService")
    fun provideTmdbApiService(
        client: HttpClient,
        @Named("TMDBBaseUrl") baseUrl: String,
        @Named("TMDBApiKey") apiKey: String
    ): MoviesService {
        return TmdbApiServiceImpl(client, baseUrl, apiKey)
    }

    @Provides
    @Singleton
    @Named("FakeMoviesService")
    fun provideFakeApiService(): MoviesService {
        return FakeMoviesService()
    }
}
