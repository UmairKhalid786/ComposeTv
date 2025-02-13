package com.techlads.config.di

import com.techlads.config.ConfigApiService
import com.techlads.config.ConfigApiServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {

    @Provides
    @Singleton
    fun provideConfigApiService(
        client: HttpClient,
        @Named("TMDBBaseUrl") baseUrl: String,
        @Named("TMDBApiKey") apiKey: String
    ): ConfigApiService {
        return ConfigApiServiceImpl(client, baseUrl, apiKey)
    }
}
