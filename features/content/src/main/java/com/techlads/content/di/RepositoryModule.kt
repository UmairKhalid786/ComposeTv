package com.techlads.content.di

import com.techlads.content.TmdbApiService
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
    fun provideTmdbApiService(
        client: HttpClient,
        @Named("TMDBBaseUrl") baseUrl: String,
        @Named("TMDBApiKey") apiKey: String
    ): TmdbApiService {
        return TmdbApiServiceImpl(client, baseUrl, apiKey)
    }
}
