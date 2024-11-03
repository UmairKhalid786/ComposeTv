package com.techlads.composetv.features.home.network.di

import com.techlads.composetv.features.home.network.TmdbApiService
import com.techlads.composetv.features.home.network.TmdbApiServiceImpl
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
